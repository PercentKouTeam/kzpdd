package com.hpercent.snail.app.fragments.order;

import android.util.TypedValue;

import com.hpercent.snail.app.R;
import com.hpercent.snail.app.controllers.order.OrderTypeController;
import com.hpercent.snail.app.fragments.BaseFragment;

/**
 * Created by koudejian on 14-11-4.
 */
public abstract class BaseOrderFragment extends BaseFragment {

    protected OrderTypeController mOrderTypeController = null;

    public abstract void initView();
    @Override
    public void initViews() {
        //add your own logic here.
        if(mView == null){
            mView = findViewById(R.id.parent);
        }
        mOrderTypeController = new OrderTypeController(mContext, mView);
        initView();
    }

    protected int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

}
