package com.hpercent.snail.app.fragments;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.hpercent.snail.app.MainApplication;
import com.hpercent.snail.app.R;
import com.hpercent.snail.app.activities.personal.LoginActivity;

/**
 * Created by koudejian on 14-7-30.
 */
public class PersonalFragment extends BaseFragment implements View.OnClickListener{

    private View mBtNotice = null;

    private View mBtPublish = null;
    private View mBtFavorite = null;
    private View mBtComplaint = null;
    private View mBtContact = null;
    private View mBtLogout = null;

    private TextView mTvCount = null;
    /**
     * load layout for different fragment.
     * @return View
     */
    @Override
    public View setLayout() {
        return mInflater.inflate(R.layout.fragment_personal, mContainer, false);
    }

    @Override
    public void initViews() {
        //add your own logic here.

        mBtNotice = findViewById(R.id.notice_rl);
        mBtPublish = findViewById(R.id.publish_rl);
        mBtFavorite = findViewById(R.id.favorite_rl);
        mBtComplaint = findViewById(R.id.complaint_rl);
        mBtContact = findViewById(R.id.contact_rl);
        mBtLogout = findViewById(R.id.logout_rl);

        mBtNotice.setOnClickListener(this);
        mBtPublish.setOnClickListener(this);
        mBtFavorite.setOnClickListener(this);
        mBtComplaint.setOnClickListener(this);
        mBtContact.setOnClickListener(this);
        mBtLogout.setOnClickListener(this);

        mTvCount = (TextView) findViewById(R.id.count);

    }

    public void updateUI(){
        if(MainApplication.isLogin()){
            mBtLogout.setVisibility(View.VISIBLE);
        }else {
            mBtNotice.setVisibility(View.INVISIBLE);
            mBtLogout.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onResume() {
        updateUI();
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        if(v == mBtLogout){
            MainApplication.logout();
            updateUI();
            return;
        }
        if(!MainApplication.isLogin()){
            startActivity(new Intent(getActivity(), LoginActivity.class));
            return;
        }
        if(v == mBtPublish){

        }
        else if(v == mBtFavorite){

        }
        else if(v == mBtComplaint){

        }
        else if(v == mBtContact){

        }
        else if(v == mBtNotice){

        }

    }
}
