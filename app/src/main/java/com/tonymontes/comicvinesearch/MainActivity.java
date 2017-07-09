
package com.tonymontes.comicvinesearch;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.tonymontes.comicvine.utils.Constants;
import com.tonymontes.comicvinesearch.activities.SearchIssuesActivity;
import com.tonymontes.comicvinesearch.activities.SettingsActivity;
import com.tonymontes.comicvinesearch.fragments.AbstractFragment;
import com.tonymontes.comicvinesearch.fragments.MessageDialogFragment;
import com.tonymontes.comicvinesearch.fragments.SearchVolumesFragment;
import com.tonymontes.comicvinesearch.items.VolumeCellItem;
import com.tonymontes.comicvinesearch.listeners.OnFragmentInteractionListener;
import com.tonymontes.comicvinesearch.services.VolumeService;
import com.tonymontes.comicvinesearch.utils.Utils;
import com.tonymontes.comicvinesearch.views.HeaderView;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.flexibleadapter.utils.Log;

@SuppressWarnings({"ConstantConditions", "unchecked"})
public class MainActivity
    extends
        AppCompatActivity
    implements
        FlexibleAdapter.OnItemClickListener,             // onItemClick
        NavigationView.OnNavigationItemSelectedListener, // onNavigationItemSelected
        OnFragmentInteractionListener {                  // onFragmentChange

    // bind components to variables
    @BindView(R.id.toolbar) protected Toolbar mToolbar;
    @BindView(R.id.drawer_layout) protected DrawerLayout mDrawer;
    @BindView(R.id.nav_view) protected NavigationView mNavigationView;
    @BindView(R.id.toolbar_header_view) protected HeaderView mHeaderView;

    private static final String STATE_ACTIVE_FRAGMENT = "active_fragment";

    private RecyclerView mRecyclerView;
    private FlexibleAdapter<AbstractFlexibleItem> mAdapter;
    private AbstractFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initToolbar();
        initDrawer();

        initFragment(savedInstanceState);

        if (shouldAskPermissions())
            askPermissions();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        mAdapter.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, STATE_ACTIVE_FRAGMENT, mFragment);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // restore previous state
        if (savedInstanceState != null && mAdapter != null)
            mAdapter.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onFragmentChange(RecyclerView recyclerView) {

        mRecyclerView = recyclerView;
        mAdapter = (FlexibleAdapter) recyclerView.getAdapter();
    }

    private void initFragment(Bundle savedInstanceState) {

        if (savedInstanceState != null)
            mFragment = (AbstractFragment) getSupportFragmentManager().getFragment(savedInstanceState, STATE_ACTIVE_FRAGMENT);

        if (mFragment == null)
            mFragment = SearchVolumesFragment.newInstance();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.recycler_view_container, mFragment).commit();
    }

    private void initToolbar() {

        mHeaderView.bindTo(getString(R.string.app_name));

        setSupportActionBar(mToolbar);
    }

    private void initDrawer() {

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

        View headerView = mNavigationView.getHeaderView(0);

        // name
        TextView appName = headerView.findViewById(R.id.app_name);
        appName.setText(R.string.app_name);

        // version
        TextView appVersion = headerView.findViewById(R.id.app_version);
        appVersion.setText(getString(R.string.about_version, Utils.getVersionName(this)));
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        // Handle navigation view item clicks here.
        int itemId = menuItem.getItemId();
        switch (itemId) {
            case R.id.nav_comicvine: {

                mFragment = SearchVolumesFragment.newInstance();
                break;
            }
            case R.id.nav_about: {

                MessageDialogFragment.newInstance(
                        R.drawable.ic_about_white,
                        getString(R.string.about_title),
                        getString(R.string.about_body, Utils.getVersionName(this)))
                        .show(getFragmentManager(), MessageDialogFragment.TAG);

                // close drawer
                mDrawer.closeDrawers();

                return true;
            }
            default:
                break;
        }

        if (mFragment != null) {

            // replace existing fragment with a new one
            menuItem.setChecked(true);

            if (mAdapter != null)
                mAdapter.onDetachedFromRecyclerView(mRecyclerView);

            // inflate the new fragment with a new RecyclerView and a new Adapter
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.recycler_view_container, mFragment).commit();

            // close drawer
            mDrawer.closeDrawers();
            return true;
        }

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        int id = menuItem.getItemId();
        switch (id) {

            case android.R.id.home: {

                // app icon in action bar clicked; go to parent activity.
                this.finish();
                return true;
            }
            case R.id.action_settings: {

                startActivity(new Intent(this, SettingsActivity.class));
                break;
            }
            default:
                break;
        }

        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onBackPressed() {

        // if the drawer is open, the back key closes it
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {

            mDrawer.closeDrawer(GravityCompat.START);
            return;
        }

        // close the App
        VolumeService.onDestroy();
        super.onBackPressed();
    }

    @Override
    public boolean onItemClick(int position) {
        Log.d("onItemClick");

        // notify active callbacks or implement a custom action onClick
        IFlexible flexibleItem = mAdapter.getItem(position);
        if (flexibleItem instanceof VolumeCellItem) {

            VolumeCellItem volumeCellItem = (VolumeCellItem) flexibleItem;

            String title = volumeCellItem.getIssueSearchTitle();
            String comicvineID = volumeCellItem.getComicvineID();

            getIssuesForVolumeWithCvID(title, comicvineID);
        }

        return false;
    }

    protected boolean shouldAskPermissions() {

        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(23)
    protected void askPermissions() {

        String[] permissions = {
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        int requestCode = 200;
        requestPermissions(permissions, requestCode);
    }

    private boolean getIssuesForVolumeWithCvID(String title, String comicvineID) {

        if (!comicvineID.isEmpty() && !comicvineID.equals("0")) {

            int searchType = Constants.getIssuesForVolumeWithCvID;

            String cvQuery = Utils.buildQuery(searchType, comicvineID, "", 0); // get apikey from settings
            if (cvQuery == null || cvQuery.isEmpty()) {

                Utils.showToast(getString(R.string.incorrect_cv_key));
                return false;
            }

            startSearchIssuesActivity(title, cvQuery);
        }

        return true;
    }

    private void startSearchIssuesActivity(String title, String cvQuery) {

        Intent intent = new Intent(this, SearchIssuesActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("cvQuery", cvQuery);
        startActivity(intent);
    }
}
