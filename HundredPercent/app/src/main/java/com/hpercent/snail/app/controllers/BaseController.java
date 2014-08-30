package com.hpercent.snail.app.controllers;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * Created by koudejian on 14-3-27.
 */
abstract public class BaseController {

    public Context mContext = null;
    public View mView = null;

    public BaseController(Context context, View view) {
        mContext = context;
        mView = view;
        init();
    }

    /**
     * init view and base logic.
     */
    abstract void init();

    public View findViewById(int resId){
        return (mView == null) ? null : mView.findViewById(resId);
    }

    public void popMessage(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
