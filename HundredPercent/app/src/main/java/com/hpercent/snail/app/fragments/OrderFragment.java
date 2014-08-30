package com.hpercent.snail.app.fragments;

import android.view.View;

import com.hpercent.snail.app.R;

/**
 * Created by koudejian on 14-7-30.
 */
public class OrderFragment extends BaseFragment {

    /**
     * load layout for different fragment.
     * @return View
     */
    @Override
    public View setLayout() {
        return mInflater.inflate(R.layout.fragment_order, mContainer, false);
    }

    @Override
    public void initViews() {
        //add your own logic here.
    }
}
