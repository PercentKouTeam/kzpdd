/*
 * Copyright (c) 2013. 1010.am
 *
 * You may obtain a copy of the License at
 *
 *      http://1010.am
 */

package com.hpercent.snail.app.network;

import com.android.volley.Response;

/**
 * Created by sunkai on 13-9-5.
 */
public interface RequestHandler<T> {

    Response.Listener<T> createReqSuccessListener();

    Response.ErrorListener createReqErrorListener();
    
    NetworkFinListener getFinListener();
}
