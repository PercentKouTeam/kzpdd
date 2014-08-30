/*
 * Copyright (c) 2013. 1010.am
 *
 * You may obtain a copy of the License at
 *
 *      http://1010.am
 */

package com.hpercent.snail.app.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.hpercent.snail.app.MainApplication;

/**
 * 设备尺寸换算&&网络状态获取
 * Created by koudejian on 14-8-26.
 */
public class DeviceUtils {
    /**
     * 屏幕dip与px换算
     * @param dpValue
     * @return
     */
    public static int dip2px(float dpValue) {
        final float scale = MainApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(float pxValue) {
        final float scale = MainApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取屏幕尺寸
     * @return
     */
    public static int deviceWidth() {
        return MainApplication.getInstance().getResources().getDisplayMetrics().widthPixels;
    }

    public static int deviceHeight() {
        return MainApplication.getInstance().getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 网络状态
     * @return
     */
    public static boolean isNetworkConnected() {
        if (MainApplication.getInstance() != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) MainApplication.getInstance()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

}
