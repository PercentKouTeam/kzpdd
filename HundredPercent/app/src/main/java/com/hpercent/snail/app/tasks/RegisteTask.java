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
 * Created by koudejian on 14-10-28.
 */
public class RegisteTask extends BaseTask{
    private PostDataTask mPostDataTask = null;

    public RegisteTask(Context context, String vcode, String passwd, String name, String idcard, String phone){
        super(context);
        // post 参数
        PostParameter param = new PostParameter();
        param.add("verify", vcode);
        param.add("passWord", passwd);
        param.add("realName", name);
        param.add("idCard", idcard);
        param.add("mobile", phone);
        mPostDataTask = new PostDataTask(UrlConfig.USER_REGISTE, param) {
            @Override
            public void dealWithResult(String request) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(request);
                    String status = jsonObject.getString(JsonConfig.KEY_STATUS);
                    if("0".equals(status)){//success
                        String data = jsonObject.getString(JsonConfig.KEY_DATA);
                        if(mOnTaskFinishedListener != null){
                            mOnTaskFinishedListener.onDone(data);
                        }
                    }else{
                        popMessage("注册失败！");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(mCustomDialog != null){
                    mCustomDialog.cancel();
                }
            }
        };

    }

    @Override
    public void start() {
        if(DeviceUtils.isNetworkConnected()){
            if(mCustomDialog == null){
                mCustomDialog = new CustomDialog(mContext, CustomDialog.DIALOG_THEME_WAIT_NOT_CANCEL);
            }
            mCustomDialog.show();
            if(mPostDataTask.getStatus() == AsyncTask.Status.PENDING){
                mPostDataTask.execute();
            }
        }else{
            popMessage("暂无网络链接!");
        }
    }
}
