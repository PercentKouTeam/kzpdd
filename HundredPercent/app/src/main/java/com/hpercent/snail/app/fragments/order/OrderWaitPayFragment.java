package com.hpercent.snail.app.fragments.order;

import android.view.View;

import com.hpercent.snail.app.R;
import com.hpercent.snail.app.activities.MainActivity;

/**
 * Created by koudejian on 14-9-30.
 */
public class OrderWaitPayFragment extends BaseOrderFragment {

    /**
     * load layout for different fragment.
     * @return View
     */
    @Override
    public View setLayout() {
        return mInflater.inflate(R.layout.fragment_order_wait_pay, mContainer, false);
    }

    @Override
    public void initView() {
        mOrderTypeController.changeItem(MainActivity.ORDER_WAIT_PAY);
    }

}
