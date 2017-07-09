
package com.tonymontes.comicvinesearch.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.tonymontes.comicvine.Issue;
import com.tonymontes.comicvine.network.ComicvineInterface;
import com.tonymontes.comicvine.responses.ComicvineIssuesResponse;
import com.tonymontes.comicvinesearch.R;
import com.tonymontes.comicvinesearch.activities.ImageViewerActivity;
import com.tonymontes.comicvinesearch.adapters.SearchIssuesAdapter;
import com.tonymontes.comicvinesearch.utils.IssueNumberComparator;
import com.tonymontes.comicvinesearch.utils.Utils;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchIssuesFragment
    extends
        Fragment
    implements
        SearchIssuesAdapter.IssueCellListener {

    // bind components to variables
    @BindView(R.id.progressBar) protected ProgressBar progress;
    @BindView(R.id.swipeRefreshLayout) protected SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycler_view) protected RecyclerView recyclerView;
    @BindView(R.id.fab) protected FloatingActionButton mFab;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private SearchIssuesAdapter adapter;

    private boolean isSearching = false;

    private ActionModeCallback actionModeCallback = new ActionModeCallback();
    private ActionMode actionMode;

    private String cvQuery;

    private final Handler mRefreshHandler = new Handler(Looper.getMainLooper(), message -> {

        switch (message.what) {

            case 0: // Stop
                swipeRefreshLayout.setRefreshing(false);
                swipeRefreshLayout.setEnabled(true);
                return true;

            case 1: // Start
                swipeRefreshLayout.setRefreshing(true);
                swipeRefreshLayout.setEnabled(false);
                return true;

            default:
                return false;
        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_search_issues, container, false);
        ButterKnife.bind(this, rootView);

        // get cvQuery from passed intent
        Intent intent = getActivity().getIntent();
        cvQuery = intent.getStringExtra("cvQuery");

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        initRecyclerView();
        initSwipeToRefresh();
        initFab();

        doSearch();
    }

    @Override
    public void onIssueRowClicked(int position) {

        enableActionMode(position);
    }

    @Override
    public void onIssueCoverClicked(String[] covers, int position) {

        Intent intent = new Intent(getActivity(), ImageViewerActivity.class);

        Bundle extras = new Bundle();
        extras.putStringArray("images", covers);
        extras.putInt("position", position);
        intent.putExtras(extras);

        startActivity(intent);
    }

    @Override
    public void onDestroy() {

        mCompositeDisposable.clear();

        super.onDestroy();
    }

    private void initRecyclerView() {

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
    }

    private void initSwipeToRefresh() {

        // swipe down to force synchronization
        swipeRefreshLayout.setDistanceToTriggerSync(390);
        swipeRefreshLayout.setEnabled(true);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_purple,
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light);

        swipeRefreshLayout.setOnRefreshListener(() -> {

            doSearch();

            swipeRefreshLayout.setEnabled(false);
            mRefreshHandler.sendEmptyMessageDelayed(0, 1000L);
        });
    }

    private void initFab() {

        mFab.setOnClickListener(view -> {

            hideFab();
            adapter.clearSelections();
            actionMode.finish();

            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show();
        });
        hideFab();
    }

    private void doSearch() {

        showProgressBar();
        makeRequest();
    }

    private void makeRequest() {

        if (isSearching)
            return;
        isSearching = true;

        ComicvineInterface comicvineInterface = new Retrofit.Builder()
                .baseUrl(getString(R.string.BASE_URL))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ComicvineInterface.class);

        mCompositeDisposable.add(comicvineInterface.getIssues(cvQuery)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(ComicvineIssuesResponse res) {

        hideProgressBar();

        List<Issue> issues = res.getResults();
        if (issues.isEmpty()) {

            MessageDialogFragment.newInstance(
                    R.drawable.ic_info_grey600_24dp,
                    getString(R.string.ErrorFetchingIssues),
                    getString(R.string.NoResultsFound))
                    .show(getActivity().getFragmentManager(), MessageDialogFragment.TAG);

            getActivity().finish();
        }
        Collections.sort(issues, new IssueNumberComparator());

        adapter = new SearchIssuesAdapter(getActivity(), issues, this);
        recyclerView.setAdapter(adapter);
    }

    private void handleError(Throwable error) {

        hideProgressBar();
        Utils.showToast("Error " + error.getLocalizedMessage());
    }

    private void hideFab() {

        if (mFab != null)
            ViewCompat.animate(mFab)
                    .scaleX(0f).scaleY(0f)
                    .alpha(0f).setDuration(100)
                    .start();
    }

    private void showFab() {

        if (mFab != null)
            ViewCompat.animate(mFab)
                    .scaleX(1f).scaleY(1f)
                    .alpha(1f).setDuration(100)
                    .setStartDelay(400L)
                    .start();
    }

    private void showProgressBar() {

        if (progress != null) {
            progress.bringToFront();
            progress.setVisibility(View.VISIBLE);
            progress.setIndeterminate(true);
        }
    }

    private void hideProgressBar() {

        isSearching = false;
        if (progress != null)
            progress.setVisibility(View.GONE);
    }

    private void enableActionMode(int position) {

        if (actionMode == null)
            actionMode = ((AppCompatActivity) getActivity()).startSupportActionMode(actionModeCallback);

        toggleSelection(position);
    }

    private void toggleSelection(int position) {

        adapter.toggleSelection(position);

        int count = adapter.getSelectedItemCount();

        if (count == 0) {

            hideFab();
            actionMode.finish();
        } else {

            showFab();
            actionMode.setTitle(String.format("%s selected", count));
            actionMode.invalidate();
        }
    }

    private class ActionModeCallback implements ActionMode.Callback {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {

            mode.getMenuInflater().inflate(R.menu.action_mode, menu); // inflate the menu over action mode

            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

            // sometimes the menu items will not be visible
            // so set their visibility manually

            menu.findItem(R.id.action_delete).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            menu.findItem(R.id.action_copy).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            menu.findItem(R.id.action_forward).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {

            switch (menuItem.getItemId()) {

                case R.id.action_delete:

                    Utils.showToast("You selected the Delete menu.");
                    break;

                case R.id.action_copy:

                    Utils.showToast("You selected the Copy menu.");
                    break;

                case R.id.action_forward:

                    Utils.showToast("You selected the Forward menu.");
                    break;

                default:
                    break;
            }

            hideFab();
            actionMode.finish();
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

            hideFab();
            adapter.clearSelections();
            actionMode = null;
        }
    }
}
