/*
 * Copyright (c) 2013. 1010.am
 *
 * You may obtain a copy of the License at
 *
 *      http://1010.am
 */
package com.hpercent.snail.app.network;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.hpercent.snail.app.config.UrlConfig;

//import one010.am.snail.b2b.utils.UserVerification;

/**
 * @author sukani
 * 
 */
public class NetworkHandler implements RequestHandler<String> {

    NetworkProcessor<String> processor = new HttpClientNetworkProcessor();

    NetworkFinListener listener;
    private String url;

    public NetworkHandler(NetworkFinListener context) {
        this.listener = context;
    }

    public void execute(String url) {
        url += "&" + UrlConfig.BFB_KEY + "=" + UrlConfig.BFB_KEY_VALUE;
        this.url = url;
//        if (DeviceUtils.isNetworkConnected(MainApplication.getInstance())) {
            processor.process(Request.Method.GET, url, this);
//        } else {
//            String json = NetJsonFile.read(url.substring(41));
//            if (StringUtil.isEmpty(json)) {
//                listener.error("无网络");
//            } else {
//                listener.finish(json);
//            }
//        }
    }

    @Override
    public Response.Listener<String> createReqSuccessListener() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
//                    System.out.println("================================");
//                    NetJsonFile.save(url.substring(41), response);
                    if(response == null){
                        response = "";
                    }
                    listener.finish(response.toString());

                } catch (Exception e) {
                    listener.error(e.getMessage());
                }
            }
        };

    }

    @Override
    public Response.ErrorListener createReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.error(error.getMessage());
            }
        };
    }

    @Override
    public NetworkFinListener getFinListener() {
        return this.listener;
    }

}
