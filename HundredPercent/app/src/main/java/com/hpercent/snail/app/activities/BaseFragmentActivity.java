/*
 * Copyright (c) 2013. 1010.am
 *
 * You may obtain a copy of the License at
 *
 *      http://1010.am
 */

package com.hpercent.snail.app.activities;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.hpercent.snail.app.R;
import com.hpercent.snail.app.fragments.FooterFragment;
import com.hpercent.snail.app.models.FooterDataModel;

import java.util.List;


/**
 * Created by sunkai on 13-9-2.
 */
public abstract class BaseFragmentActivity extends FragmentActivity implements FooterFragment.FooterClickListner {

    protected abstract List<FooterDataModel> getItemPairs();

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }
}
