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
        sp.edit().putString("oid", user.getOpenid()).commit();
        sp.edit().putString("uid", user.getUid()).commit();
        sp.edit().putString("nick", user.getNick()).commit();
        sp.edit().putString("avatar", user.getAvatar()).commit();
    }

    /**
     * 退出登录
     */
    public static void logout(){
        gUser.clear();
        // 同步存储本地
        SharedPreferences sp = MainApplication.getInstance().getSharedPreferences(Config.PREFS_NAME, 0);
        sp.edit().putString("oid", gUser.getOpenid()).commit();
        sp.edit().putString("uid", gUser.getUid()).commit();
        sp.edit().putString("nick", gUser.getNick()).commit();
        sp.edit().putString("avatar", gUser.getAvatar()).commit();
        sp.edit().putString("history_counts", gUser.getHistoryCounts()).commit();
        sp.edit().putString("favorite_counts", gUser.getFavoriteCounts()).commit();
        sp.edit().putString("comments_counts", gUser.getCommentscounts()).commit();
    }

    /**
     * 用户信息初始化
     */
    private void initUserInfo() {
        SharedPreferences sp = getSharedPreferences(Config.PREFS_NAME, 0);
        String oid = sp.getString("oid", "");
        String uid = sp.getString("uid", "0");
        String nick = sp.getString("nick", "");
        String avatar = sp.getString("avatar", "");

        String historyCounts = sp.getString("history_counts", "0");
        String favoriteCounts = sp.getString("favorite_counts", "0");
        String commentsCounts = sp.getString("comments_counts", "0");
        this.gUser = new UserModel(oid, uid, nick, avatar, historyCounts, favoriteCounts, commentsCounts);
    }
}
