package com.hpercent.snail.app.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.hpercent.snail.app.config.JsonConfig;
import com.hpercent.snail.app.config.UrlConfig;
import com.hpercent.snail.app.network.PostDataTask;
import com.hpercent.snail.app.network.PostParameter;
import com.hpercent.snail.app.utils.DeviceUtils;
import com.hpercent.snail.app.views.CustomDialog;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 一般的异步任务，参数由外部传入
 * Created by lyihuang86 on 14-11-03.
 */
public class NormalTask extends BaseTask {
    private PostDataTask mPostDataTask = null;

    public NormalTask(Context context, String url, PostParameter param) {
        super(context);
        mPostDataTask = new PostDataTask(url, param) {
            @Override
            public void dealWithResult(String request) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(request);
                    String status = jsonObject.getString(JsonConfig.KEY_STATUS);
                    if ("0".equals(status)) {//success
                        String data = jsonObject.getString(JsonConfig.KEY_DATA);
                        if (mOnTaskFinishedListener != null) {
                            mOnTaskFinishedListener.onDone(data);
                        }
                    } else {
                        popMessage("登录失败！");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (mCustomDialog != null) {
                    mCustomDialog.cancel();
                }
            }
        };

    }

    @Override
    public void start() {
        if (DeviceUtils.isNetworkConnected()) {
            if (mCustomDialog == null) {
                mCustomDialog = new CustomDialog(mContext, CustomDialog.DIALOG_THEME_WAIT_NOT_CANCEL);
            }
            mCustomDialog.show();
            if (mPostDataTask.getStatus() == AsyncTask.Status.PENDING) {
                mPostDataTask.execute();
            }
        } else {
            popMessage("暂无网络链接!");
        }
    }
}
