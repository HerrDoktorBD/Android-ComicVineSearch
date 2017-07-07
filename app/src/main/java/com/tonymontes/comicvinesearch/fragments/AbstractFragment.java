
package com.tonymontes.comicvinesearch.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.tonymontes.comicvinesearch.R;
import com.tonymontes.comicvinesearch.listeners.OnFragmentInteractionListener;

import eu.davidea.flexibleadapter.common.SmoothScrollLinearLayoutManager;

public abstract class AbstractFragment
    extends
        Fragment {

    public static final String TAG = AbstractFragment.class.getSimpleName();

    protected OnFragmentInteractionListener mListener;

    protected RecyclerView mRecyclerView;
    protected FloatingActionButton mFab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof OnFragmentInteractionListener)
            mListener = (OnFragmentInteractionListener) activity;
        else
            throw new RuntimeException(activity.toString() + " must implement OnFragmentInteractionListener");
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
    }

    protected LinearLayoutManager createNewLinearLayoutManager() {
        return new SmoothScrollLinearLayoutManager(getActivity());
    }

    @CallSuper
    public void showNewLayoutInfo(final MenuItem item) {

        item.setEnabled(false);
        mRecyclerView.postDelayed(() -> item.setEnabled(true), 1000L);
    }
}