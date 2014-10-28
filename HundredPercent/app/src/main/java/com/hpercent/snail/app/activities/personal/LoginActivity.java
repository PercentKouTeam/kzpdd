package com.hpercent.snail.app.activities.personal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.hpercent.snail.app.tasks.LoginTask;
import com.hpercent.snail.app.utils.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by koudejian on 14-10-29.
 */
public class LoginActivity extends BaseActivity {
    private View mView = null;
    private Context mContext = null;
    private HeaderLeftBtnController mHeaderLeftBtnController = null;

    private TextView mTvForgetPassword = null;
    private TextView mTvRegiste = null;

    private EditText mEtAccout = null;
    private EditText mEtPassword = null;

    private View mBtSubmit = null;

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

        setContentView(R.layout.activity_login);

        mView = findViewById(R.id.parent);
        mContext = this;
        mHeaderLeftBtnController = new HeaderLeftBtnController(this, mView);
        mHeaderLeftBtnController.setTitle("登录");

        mEtAccout = (EditText) findViewById(R.id.accout);
        mEtPassword = (EditText) findViewById(R.id.passwd);
        mEtAccout.addTextChangedListener(mTextWatcher);
        mEtPassword.addTextChangedListener(mTextWatcher);

        mBtSubmit = findViewById(R.id.submit_rl);
        mBtSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accout = mEtAccout.getText().toString().trim();
                String passwd = mEtPassword.getText().toString().trim();
                submit(accout, passwd);
            }
        });

        mTvForgetPassword = (TextView) findViewById(R.id.forget_pass);
        mTvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mTvRegiste = (TextView) findViewById(R.id.registe);
        mTvRegiste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisteStepOneActivity.class));
            }
        });
    }
    private boolean canSubmit() {
        boolean result = false;
        String accout = mEtAccout.getText().toString().trim();
        String passwd = mEtPassword.getText().toString().trim();
        if (StringUtil.isPassword(passwd) && !StringUtil.isEmpty(accout)) {
            result = true;
        }
        return result;
    }
    private void submit(String accout, String passwd) {
        LoginTask loginTask = new LoginTask(mContext, accout, passwd);
        loginTask.setOnTaskFinishedListener(new OnTaskFinishedListener() {
            @Override
            public void onDone(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    String uid = obj.getString(JsonConfig.KEY_USER_UID);
                    String name = obj.getString(JsonConfig.KEY_USER_NAME);
                    String phone = obj.getString(JsonConfig.KEY_USER_PHONE);
                    MainApplication.login(new UserModel(uid, name, phone));
                    LoginActivity.this.finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        loginTask.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        mBtSubmit.setEnabled(canSubmit());
    }
}
