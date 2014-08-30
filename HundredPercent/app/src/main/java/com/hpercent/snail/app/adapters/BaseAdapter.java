package com.hpercent.snail.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hpercent.snail.app.R;

import java.util.LinkedList;

/**
 * Created by koudejian on 14-8-26.
 * templates of Adapter.
 */
public class BaseAdapter extends android.widget.BaseAdapter {
    private Context mContext = null;
    private LayoutInflater mInflater;
    private LinkedList mList = null;
    public BaseAdapter(Context context, LinkedList list ){
        mContext = context;
        mList = list;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        if(mList != null){
            return mList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.footer_item, null);

        return null;
    }
}
