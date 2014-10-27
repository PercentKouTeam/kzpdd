package com.hpercent.snail.app.fragments;

import android.content.Intent;
import android.view.View;

import com.hpercent.snail.app.R;
import com.hpercent.snail.app.activities.personal.RegisteStepOneActivity;

/**
 * Created by koudejian on 14-7-30.
 */
public class PersonalFragment extends BaseFragment {

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

        startActivity(new Intent(mContext, RegisteStepOneActivity.class));

    }

}
