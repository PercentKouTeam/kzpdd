package com.hpercent.snail.app.activities.personal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hpercent.snail.app.MainApplication;
import com.hpercent.snail.app.R;
import com.hpercent.snail.app.activities.BaseActivity;
import com.hpercent.snail.app.config.JsonConfig;
import com.hpercent.snail.app.controllers.HeaderLeftBtnController;
import com.hpercent.snail.app.listeners.OnTaskFinishedListener;
import com.hpercent.snail.app.models.UserModel;
import com.hpercent.snail.app.tasks.GetVcodeTask;
import com.hpercent.snail.app.tasks.RegisteTask;
import com.hpercent.snail.app.utils.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by koudejian on 14-10-28.
 */
public class RegisteStepTwoActivity extends BaseActivity {
    private final int COUNT_TIME = 60000;
    private View mView = null;
    private Context mContext = null;
    private HeaderLeftBtnController mHeaderLeftBtnController = null;

    private String mPhone = null;
    private String mVcode = null;

    private TextView mTvHint = null;
    private EditText mEtVcode = null;
    private EditText mEtPasswd = null;
    private EditText mEtName = null;
    private EditText mEtIdcard = null;

    private TextView mBtSubmit = null;
    private TextView mBtResend = null;

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
        @Override
        public void afterTextChanged(Editable s) {
            mBtSubmit.setEnabled(canSubmit());
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_registe_next);

        mView = findViewById(R.id.parent);
        mContext = this;
        mHeaderLeftBtnController = new HeaderLeftBtnController(this, mView);
        mHeaderLeftBtnController.setTitle("注册");

        mTvHint = (TextView) findViewById(R.id.hint);
        Intent it = getIntent();
        mPhone = it.getStringExtra("phone");
        mVcode = it.getStringExtra("vcode");
        mTvHint.setText(getString(R.string.sms_send_hint, mPhone));

        mEtVcode = (EditText) findViewById(R.id.vcode);
        mEtVcode.setText(mVcode);
        mEtPasswd = (EditText) findViewById(R.id.passwd);
        mEtName = (EditText) findViewById(R.id.name);
        mEtIdcard = (EditText) findViewById(R.id.idcard);

        mBtSubmit = (TextView) findViewById(R.id.submit);
        mBtSubmit.setEnabled(canSubmit());

        mEtVcode.addTextChangedListener(mTextWatcher);
        mEtPasswd.addTextChangedListener(mTextWatcher);
        mEtName.addTextChangedListener(mTextWatcher);
        mEtIdcard.addTextChangedListener(mTextWatcher);

        mBtSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vcode = mEtVcode.getText().toString().trim();
                String passwd = mEtPasswd.getText().toString().trim();
                String name = mEtName.getText().toString().trim();
                String idcard = mEtIdcard.getText().toString().trim();
                submit(vcode, passwd, name, idcard);
            }
        });

        //重发验证码逻辑
        mBtResend = (TextView) findViewById(R.id.resend);
        mBtResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetVcodeTask getVcodeTask = new GetVcodeTask(mContext, mPhone);
                getVcodeTask.setOnTaskFinishedListener(new OnTaskFinishedListener() {
                    @Override
                    public void onDone(String result) {
                        popMessage(getString(R.string.sms_send_hint, mPhone));
                        startCountDownTimer();
                    }
                });
                getVcodeTask.start();
            }
        });
        startCountDownTimer();
    }

    private boolean canSubmit() {
        boolean result = false;
        String vcode = mEtVcode.getText().toString().trim();
        String passwd = mEtPasswd.getText().toString().trim();
        String name = mEtName.getText().toString().trim();
        String idcard = mEtIdcard.getText().toString().trim();
        if (StringUtil.isVcode(vcode) && StringUtil.isPassword(passwd) && !StringUtil.isEmpty(name) && StringUtil.isIdCard(idcard)) {
            result = true;
        }
        return result;
    }


    private  void submit(String vcode, String passwd, String name, String idcard) {
        RegisteTask mRegisteTask = new RegisteTask(mContext, vcode, passwd, name, idcard, mPhone);
        mRegisteTask.setOnTaskFinishedListener(new OnTaskFinishedListener() {
            @Override
            public void onDone(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    String uid = obj.getString(JsonConfig.KEY_USER_UID);
                    String name = obj.getString(JsonConfig.KEY_USER_NAME);
                    String phone = obj.getString(JsonConfig.KEY_USER_PHONE);
                    MainApplication.login(new UserModel(uid, name, phone));
                    RegisteStepTwoActivity.this.finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        mRegisteTask.start();
    }

    /**
     * 格式化字符串
     * @param resId
     * @param value
     * @return
     */
    private String getString(int resId, int value){
        String tempFormat = getResources().getString(resId);
        return String.format(tempFormat, value);
    }
    private void startCountDownTimer(){
        mBtResend.setEnabled(false);
        new CountDownTimer(COUNT_TIME, 1000) {
            public void onTick(long millisUntilFinished) {
                mBtResend.setText(getString(R.string.resend, millisUntilFinished / 1000));
            }
            public void onFinish() {
                mBtResend.setText("重新获取");
                mBtResend.setEnabled(true);
            }
        }.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!"0".equals(MainApplication.gUser.getUid())){
            finish();
        }
    }
}
