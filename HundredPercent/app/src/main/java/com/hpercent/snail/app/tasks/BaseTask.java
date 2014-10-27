package com.hpercent.snail.app.tasks;

import android.content.Context;
import android.widget.Toast;

import com.hpercent.snail.app.listeners.OnTaskFinishedListener;
import com.hpercent.snail.app.views.CustomDialog;

/**
 * Created by koudejian on 14-10-28.
 */
public abstract class BaseTask {
    public abstract void start();

    protected Context mContext = null;
    protected CustomDialog mCustomDialog = null;

    protected OnTaskFinishedListener mOnTaskFinishedListener = null;
    public void setOnTaskFinishedListener(OnTaskFinishedListener onTaskFinishedListener){
        mOnTaskFinishedListener = onTaskFinishedListener;
    }
    public BaseTask(Context context){
        mContext = context;
        mCustomDialog = new CustomDialog(mContext, CustomDialog.DIALOG_THEME_WAIT_NOT_CANCEL);
    }
    /**
     * 弹出提示消息框
     * @param msg
     */
    protected void popMessage(String msg){
        if(mContext != null){
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
