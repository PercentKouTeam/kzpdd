package com.hpercent.snail.app.config;

/**
 * Created by koudejian on 14-7-29.
 * api config
 */
public class UrlConfig {


    private final static String HOST = "http://121.42.24.4/app/";
    private final static String BFB_KEY = "1202d254d511e80909200ae3a761fa2973c71a35";

    /**
     * 获取广告
     */
    public final static String GET_ADVER = HOST + "index/getAdver?bfb_key=" + BFB_KEY + "&limit = 10";
    /**
     * 获取推荐服务
     */
    public final static String GET_RECOMMEND = HOST + "index/getRecommend?bfb_key=" + BFB_KEY + "&limit = 100&cateId=";

}
