package com.hpercent.snail.app.controllers;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.hpercent.snail.app.MainApplication;
import com.hpercent.snail.app.R;

/**
 * Created by koudejian on 14-10-28.
 */
public class HeaderLeftBtnController extends BaseController {

    private TextView mTvTitle = null;
    private View mBtBack = null;

    public HeaderLeftBtnController(Context context, View view) {
        super(context, view);
        init();
    }

    public void init() {
        mTvTitle = (TextView) findViewById(R.id.header_title);
        mBtBack = findViewById(R.id.return_btn_rl);
        mBtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainApplication.STACK_FRAGMENT = MainApplication.CURRENT_FRAGMENT;
                ((Activity)mContext).finish();
            }
        });
    }
    public void setTitle(String title){
        if(mTvTitle != null && title != null){
            mTvTitle.setText(title);
        }
    }
}
