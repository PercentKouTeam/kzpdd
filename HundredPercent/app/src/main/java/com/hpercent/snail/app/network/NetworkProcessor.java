/*
 * Copyright (c) 2013. 1010.am
 *
 * You may obtain a copy of the License at
 *
 *      http://1010.am
 */

package com.hpercent.snail.app.network;

/**
 * Created by sunkai on 13-9-5.
 */
public interface NetworkProcessor<T> {

    void process(int method, String url, RequestHandler<T> requestor);


}
