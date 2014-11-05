package com.hpercent.snail.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hpercent.snail.app.R;
import com.hpercent.snail.app.models.OrderMenuModel;

import java.util.List;

/**
 * Created by koudejian on 14-11-4.
 */
public class OrderTypeAdapter extends BaseAdapter {
    private Context mContext = null;
    private LayoutInflater mInflater;
    private List<OrderMenuModel> mList= null;
    public OrderTypeAdapter(Context context, List list){
        mContext = context;
        mList = list;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return (mList == null) ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item_order_type, null);
        OrderMenuModel orderMenuModel = mList.get(position);

        TextView name = (TextView) view.findViewById(R.id.name);
        TextView num = (TextView) view.findViewById(R.id.num);

        name.setText(orderMenuModel.getName());
        num.setText(orderMenuModel.getNumText());

        return view;
    }
}
