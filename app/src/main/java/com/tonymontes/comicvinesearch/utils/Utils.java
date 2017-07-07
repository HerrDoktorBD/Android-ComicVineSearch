
package com.tonymontes.comicvinesearch.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Looper;
import android.text.ParcelableSpan;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.tonymontes.comicvine.utils.constants;
import com.tonymontes.comicvinesearch.R;
import com.tonymontes.comicvinesearch.app.ComicvineSearch;

import java.util.ArrayList;
import java.util.List;

import static com.tonymontes.comicvine.R.string.CV_APIKEY_PREF_NAME;
import static com.tonymontes.comicvine.R.string.CV_PREFS_NAME;

public final class Utils {

    private Utils() {
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    public static int dpToPx(Context context, float dp) {
        return Math.round(dp * getDisplayMetrics(context).density);
    }

    /**
     * API 21
     *
     * @see Build.VERSION_CODES#LOLLIPOP
     */
    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * API 23
     *
     * @see Build.VERSION_CODES#M
     */
    public static boolean hasMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public static String getVersionName(Context context) {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return "v" + pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return context.getString(android.R.string.unknownName);
        }
    }

    public static void setImageForItem(Context context,
                                       final String imageUrlPath,
                                       final ImageView imageView) {
        // cover placeholder
        int resourceId = R.drawable.cl_placeholder_3x;

        if (imageUrlPath == null || imageUrlPath.isEmpty()) {
            Glide.with(context)
                    .load(resourceId)
                    .into(imageView);
            return;
        }

        // ignore comicvine default images
        if (Utils.isSillyComicVineImage(imageUrlPath)) {

            // show the placeholder image instead
            Glide.with(context)
                    .load(resourceId)
                    .placeholder(resourceId)
                    .fitCenter()
                    .into(imageView);
            return;
        }

        // if there is an image in the object, show it
        Glide.with(context)
                .load(imageUrlPath)
                .asBitmap()
                .placeholder(resourceId)
                .error(resourceId)
                .fitCenter()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                        // do something with the bitmap
                        // for demonstration purposes, let's just set it to an ImageView
                        imageView.setImageBitmap(bitmap);
                    }
                });
    }

    public static String getSettingsValue(String key) {

        SharedPreferences settings = ComicvineSearch.getContext().getSharedPreferences(ComicvineSearch.getResourceString(CV_PREFS_NAME), 0);
        return settings.getString(key, null);
    }

    public static void saveSettingsValue(String key, String value) {

        SharedPreferences settings = ComicvineSearch.getContext().getSharedPreferences(ComicvineSearch.getResourceString(CV_PREFS_NAME), 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void removeSettingsValue(String key) {

        SharedPreferences settings = ComicvineSearch.getContext().getSharedPreferences(ComicvineSearch.getResourceString(CV_PREFS_NAME), 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(key);
        editor.apply();
    }

    public static String buildQuery(int requestCode, String searchTermOrCvID, String apiKey, int cvOffset) {

        StringBuilder sb = new StringBuilder();

        if (apiKey == null || apiKey.isEmpty())
            apiKey = Utils.getSettingsValue(ComicvineSearch.getResourceString(CV_APIKEY_PREF_NAME));
        if (apiKey == null || apiKey.isEmpty())
            return null;

        switch (requestCode) {

            case constants.getDetailsForCharacterWithCvID: {

                sb.append("character/4005-");
                sb.append(searchTermOrCvID);
                sb.append("/?api_key=");
                sb.append(apiKey);
                sb.append("&format=");
                sb.append(constants.cvFormat);

                //query = "http://comicvine.gamespot.com/api/character/4005-108021/?api_key=YOUR_KEY&format=json";
                break;
            }
            case constants.getDetailsForIssueWithCvID: {

                sb.append("issue/4000-");
                sb.append(searchTermOrCvID);
                sb.append("/?api_key=");
                sb.append(apiKey);
                sb.append("&format=");
                sb.append(constants.cvFormat);

                //query = "http://comicvine.gamespot.com/api/issue/4000-30019/?api_key=YOUR_KEY&format=json";
                break;
            }
            case constants.getDetailsForPersonWithCvID: {

                sb.append("person/4040-");
                sb.append(searchTermOrCvID);
                sb.append("/?api_key=");
                sb.append(apiKey);
                sb.append("&format=");
                sb.append(constants.cvFormat);

                //query = "http://comicvine.gamespot.com/api/person/4040-83794/?api_key=YOUR_KEY&format=json";
                break;
            }
            case constants.getDetailsForPublisherWithCvID: {

                sb.append("publisher/4010-");
                sb.append(searchTermOrCvID);
                sb.append("/?api_key=");
                sb.append(apiKey);
                sb.append("&format=");
                sb.append(constants.cvFormat);

                //query = "http://comicvine.gamespot.com/api/publisher/4010-3662/?api_key=YOUR_KEY&format=json";
                break;
            }
            case constants.getDetailsForVolumeWithCvID: {

                sb.append("volume/4050-");
                sb.append(searchTermOrCvID);
                sb.append("/?api_key=");
                sb.append(apiKey);
                sb.append("&format=");
                sb.append(constants.cvFormat);

                //query = "http://comicvine.gamespot.com/api/volume/4050-39022/?api_key=YOUR_KEY&format=json";
                break;
            }
            case constants.getVolumesForSearchTerm: {

                sb.append("volumes");
                sb.append("/?api_key=");
                sb.append(apiKey);
                sb.append("&format=");
                sb.append(constants.cvFormat);
                sb.append("&offset=");
                sb.append(String.valueOf(cvOffset));
                sb.append("&limit=");
                sb.append(constants.cvLimit);
                sb.append("&filter=name:");
                sb.append(searchTermOrCvID);

                //query = "http://comicvine.gamespot.com/api/volumes/?api_key=YOUR_KEY&format=json&filter=name:Mortimer";
                break;
            }
            case constants.getIssuesForVolumeWithCvID: {

                sb.append("issues");
                sb.append("/?api_key=");
                sb.append(apiKey);
                sb.append("&format=");
                sb.append(constants.cvFormat);
                sb.append("&filter=volume:");
                sb.append(searchTermOrCvID);

                //query = "http://comicvine.gamespot.com/api/issues/?api_key=YOUR_KEY&format=json&filter=volume:51195";
                break;
            }
            case constants.getIssuesForSearchTerm: {

                sb.append("search");
                sb.append("/?api_key=");
                sb.append(apiKey);
                sb.append("&format=");
                sb.append(constants.cvFormat);
                sb.append("&offset=");
                sb.append(String.valueOf(cvOffset));
                sb.append("&limit=");
                sb.append(constants.cvLimit);
                sb.append("&sort=");
                sb.append(constants.sortedByName);
                sb.append("&resources=issue");
                sb.append("&query=");
                sb.append(searchTermOrCvID);

                //query = "http://comicvine.gamespot.com/api/search/?api_key=YOUR_KEY&format=json&sort=name:asc&resources=issue&query=D4ve";
                break;
            }
        }

        return sb.toString();
    }

    // http://stackoverflow.com/questions/14981307/how-to-set-multiple-spans-on-a-textviews-text-on-the-same-partial-text
    private static class SimpleSpanBuilder {

        private class SpanSection {
            private final String text;
            private final int startIndex;
            private final ParcelableSpan[] spans;

            SpanSection(String text, int startIndex, ParcelableSpan... spans) {
                this.spans = spans;
                this.text = text;
                this.startIndex = startIndex;
            }

            void apply(SpannableStringBuilder spanStringBuilder) {
                if (spanStringBuilder == null)
                    return;
                for (ParcelableSpan span : spans) {
                    spanStringBuilder.setSpan(span, startIndex, startIndex + text.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                }
            }
        }

        private List<SpanSection> spanSections;
        private StringBuilder stringBuilder;

        SimpleSpanBuilder() {
            stringBuilder = new StringBuilder();
            spanSections = new ArrayList<>();
        }

        SimpleSpanBuilder append(String text, ParcelableSpan... spans) {
            if (spans != null && spans.length > 0) {
                spanSections.add(new SpanSection(text, stringBuilder.length(), spans));
            }
            stringBuilder.append(text);
            return this;
        }

        SpannableStringBuilder build() {
            SpannableStringBuilder ssb = new SpannableStringBuilder(stringBuilder.toString());
            for (SpanSection section : spanSections) {
                section.apply(ssb);
            }
            return ssb;
        }

        @Override
        public String toString() {
            return stringBuilder.toString();
        }
    }

    public static SpannableStringBuilder textForLabel(String seriesTitle,
                                                      int isCollectedEdition,
                                                      String issueTitle,
                                                      String issueSubtitle,
                                                      int issueNo) {

        SimpleSpanBuilder ssb = new SimpleSpanBuilder();

        if (seriesTitle != null && !seriesTitle.isEmpty())
            ssb.append(seriesTitle, new ForegroundColorSpan(Color.BLACK));

        if (issueNo > 0) {
            if (seriesTitle != null && !seriesTitle.isEmpty())
                ssb.append((isCollectedEdition == 1) ? ", " : " ", new ForegroundColorSpan(Color.BLACK));

            android.content.res.Resources res = ComicvineSearch.getInstance().getResources();
            String format = (isCollectedEdition == 1) ? res.getString(R.string.VolFormat) : res.getString(R.string.IssFormat);
            ssb.append(String.format(format, String.valueOf(issueNo)), new ForegroundColorSpan(Color.BLACK));
        }

        if (seriesTitle != null && !seriesTitle.isEmpty() && ((issueTitle != null && !issueTitle.isEmpty()) || (issueSubtitle != null && !issueSubtitle.isEmpty())))
            ssb.append(": ", new ForegroundColorSpan(Color.BLACK));

        String it = (issueTitle != null) ? issueTitle : "";
        String ist = (issueSubtitle != null) ? issueSubtitle : "";

        if (issueTitle != null && !issueTitle.isEmpty()) {
            if (issueSubtitle != null && !issueSubtitle.isEmpty())
                ssb.append(String.format("%s (%s)", it, ist), new ForegroundColorSpan(Color.LTGRAY));
            else
                ssb.append(String.format("%s", it), new ForegroundColorSpan(Color.LTGRAY));
        } else if (issueSubtitle != null && !issueSubtitle.isEmpty())
            ssb.append(String.format("(%s)", ist), new ForegroundColorSpan(Color.LTGRAY));

        return ssb.build();
    }

    /*
     *@return boolean return true if the application can access the internet
     */
    public static boolean isNetworkAvailable() {

        Context context = ComicvineSearch.getContext();
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    /**
     * Utility routine to display a Toast message.
     *
     * @param id
     */
    public static void showToast(final int id) {

        if (id != 0) {
            // We don't use getString() because we have no guarantee this
            // object is associated with an activity when this is called, and
            // for whatever reason the implementation requires it.
            showToast(ComicvineSearch.getResourceString(id));
        }
    }

    /**
     * Utility routine to display a Toast message.
     *
     * @param message
     */
    public static void showToast(final String message) {

        // Can only display in main thread.
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {

            synchronized (ComicvineSearch.getContext()) {

                Toast.makeText(ComicvineSearch.getContext(), message, Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * ignore comicvine default "not available" or other silly images
     *
     * @param s
     */
    private static Boolean isSillyComicVineImage(String s) {

        String[] array = {

                "male-good-large",
                "question_mark",
                "nononono",
                "1287935-9",
                "1281190-9",
                "photo_not_available",
                "nophoto",
                "blambot",
                "img_broken"
        };

        for (String ss : array) {

            if (s.toLowerCase().contains(ss.toLowerCase()))
                return true;
        }

        return false;
    }
}