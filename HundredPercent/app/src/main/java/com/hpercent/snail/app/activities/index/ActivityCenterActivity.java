package com.hpercent.snail.app.activities.index;

import android.os.Bundle;
import android.widget.ListView;

import com.hpercent.snail.app.R;
import com.hpercent.snail.app.activities.BaseActivity;
import com.hpercent.snail.app.adapters.ActivityCenterAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyihuang86 on 2014/9/4.
 */
public class ActivityCenterActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);
        ListView listView = (ListView) findViewById(R.id.activityListView);
        listView.setAdapter(new ActivityCenterAdapter(this, getData()));
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
