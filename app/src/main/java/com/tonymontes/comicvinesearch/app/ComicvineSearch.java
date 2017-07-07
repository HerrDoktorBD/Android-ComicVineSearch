
package com.tonymontes.comicvinesearch.app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/*
 * Created by tony on 3/4/17.
 */

@SuppressLint("StaticFieldLeak")
public class ComicvineSearch extends Application {

    private static ComicvineSearch sInstance;
    private static Context mContext;

    @Override
    public void onCreate() {

        super.onCreate();

        sInstance = this;
        mContext = this;
    }

    /**
     * Wrapper to reduce explicit use of the 'context' member.
     *
     * @param resId Resource ID
     * @return Localized resource string
     */
    public static String getResourceString(int resId) {
        return mContext.getString(resId);
    }

    public static ComicvineSearch getInstance() {
        return sInstance;
    }

    public static Context getContext() {
        return mContext;
    }
}
