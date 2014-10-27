package com.hpercent.snail.app.network;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;

import com.hpercent.snail.app.config.UrlConfig;
import com.hpercent.snail.app.utils.LogUtil;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.List;


@TargetApi(Build.VERSION_CODES.CUPCAKE)
public abstract class PostDataTask extends AsyncTask<Void, Void, Void> {
    private String results = "";
    private String url = "";
    private PostParameter param = null;

    public PostDataTask(String urls, PostParameter params) {
        // TODO Auto-generated constructor stub
        this.url = urls;
        this.param = params;
        // this.param.add("token", UserVerification.getUserIdentityStr(url));
    }

    @Override
    protected Void doInBackground(Void... params) {
        postData(url, param.getParas());
        return null;
    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        param.add(UrlConfig.BFB_KEY, UrlConfig.BFB_KEY_VALUE);

        String result = "";
        for (int i = 0; i < param.getParas().size(); i++) {
            result += "&" + param.getParas().get(i);
        }

        LogUtil.d("url-post", url + result);
        this.results = "";
    }

    @Override
    protected void onPostExecute(Void result) {
        if (results == null) {
            results = "";
        }
        dealWithResult(this.results);
    }

    public void postData(String url, List<NameValuePair> params) {
        String destUrl = url;
        DefaultHttpClient client = new DefaultHttpClient();
        HttpConnectionParams.setConnectionTimeout(client.getParams(), 5000);
        HttpConnectionParams.setSoTimeout(client.getParams(), 5000);
        HttpEntityEnclosingRequestBase httpRequest = new HttpPost(destUrl);
        try {
            httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            // execute the post and get the response from servers
            HttpResponse httpResponse = client.execute(httpRequest);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                String strResult = EntityUtils.toString(httpResponse.getEntity());
                LogUtil.d("tests-httppost", strResult);
                this.results = strResult;
            } else {
                LogUtil.d("tests-httppost", "Error Response" + httpResponse.toString());
                LogUtil.d("tests-httppost", "Error Response" + httpResponse.getStatusLine().toString());
            }
        } catch (Exception e) {
            LogUtil.d("tests-httppost", "error occurs");
        }
    }

    public abstract void dealWithResult(String request);
}