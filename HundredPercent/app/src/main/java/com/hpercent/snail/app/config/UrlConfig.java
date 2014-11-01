package com.hpercent.snail.app.config;

/**
 * Created by koudejian on 14-7-29.
 * api config
 */
public class UrlConfig {


    private final static String HOST = "http://121.42.24.4/app/";
    //this parameter should be add when every http request.
    public final static String BFB_KEY_VALUE = "1202d254d511e80909200ae3a761fa2973c71a35";
    public final static String BFB_KEY = "bfb_key";
    /**
     * 获取广告
     */
    public final static String GET_ADVER = HOST + "index/getAdver?limit=10";
    /**
     * 获取推荐服务
     */
    public final static String GET_RECOMMEND = HOST + "index/getRecommend?limit = 100&cateId=";

    /**
     * 注册获取验证码
     */
    public final static String GET_VCODE_FOR_REGISTE = HOST + "account/getVerifyForRegister";

    /**
     * 注册
     */
    public final static String USER_REGISTE = HOST + "app/account/register";

    /**
     * 登录
     */
    public final static String USER_LOGIN = HOST + "account/login";
}
