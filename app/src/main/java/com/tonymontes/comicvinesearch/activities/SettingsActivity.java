
package com.tonymontes.comicvinesearch.activities;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.tonymontes.comicvinesearch.R;
import com.tonymontes.comicvinesearch.utils.Utils;

/**
 * @author tony
 * @since 7/8/16
 */

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new PrefsFragment())
                .commit();
    }

    protected void onPostCreate(Bundle savedInstanceState) {

        super.onPostCreate(savedInstanceState);

        // add a back button
        LinearLayout root = (LinearLayout) findViewById(android.R.id.list).getParent().getParent().getParent();
        AppBarLayout bar = (AppBarLayout) LayoutInflater.from(this).inflate(R.layout.toolbar_settings, root, false);
        root.addView(bar, 0);

        Toolbar tbar = (Toolbar) bar.getChildAt(0);
        tbar.setNavigationOnClickListener(v -> finish());
    }

    public static class PrefsFragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);

            // load preferences from an XML resource
            addPreferencesFromResource(R.xml.pref_settings);

            String appVersion;
            try {
                PackageInfo pInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
                appVersion = "v" + pInfo.versionName;
            } catch (PackageManager.NameNotFoundException e) {
                appVersion = "unknown";
            }
            Preference app_version = findPreference("pref_key_application_version");
            app_version.setSummary(appVersion);
        }

        @Override
        public void onStart() {
            super.onStart();

            String apiKey = Utils.getSettingsValue(getString(R.string.CV_APIKEY_PREF_NAME));
            if (apiKey != null && !apiKey.isEmpty()) {

                Preference cv_credentials = findPreference("pref_key_cv_credentials");
                cv_credentials.setSummary(apiKey);
            }
        }
    }
}
