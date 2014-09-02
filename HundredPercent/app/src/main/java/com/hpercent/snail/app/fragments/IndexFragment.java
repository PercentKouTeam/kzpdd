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
import com.hpercent.snail.app.controllers.HeaderSearchController;
import com.hpercent.snail.app.views.ViewPagerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koudejian on 14-7-30.
 */
public class IndexFragment extends BaseFragment {

    private HeaderSearchController headerSearchController = null;
    android.support.v4.view.ViewPager adPager = null;
    android.support.v4.view.ViewPager servicePager = null;

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
        headerSearchController = new HeaderSearchController(this.mContext, this.mView);
        adPager = (android.support.v4.view.ViewPager) findViewById(R.id.adPager);
        ArrayList listViews = new ArrayList<View>();
        listViews.add(mInflater.inflate(R.layout.pager_view_ad, null));
        listViews.add(mInflater.inflate(R.layout.pager_view_ad, null));
        listViews.add(mInflater.inflate(R.layout.pager_view_ad, null));
        adPager.setAdapter(new IndexPagerAdapter(listViews));
        adPager.setCurrentItem(0);
        servicePager = (android.support.v4.view.ViewPager) findViewById(R.id.servicePager);
        listViews = new ArrayList<View>();
        listViews.add(mInflater.inflate(R.layout.pager_view_service,null));
        listViews.add(mInflater.inflate(R.layout.pager_view_service,null));
        listViews.add(mInflater.inflate(R.layout.pager_view_service,null));
        servicePager.setAdapter(new IndexPagerAdapter(listViews));
        servicePager.setCurrentItem(0);
    }

}