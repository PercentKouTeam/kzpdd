package com.hpercent.snail.app.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.hpercent.snail.app.R;
import com.hpercent.snail.app.activities.index.ActivityCenterActivity;
import com.hpercent.snail.app.activities.index.NewsCenterActivity;
import com.hpercent.snail.app.adapters.IndexPagerAdapter;
import com.hpercent.snail.app.controllers.AdvertisementController;
import com.hpercent.snail.app.controllers.IndustryController;
import com.hpercent.snail.app.controllers.HeaderSearchController;

import java.util.ArrayList;

/**
 * Created by koudejian on 14-7-30.
 */
public class IndexFragment extends BaseFragment {

    private HeaderSearchController headerSearchController = null;
    private IndustryController categoryController = null;
    private AdvertisementController advertisementController = null;
    private android.support.v4.view.ViewPager adPager = null;
    private android.support.v4.view.ViewPager servicePager = null;
    private Dialog dialog = null;

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
        //头部搜索
        headerSearchController = new HeaderSearchController(this.mContext, this.mView);
        //广告
        advertisementController = new AdvertisementController(this.mContext, this.mView);
        ArrayList listViews = new ArrayList<View>();
        /*adPager = (android.support.v4.view.ViewPager) findViewById(R.id.adPager);
        listViews.add(mInflater.inflate(R.layout.pager_view_ad, null));
        listViews.add(mInflater.inflate(R.layout.pager_view_ad, null));
        listViews.add(mInflater.inflate(R.layout.pager_view_ad, null));
        adPager.setAdapter(new IndexPagerAdapter(listViews));
        adPager.setCurrentItem(0);*/
        //推荐服务
        servicePager = (android.support.v4.view.ViewPager) findViewById(R.id.servicePager);
        listViews = new ArrayList<View>();
        listViews.add(mInflater.inflate(R.layout.pager_view_service, null));
        listViews.add(mInflater.inflate(R.layout.pager_view_service, null));
        listViews.add(mInflater.inflate(R.layout.pager_view_service, null));
        servicePager.setAdapter(new IndexPagerAdapter(listViews));
        servicePager.setCurrentItem(0);
        bindClick();

    }

    /**
     * 首页中间绑定click事件
     */
    private void bindClick() {
        //切换行业
        RelativeLayout changeIndustry = (RelativeLayout) findViewById(R.id.changeIndustryLayout);
        changeIndustry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (categoryController == null) {
                    Log.v("lyihuang86", "呵呵");
                    categoryController = new IndustryController(mContext, v);
                } else {
                    Log.v("lyihuang86", "哈哈");
                    categoryController.dialog.show();
                }
            }
        });
        //活动中心
        RelativeLayout activityCenter = (RelativeLayout) findViewById(R.id.activityCenterLayout);
        activityCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, ActivityCenterActivity.class);
                startActivity(intent);
            }
        });
        //新闻资讯
        RelativeLayout newsCenter = (RelativeLayout) findViewById(R.id.newsCenterLayout);
        newsCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, NewsCenterActivity.class);
                startActivity(intent);
            }
        });
    }
}
