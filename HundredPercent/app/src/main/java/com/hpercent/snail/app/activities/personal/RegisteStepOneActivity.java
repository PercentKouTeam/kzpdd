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
import com.hpercent.snail.app.controllers.HeaderLeftBtnController;
import com.hpercent.snail.app.listeners.OnTaskFinishedListener;
import com.hpercent.snail.app.tasks.GetVcodeTask;
import com.hpercent.snail.app.utils.StringUtil;
import com.hpercent.snail.app.views.CusCheckBox;

/**
 * Created by koudejian on 14-10-28.
 */
public class RegisteStepOneActivity extends BaseActivity {
    private View mView = null;
    private Context mContext = null;
    private HeaderLeftBtnController mHeaderLeftBtnController = null;

    private EditText mEtPhone = null;
    private TextView mBtSubmit = null;
    private CusCheckBox mCbAgreement = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_registe);

        mView = findViewById(R.id.parent);
        mContext = this;
        mHeaderLeftBtnController = new HeaderLeftBtnController(this, mView);
        mHeaderLeftBtnController.setTitle("注册");

        mEtPhone = (EditText) findViewById(R.id.phone);
        mBtSubmit = (TextView) findViewById(R.id.submit);
        mCbAgreement = (CusCheckBox) findViewById(R.id.checkbox);
        mBtSubmit.setEnabled(canSubmit());

        mEtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                mBtSubmit.setEnabled(canSubmit());
            }
        });

        mBtSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = mEtPhone.getText().toString().trim();
                submit(phone);
            }
        });

        mCbAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCbAgreement.changeValue();
            }
        });
    }

    private boolean canSubmit() {
        String phone = mEtPhone.getText().toString().trim();
        if (StringUtil.isPhoneNumber(phone) && mCbAgreement.getValue()) {
            return true;
        }
        return false;
    }

    private  void submit(final String phone) {
        GetVcodeTask getVcodeTask = new GetVcodeTask(mContext, phone);
        getVcodeTask.setOnTaskFinishedListener(new OnTaskFinishedListener() {
            @Override
            public void onDone(String result) {
                gotoNext(phone, result);
            }
        });
        getVcodeTask.start();
    }

    private void gotoNext(String phone, String vcode){
        Intent it = new Intent(mContext, RegisteStepTwoActivity.class);
        it.putExtra("phone", phone);
        it.putExtra("vcode", vcode);
        startActivity(it);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!"0".equals(MainApplication.gUser.getUid())){
            finish();
        }
    }
}
