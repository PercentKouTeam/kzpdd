package com.hpercent.snail.app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by koudejian on 14-8-26.
 *
 */
public abstract class BaseFragment extends Fragment {

    protected LayoutInflater mInflater = null;
    protected ViewGroup mContainer = null;

    protected View mView = null;
    protected Context mContext = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        mContainer = container;

        mView  = setLayout();
        mContext = mView.getContext();
        initViews();
        return mView;
    }

    public abstract View setLayout();
    public abstract void initViews();

    /**
     * findViewById();
     * @param resId
     * @return
     */
    public View findViewById(int resId){
        View view = null;
        if(mView != null){
            view = mView.findViewById(resId);
        }
        return view;
    }
}
