
package com.tonymontes.comicvinesearch.fragments;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.SearchView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.tonymontes.comicvine.Volume;
import com.tonymontes.comicvine.network.ComicvineInterface;
import com.tonymontes.comicvine.responses.ComicvineVolumesResponse;
import com.tonymontes.comicvine.utils.Constants;
import com.tonymontes.comicvinesearch.R;
import com.tonymontes.comicvinesearch.activities.SearchIssuesActivity;
import com.tonymontes.comicvinesearch.adapters.SearchVolumesAdapter;
import com.tonymontes.comicvinesearch.services.VolumeService;
import com.tonymontes.comicvinesearch.utils.Utils;

import java.util.List;

import eu.davidea.flexibleadapter.common.FlexibleItemDecoration;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchVolumesFragment
    extends
        AbstractFragment {

    private static String key = "searchQuery";

    public static final int searchScopeVolumes = 0;
//    public static final int searchScopeIssues = 1;

    private AppBarLayout appBarLayout;
    private ProgressBar progress;
    private SwipeRefreshLayout swipeRefreshLayout;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private SearchVolumesAdapter mAdapter;

    private SearchView searchView;
    private MenuItem searchMenuItem;
    private TabLayout tabLayout;

    private boolean collapsingSearchView = true;
    private boolean isSearching = false;

    private int searchType = Constants.getVolumesForSearchTerm;
    private String searchTermOrCvID;
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

    public static SearchVolumesFragment newInstance() {

        return new SearchVolumesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_search_volumes, container, false);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        progress = getView().findViewById(R.id.progressBar);

        initSwipeToRefresh();
        initRecyclerView();

        // programmatically add comicvine_search_tabs to appBarLayout
        add_tabs();
    }

    @Override
    public void onDestroyView() {

        appBarLayout.removeView(tabLayout);

        super.onDestroyView();
    }

    @Override
    public void onDestroy() {

        mCompositeDisposable.clear();

        super.onDestroy();
    }

    @SuppressWarnings("ConstantConditions")
    private void initSwipeToRefresh() {

        swipeRefreshLayout = getView().findViewById(R.id.swipeRefreshLayout);

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

    @SuppressWarnings({"ConstantConditions", "NullableProblems"})
    private void initRecyclerView() {

        mAdapter = new SearchVolumesAdapter(null, getActivity());

        mAdapter.setAutoCollapseOnExpand(true)
                .setAutoScrollOnExpand(true);

        mRecyclerView = getView().findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(createNewLinearLayoutManager());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);

        // custom divider item decorator
        mRecyclerView.addItemDecoration(new FlexibleItemDecoration(getActivity())
                .withDivider(R.drawable.divider));

        mListener.onFragmentChange(mRecyclerView);
    }

    private void initSearchView(final Menu menu) {

        searchMenuItem = menu.findItem(R.id.cv_action_search);
        if (searchMenuItem == null)
            return;

        searchTermOrCvID = Utils.getSettingsValue(key);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        MenuItemCompat.setOnActionExpandListener(searchMenuItem, new MenuItemCompat.OnActionExpandListener() {

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {

                toggleSwipeRefreshLayout(false);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {

                collapsingSearchView = true;
                toggleSwipeRefreshLayout(true);

                // hide comicvine_search_tabs
                showTabs(false);

                // scoot recycler view up
                View v = getActivity().findViewById(R.id.swipeRefreshLayout);
                v.animate().translationY(0);

                return true;
            }
        });

        searchView = (SearchView) MenuItemCompat.getActionView(searchMenuItem);
        searchView.setOnSearchClickListener(view -> {

            // show comicvine_search_tabs
            showTabs(true);

            // scoot recycler view down
            View v = getActivity().findViewById(R.id.swipeRefreshLayout);
            int tabBarHeight = 160;
            v.animate().translationY(tabBarHeight);

            searchView.setQuery(searchTermOrCvID, false);
        });
        searchView.setInputType(InputType.TYPE_TEXT_VARIATION_FILTER);
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_FULLSCREEN);
        searchView.setQueryHint(getHint(searchScopeVolumes));
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String text) {

                return getDataForSearchTerm(text);
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                // ignore text changes while collapsing the search view
                if (collapsingSearchView) {
                    collapsingSearchView = false;
                    return true;
                }

                if (newText.isEmpty()) {
                    Utils.removeSettingsValue(key);

                    searchTermOrCvID = null;
                    searchView.setSubmitButtonEnabled(false);
                } else {

                    searchTermOrCvID = newText;
                    searchView.setSubmitButtonEnabled(true);
                }

                return true;
            }
        });

        // expand the SearchView
        searchMenuItem.expandActionView();
    }

    @SuppressWarnings("ConstantConditions")
    private void add_tabs() {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        ViewGroup container = getView().findViewById(R.id.fragment_search_volumes);

        tabLayout = (TabLayout) inflater.inflate(R.layout.cv_search_tabs, container, false);

        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_volumes));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_issues));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int tabPos = tab.getPosition();

                if (searchView != null)
                    searchView.setQueryHint(getHint(tabPos));

                searchType = (tabPos == searchScopeVolumes) ? Constants.getVolumesForSearchTerm : Constants.getIssuesForSearchTerm;
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // noop
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // noop
            }
        });

        appBarLayout = getView().findViewById(R.id.appbar);
        appBarLayout.addView(tabLayout,
                new LinearLayoutCompat.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    private String getHint(int tabPos) {

        return getString(tabPos == searchScopeVolumes ? R.string.action_search_volumes : R.string.action_search_issues);
    }

    private boolean getDataForSearchTerm(String text) {

        // collapse the SearchView
        searchMenuItem.collapseActionView();

        Utils.saveSettingsValue(key, text);
        searchTermOrCvID = text;

        cvQuery = Utils.buildQuery(searchType, searchTermOrCvID, "", 0); // get apikey from settings
        if (cvQuery == null || cvQuery.isEmpty()) {

            Utils.showToast(getString(R.string.incorrect_cv_key));
            hideProgressBar();
            return false;
        }

        doSearch();

        return true;
    }

    private void doSearch() {

        if (!Utils.isNetworkAvailable()) {
            Utils.showToast(R.string.network_not_available);
            return;
        }

        if (searchType == Constants.getVolumesForSearchTerm) {

            showProgressBar();
            makeRequest();
        } else // Constants.getIssuesForSearchTerm

            launchSearchIssuesActivity(searchTermOrCvID);
    }

    private void launchSearchIssuesActivity(String title) {

        Context context = getActivity().getBaseContext();
        Intent intent = new Intent(context, SearchIssuesActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("cvQuery", cvQuery);
        getActivity().startActivity(intent);
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

        mCompositeDisposable.add(comicvineInterface.getVolumes(cvQuery)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(ComicvineVolumesResponse res) {

        hideProgressBar();

        List<Volume> volumes = res.getResults();
        if (volumes.isEmpty()) {

            MessageDialogFragment.newInstance(
                    R.drawable.ic_info_grey600_24dp,
                    getString(R.string.ErrorFetchingVolumes),
                    getString(R.string.NoResultsFound))
                    .show(getActivity().getFragmentManager(), MessageDialogFragment.TAG);

            // expand the SearchView
            searchMenuItem.expandActionView();
            return;
        }

        setFeedResultsFrom(volumes);

        toggleSwipeRefreshLayout(true);
    }

    private void setFeedResultsFrom(List<Volume> volumes) {

        // generate volume headers with one cell each
        VolumeService volumeService = VolumeService.getInstance();
        List<AbstractFlexibleItem> feedResults = volumeService.createVolumeListFrom(volumes);

        // populate the mAdapter
        mAdapter.updateDataSet(feedResults, true);
    }

    private void handleError(Throwable error) {

        hideProgressBar();
        Utils.showToast("Error " + error.getLocalizedMessage());
    }

    private void toggleSwipeRefreshLayout(Boolean show) {

        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setVisibility(show ? View.VISIBLE : View.GONE);
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

    private void showTabs(final boolean show) {

        tabLayout.postDelayed(() -> tabLayout.setVisibility(show ? View.VISIBLE : View.GONE), 200);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_actions, menu);
        initSearchView(menu);
    }
}