package com.hpercent.snail.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hpercent.snail.app.R;

import java.util.List;

/**
 * Created by lyihuang86 on 2014/9/5.
 */
public class NewsCenterAdapter extends android.widget.BaseAdapter {

    private Context mContext = null;
    private LayoutInflater mInflater;
    private List mList = null;

    public NewsCenterAdapter(Context context, List list) {
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
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            Object itemData = getItem(position);
            convertView = mInflater.inflate(R.layout.news_center_item, null);
            //单独对虚线关闭硬件加速，防止虚线变实线
            ImageView imageView = (ImageView) convertView.findViewById(R.id.dottedLine);
            imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        return convertView;
    }
}
