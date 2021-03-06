package com.hpercent.snail.app.network;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class PostParameter {
    public List <NameValuePair> params = null;

    public PostParameter(){
        this.params = new ArrayList<NameValuePair>();
    }
    public void add(String key,String value){
        if(key!=null&&key.length()!=0){        //key is not null
            this.params.add(new BasicNameValuePair(key,value));
        }
    }
    public List <NameValuePair> getParas(){
        return this.params;
    }
}
