package com.hpercent.snail.app.activities;

import android.os.Bundle;
import android.widget.ListView;

import com.hpercent.snail.app.R;
import com.hpercent.snail.app.adapters.ActivityCenterAdapter;
import com.hpercent.snail.app.adapters.NewsCenterAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyihuang86 on 2014/9/4.
 */
public class NewsCenterActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_center);
        ListView listView = (ListView) findViewById(R.id.newsListView);
        listView.setAdapter(new NewsCenterAdapter(this, getData()));
        listView.setDividerHeight(0);
    }

    private List getData() {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        return list;
    }
}
