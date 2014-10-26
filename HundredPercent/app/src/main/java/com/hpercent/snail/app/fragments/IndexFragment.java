package com.hpercent.snail.app.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.hpercent.snail.app.R;
import com.hpercent.snail.app.activities.ActivityCenterActivity;
import com.hpercent.snail.app.activities.NewsCenterActivity;
import com.hpercent.snail.app.adapters.IndexPagerAdapter;
import com.hpercent.snail.app.adapters.IndustryAdapter;
import com.hpercent.snail.app.controllers.HeaderSearchController;
import com.hpercent.snail.app.models.IndustryModel;
import com.hpercent.snail.app.views.ViewPagerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by koudejian on 14-7-30.
 */
public class IndexFragment extends BaseFragment {

    private HeaderSearchController headerSearchController = null;
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
        adPager = (android.support.v4.view.ViewPager) findViewById(R.id.adPager);
        ArrayList listViews = new ArrayList<View>();
        listViews.add(mInflater.inflate(R.layout.pager_view_ad, null));
        listViews.add(mInflater.inflate(R.layout.pager_view_ad, null));
        listViews.add(mInflater.inflate(R.layout.pager_view_ad, null));
        adPager.setAdapter(new IndexPagerAdapter(listViews));
        adPager.setCurrentItem(0);
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
                if (dialog == null) {
                    View view = mInflater.inflate(R.layout.dialog_industry, null);
                    TextView cancel = (TextView) view.findViewById(R.id.dialog_industry_cancel);
                    TextView confirm = (TextView) view.findViewById(R.id.dialog_industry_confirm);
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.hide();
                        }
                    });
                    confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.hide();
                        }
                    });
                    ListView listView = (ListView) view.findViewById(R.id.industryListView);
                    listView.setAdapter(new IndustryAdapter(mContext, getData()));
                    listView.setDividerHeight(0);
                    dialog = new Dialog(mContext, R.style.Dialog_Fullscreen);
                    dialog.setContentView(view);
                }
                dialog.show();
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

    /**
     * 从接口中获取数据
     *
     * @return
     */
    private List getData() {
        List<IndustryModel> mList = new ArrayList<IndustryModel>();
        IndustryModel industryModel = new IndustryModel();
        industryModel.type = 0;
        industryModel.name = "计算机/互联网/通信/电子";
        mList.add(industryModel);
        industryModel = new IndustryModel();
        industryModel.type = 1;
        industryModel.name = "计算机软件";
        mList.add(industryModel);
        industryModel = new IndustryModel();
        industryModel.type = 1;
        industryModel.name = "计算机硬件";
        mList.add(industryModel);
        industryModel = new IndustryModel();
        industryModel.type = 0;
        industryModel.name = "会计/金融/银行/保险";
        mList.add(industryModel);
        industryModel = new IndustryModel();
        industryModel.type = 1;
        industryModel.name = "会计/审计";
        mList.add(industryModel);
        industryModel = new IndustryModel();
        industryModel.type = 1;
        industryModel.name = "金融/投资/证券";
        mList.add(industryModel);
        return mList;
    }
}
