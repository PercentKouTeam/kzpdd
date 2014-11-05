package com.hpercent.snail.app.controllers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.hpercent.snail.app.R;
import com.hpercent.snail.app.adapters.IndexPagerAdapter;
import com.hpercent.snail.app.config.UrlConfig;
import com.hpercent.snail.app.helper.bitmap.AsyncBitmapLoader;
import com.hpercent.snail.app.helper.bitmap.model.ImageModel;
import com.hpercent.snail.app.listeners.OnTaskFinishedListener;
import com.hpercent.snail.app.network.PostParameter;
import com.hpercent.snail.app.tasks.NormalTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 滚动广告
 * Created by lyihuang86 on 2014/11/3.
 */
public class AdvertisementController extends BaseController {

    public AdvertisementController(Context context, View view) {
        super(context, view);
    }

    public void init() {
        NormalTask normalTask = new NormalTask(mContext, UrlConfig.GET_ADVER, new PostParameter());
        normalTask.setOnTaskFinishedListener(new OnTaskFinishedListener() {
            @Override
            public void onDone(String result) {
                LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
                android.support.v4.view.ViewPager adViewPager = (android.support.v4.view.ViewPager) ((Activity) mContext).findViewById(R.id.adPager);
                try {
                    JSONArray nodes = new JSONArray(result);
                    ArrayList listViews = new ArrayList<View>();
                    int len = nodes.length();
                    for (int i = 0; i < len; i++) {
                        View view = inflater.inflate(R.layout.pager_view_ad, null);
                        ImageView imageView = (ImageView) view.findViewById(R.id.ad);
                        JSONObject node = (JSONObject) nodes.get(i);
                        AsyncBitmapLoader asyncBitmapLoader = new AsyncBitmapLoader(new ImageModel(node.getString("litpic")), imageView);
                        asyncBitmapLoader.start();
                        listViews.add(view);
                    }
                    adViewPager.setAdapter(new IndexPagerAdapter(listViews));
                    adViewPager.setCurrentItem(0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        normalTask.start();
    }
}
