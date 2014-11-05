package com.hpercent.snail.app.adapters.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hpercent.snail.app.R;

import java.util.LinkedList;

/**
 * Created by koudejian on 14-11-6.
 */
public class OrderHandingAdapter extends BaseAdapter {
    private Context mContext = null;
    private LayoutInflater mInflater;
    private LinkedList mList = null;
    public OrderHandingAdapter(Context context, LinkedList list){
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
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_order_handing, null);
            new ViewHolder(convertView);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
//        holder.iv_icon.setImageDrawable(item.loadIcon(getPackageManager()));
//        holder.title.setText("");
        holder.views.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }

    class ViewHolder {
        View views;

        ImageView image;
        TextView title;
        TextView type;
        TextView price;
        TextView percent;

        TextView submit;
        TextView cancel;

        public ViewHolder(View view) {
            views = view.findViewById(R.id.info_rl);

            image = (ImageView) view.findViewById(R.id.image);
            title = (TextView) view.findViewById(R.id.title);
            type = (TextView) view.findViewById(R.id.type);
            price = (TextView) view.findViewById(R.id.price);
            percent = (TextView) view.findViewById(R.id.progress_text);

            submit = (TextView) view.findViewById(R.id.submit);
            cancel = (TextView) view.findViewById(R.id.cancel);
            view.setTag(this);
        }
    }
}
