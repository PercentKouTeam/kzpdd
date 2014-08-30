package com.hpercent.snail.app.fragments;

import android.view.View;

import com.hpercent.snail.app.R;

/**
 * Created by koudejian on 14-7-30.
 */
public class MoreFragment extends BaseFragment {

    /**
     * load layout for different fragment.
     * @return View
     */
    @Override
    public View setLayout() {
        return mInflater.inflate(R.layout.fragment_more, mContainer, false);
    }

    @Override
    public void initViews() {
        //add your own logic here.
    }
}
