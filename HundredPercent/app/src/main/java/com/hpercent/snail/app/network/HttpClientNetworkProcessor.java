package com.hpercent.snail.app.network;

import android.os.AsyncTask;

public class HttpClientNetworkProcessor extends AsyncTask<String, Integer, String> implements NetworkProcessor<String>{

    RequestHandler<String> requestor;

    @Override
    public void process(int method, String url, RequestHandler<String> requestor) {
        this.requestor = requestor;
        this.execute(url);
    }

    /* (non-Javadoc)
     * @see android.os.AsyncTask#doInBackground(Params[])
     */
    @Override
    protected String doInBackground(String... params) {
        HttpClient httpClient;
        try {
            httpClient = new HttpClient(params[0]);
            String json = httpClient.post();
            return json;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPreExecute (){
        //pBar.bringToFront();
    }

    @Override
    protected void onPostExecute (String json){
        this.requestor.getFinListener().finish(json);
    }
}
