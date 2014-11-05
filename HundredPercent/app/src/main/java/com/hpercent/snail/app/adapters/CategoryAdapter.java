package com.hpercent.snail.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hpercent.snail.app.R;
import com.hpercent.snail.app.models.CategoryModel;

import java.util.List;

/**
 * Created by koudejian on 14-11-6.
 */
public class CategoryAdapter extends BaseAdapter {
    private Context mContext = null;
    private LayoutInflater mInflater;
    private List mList = null;
    private ListView mListView = null;
    public CategoryAdapter(Context context, List list, ListView listView){
        mContext = context;
        mList = list;
        mListView = listView;
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
        View view = mInflater.inflate(R.layout.item_category_title, null);
        View info_rl = view.findViewById(R.id.info_rl);
        CategoryModel categoryModel = (CategoryModel) mList.get(position);
//        final View sub_info_rl = view.findViewById(R.id.sub_info_rl);
        ImageView imageView = (ImageView) view.findViewById(R.id.icon);
        TextView textView = (TextView) view.findViewById(R.id.title);
        textView.setText(categoryModel.getText());
        imageView.setBackground(mContext.getResources().getDrawable(categoryModel.getDrawable()));
        info_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(sub_info_rl.getVisibility() == View.VISIBLE){
//                    sub_info_rl.setVisibility(View.GONE);
//                }else{
//                    sub_info_rl.setVisibility(View.VISIBLE);
//                }
            }
        });
        return view;
    }
}
