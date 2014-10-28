package com.hpercent.snail.app;

import android.app.Application;
import android.content.SharedPreferences;

import com.hpercent.snail.app.config.Config;
import com.hpercent.snail.app.models.UserModel;

/**
 * Created by koudejian on 14-8-26.
 */
public class MainApplication extends Application {
    public static int CURRENT_FRAGMENT = 0;
    private static  MainApplication INSTANCE = null;
    //用户信息
    public static UserModel gUser = new UserModel();

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        initUserInfo();

    }

    public static MainApplication getInstance() {
        return INSTANCE;
    }

    /**
     * 登录信息
     * @param user
     */
    public static void login(UserModel user) {
        gUser = user;
        // 同步存储本地
        SharedPreferences sp = MainApplication.getInstance().getSharedPreferences(Config.PREFS_NAME, 0);
        sp.edit().putString("uid", user.getUid()).commit();
        sp.edit().putString("name", user.getUsername()).commit();
        sp.edit().putString("phone", user.getPhone()).commit();
    }

    /**
     * 退出登录
     */
    public static void logout(){
        gUser.clear();
        // 同步存储本地
        SharedPreferences sp = MainApplication.getInstance().getSharedPreferences(Config.PREFS_NAME, 0);
        sp.edit().putString("uid", gUser.getUid()).commit();
        sp.edit().putString("name", gUser.getUsername()).commit();
        sp.edit().putString("phone", gUser.getPhone()).commit();
    }

    /**
     * 用户信息初始化
     */
    private void initUserInfo() {
        SharedPreferences sp = getSharedPreferences(Config.PREFS_NAME, 0);
        String uid = sp.getString("uid", "0");
        String name = sp.getString("name", "");
        String phone = sp.getString("phone", "");

        this.gUser = new UserModel(uid, name, phone);
    }
}
