package com.hpercent.snail.app.controllers;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.hpercent.snail.app.R;
import com.hpercent.snail.app.adapters.IndustryAdapter;
import com.hpercent.snail.app.config.UrlConfig;
import com.hpercent.snail.app.listeners.OnTaskFinishedListener;
import com.hpercent.snail.app.models.IndustryModel;
import com.hpercent.snail.app.network.PostParameter;
import com.hpercent.snail.app.tasks.NormalTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取行业分类
 * Created by lyihuang86 on 2014/11/3.
 */
public class IndustryController extends BaseController {

    public Dialog dialog = null;

    public IndustryController(Context context, View view) {
        super(context, view);
    }

    public void init() {
        NormalTask normalTask = new NormalTask(this.mContext, UrlConfig.GET_CATEGORY, new PostParameter());
        normalTask.setOnTaskFinishedListener(new OnTaskFinishedListener() {
            @Override
            public void onDone(String result) {
                List<IndustryModel> mList = new ArrayList<IndustryModel>();
                mList.clear();
                try {
                    JSONArray nodes = new JSONArray(result);
                    int len = nodes.length();
                    for (int i = 0; i < len; i++) {
                        JSONObject node = (JSONObject) nodes.get(i);
                        IndustryModel industryModel = new IndustryModel();
                        industryModel.type = 0;
                        industryModel.name = node.getString("name");
                        mList.add(industryModel);
                        JSONArray child = node.getJSONArray("_child");
                        int childLen = child.length();
                        for (int j = 0; j < childLen; j++) {
                            industryModel = new IndustryModel();
                            industryModel.type = 1;
                            industryModel.name = ((JSONObject) child.get(j)).getString("name");
                            mList.add(industryModel);
                        }
                    }
                    createDialog(mList);
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        });
        normalTask.start();
    }

    private void createDialog(List list) {
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_industry, null);
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
        listView.setAdapter(new IndustryAdapter(mContext, list));
        listView.setDividerHeight(0);
        dialog = new Dialog(mContext, R.style.Dialog_Fullscreen);
        dialog.setContentView(view);
        dialog.show();
    }

}
