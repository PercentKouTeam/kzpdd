package com.hpercent.snail.app.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hpercent.snail.app.R;
import com.hpercent.snail.app.models.IndustryModel;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by lyihuang86 on 14-9-02.
 * templates of Adapter.
 */
public class IndustryAdapter extends android.widget.BaseAdapter {
    private Context mContext = null;
    private LayoutInflater mInflater;
    private List<IndustryModel> mList = null;

    public IndustryAdapter(Context context, List<IndustryModel> list) {
        mContext = context;
        mList = list;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public IndustryModel getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            IndustryModel itemData = getItem(position);
            if(itemData.type == 0){
                convertView = mInflater.inflate(R.layout.industry_item_parent, null);
            }else{
                convertView = mInflater.inflate(R.layout.industry_item_child, null);
            }
            TextView mTextView = (TextView)convertView.findViewById(R.id.title);
            //Log.v("ss", itemData.name);
            mTextView.setText(itemData.name);
        }
        return convertView;
    }
}
