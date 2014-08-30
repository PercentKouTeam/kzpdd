package com.hpercent.snail.app.fragments;

import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.hpercent.snail.app.R;
import com.hpercent.snail.app.adapters.IndexPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koudejian on 14-7-30.
 */
public class IndexFragment extends BaseFragment {

    /**
     * load layout for different fragment.
     *
     * @return View
     */
    @Override
    public View setLayout() {
        return mInflater.inflate(R.layout.fragment_index, mContainer, false);
    }

    @Override
    public void initViews() {
    }

}