package com.hpercent.snail.app.fragments;

import android.view.View;
import android.widget.ListView;

import com.hpercent.snail.app.R;
import com.hpercent.snail.app.adapters.CategoryAdapter;
import com.hpercent.snail.app.models.CategoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koudejian on 14-7-30.
 */
public class CategoryFragment extends BaseFragment {

    private ListView mListView = null;
    private List<CategoryModel> mList = null;
    private CategoryAdapter mAdapter = null;

    /**
     * load layout for different fragment.
     * @return View
     */
    @Override
    public View setLayout() {
        return mInflater.inflate(R.layout.fragment_category, mContainer, false);
    }

    @Override
    public void initViews() {
        //add your own logic here.
        mList = new ArrayList<CategoryModel>();
        mList.add(new CategoryModel(R.drawable.category_registe, "工商注册"));
        mList.add(new CategoryModel(R.drawable.category_audit, "验资审计"));
        mList.add(new CategoryModel(R.drawable.category_property, "知识产权"));
        mList.add(new CategoryModel(R.drawable.category_loan, "融资贷款"));
        mList.add(new CategoryModel(R.drawable.category_web, "网站建设"));
        mList.add(new CategoryModel(R.drawable.category_law, "法律服务"));


        mListView = (ListView) findViewById(R.id.listview);

        mAdapter = new CategoryAdapter(mContext, mList, mListView);

        mListView.setAdapter(mAdapter);


    }
}
