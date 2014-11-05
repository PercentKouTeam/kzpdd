package com.hpercent.snail.app.controllers;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.hpercent.snail.app.R;

/**
 * Created by koudejian on 14-8-30.
 */
public class HeaderOrderController extends BaseController {
    private TextView mTvTitle = null;
    private View mHeader = null;
    public HeaderOrderController(Context context, View view) {
        super(context, view);
        init();
    }
    public void init() {
        mTvTitle = (TextView) findViewById(R.id.header_title);
        mHeader = findViewById(R.id.header_order_view);
    }
    public void setOnHeaderClickedListener(View.OnClickListener onClickListener){
        if(mHeader != null){
            mHeader.setOnClickListener(onClickListener);
        }
    }
    public void setTitle(String title){
        if(mTvTitle != null){
            mTvTitle.setText(title);
        }
    }

}
