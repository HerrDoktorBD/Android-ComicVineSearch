
package com.tonymontes.comicvinesearch.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.tonymontes.comicvinesearch.R;

public class SearchIssuesActivity
        extends
        AppCompatActivity {

    private String headerLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get title from passed intent
        Intent intent = getIntent();
        headerLabel = intent.getStringExtra("title");

        setContentView(R.layout.activity_search_issues);

        setupToolbar();
    }

    private void setupToolbar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {

            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(headerLabel);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = menuItem.getItemId();
        switch (id) {

            case android.R.id.home: {

                // app icon in action bar clicked; goto parent activity.
                finish();
                return true;
            }
            default: {
                break;
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
