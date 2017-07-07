
package com.tonymontes.comicvinesearch.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.tonymontes.comicvine.Issue;
import com.tonymontes.comicvine.network.ComicvineInterface;
import com.tonymontes.comicvine.responses.ComicvineIssuesResponse;
import com.tonymontes.comicvinesearch.R;
import com.tonymontes.comicvinesearch.utils.Utils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.tonymontes.comicvine.utils.constants.getIssuesForVolumeWithCvID;

/**
 * Created by tony on 3/20/17.
 * <p>
 * This is the Comicvine Credentials page.
 * It contains details about Comicvine links and how to register for an API key.
 */
public class CredentialsActivity
        extends
        FragmentActivity {

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private String cvQuery;
    private String apiKey;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_credentials);

        setupAdmin();
    }

    protected void onPostCreate(Bundle savedInstanceState) {

        super.onPostCreate(savedInstanceState);

        // add a back button
        LinearLayout root = (LinearLayout) findViewById(R.id.root).getParent().getParent();
        AppBarLayout bar = (AppBarLayout) LayoutInflater.from(this).inflate(R.layout.toolbar_settings, root, false);
        root.addView(bar, 0);

        Toolbar toolbar = (Toolbar) bar.getChildAt(0);
        toolbar.setTitle(getString(R.string.cv_credentials));

        toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    public void onDestroy() {

        mCompositeDisposable.clear();

        super.onDestroy();
    }

    public void setupAdmin() {

        TextView cvRegisterLink = findViewById(R.id.cv_register_url);
        cvRegisterLink.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.cv_com)));
            startActivity(intent);
        });

        TextView apikeyLink = findViewById(R.id.cv_apikey_url);
        apikeyLink.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.cv_key)));
            startActivity(intent);
        });

        final EditText apikeyView = findViewById(R.id.cv_apikey);
        apikeyView.setTextIsSelectable(true);
        String apiKey = Utils.getSettingsValue(getString(R.string.CV_APIKEY_PREF_NAME));
        apikeyView.setText(apiKey);

        Button saveBtn = findViewById(R.id.confirm);
        saveBtn.setOnClickListener(v -> {

            if (!Utils.isNetworkAvailable()) {
                Utils.showToast(R.string.network_not_available);
                return;
            }

            this.apiKey = apikeyView.getText().toString();
            if (this.apiKey.isEmpty())
                finish();

            // validate the key by getting known issues for a volume
            cvQuery = Utils.buildQuery(getIssuesForVolumeWithCvID, "69379", this.apiKey, 0);
            if (cvQuery == null || cvQuery.isEmpty()) {

                Utils.showToast(R.string.incorrect_cv_key);
                return;
            }

            makeRequest();
        });
    }

    private void makeRequest() {

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

        List<Issue> issues = res.getResults();

        Boolean ok = !issues.isEmpty();
        if (ok)
            Utils.saveSettingsValue(getString(R.string.CV_APIKEY_PREF_NAME), apiKey);
        else
            Utils.removeSettingsValue(getString(R.string.CV_APIKEY_PREF_NAME));
        Utils.showToast(ok ? R.string.correct_cv_key : R.string.incorrect_cv_key);

        finish();
    }

    private void handleError(Throwable error) {

        Utils.showToast("Error " + error.getLocalizedMessage());
        Utils.showToast(R.string.incorrect_cv_key);
    }
}
