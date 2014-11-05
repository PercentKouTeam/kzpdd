package com.hpercent.snail.app.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.Toast;

import com.hpercent.snail.app.MainApplication;
import com.hpercent.snail.app.R;
import com.hpercent.snail.app.fragments.CategoryFragment;
import com.hpercent.snail.app.fragments.FooterFragment;
import com.hpercent.snail.app.fragments.IndexFragment;
import com.hpercent.snail.app.fragments.MoreFragment;
import com.hpercent.snail.app.fragments.PersonalFragment;
import com.hpercent.snail.app.fragments.order.BaseOrderDoneFragment;
import com.hpercent.snail.app.fragments.order.BaseOrderFragment;
import com.hpercent.snail.app.fragments.order.OrderCancelFragment;
import com.hpercent.snail.app.fragments.order.OrderHandingFragment;
import com.hpercent.snail.app.fragments.order.OrderRefundFragment;
import com.hpercent.snail.app.fragments.order.OrderWaitEvaluateFragment;
import com.hpercent.snail.app.fragments.order.OrderWaitHandFragment;
import com.hpercent.snail.app.fragments.order.OrderWaitPayFragment;
import com.hpercent.snail.app.models.FooterDataModel;
import com.umeng.update.UmengUpdateAgent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by koudejian on 14-8-26.
 */
public class MainActivity extends BaseFragmentActivity {

    public static final int ORDER_HANDING = 0;
    public static final int ORDER_WAIT_HAND = 1;
    public static final int ORDER_WAIT_PAY = 2;
    public static final int ORDER_WAIT_EVALUATE = 3;
    public static final int ORDER_DONE = 4;
    public static final int ORDER_REFUND = 5;
    public static final int ORDER_CANCEL = 6;

    private static android.support.v4.app.FragmentManager fm = null;
    private FooterFragment mFooterFragment = null;

    private IndexFragment mIndexFragment = null;
    private CategoryFragment mCategoryFragment = null;
    private PersonalFragment mPersonalFragment = null;
    private MoreFragment mMoreFragment = null;
    private static MainActivity INSTANCE = null;

    private List<BaseOrderFragment> mOrderFragmentList = new ArrayList<BaseOrderFragment>();

    public static MainActivity getInstance(){
        return INSTANCE;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //友盟自动更新api
        UmengUpdateAgent.update(this);
        setContentView(R.layout.activity_main);
        INSTANCE = this;
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        mOrderFragmentList.add(new OrderHandingFragment());
        mOrderFragmentList.add(new OrderWaitHandFragment());
        mOrderFragmentList.add(new OrderWaitPayFragment());
        mOrderFragmentList.add(new OrderWaitEvaluateFragment());
        mOrderFragmentList.add(new BaseOrderDoneFragment());
        mOrderFragmentList.add(new OrderRefundFragment());
        mOrderFragmentList.add(new OrderCancelFragment());

        mIndexFragment = new IndexFragment();
        mCategoryFragment = new CategoryFragment();
        mPersonalFragment = new PersonalFragment();
        mMoreFragment = new MoreFragment();

        mFooterFragment = new FooterFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("footdata", (Serializable) getItemPairs());
        // 向detailFragment传入参数
        mFooterFragment.setArguments(bundle);
        //初始化fragment选中第一项
        ft.add(R.id.fragment_radiogroup, mFooterFragment);
        ft.add(R.id.fragment_view, mIndexFragment);
        mFooterFragment.changeDisplayItem(1);
        ft.commit();
    }

    /**
     * 初始化footer
     * @return
     */
    @Override
    protected List<FooterDataModel> getItemPairs() {
        List<FooterDataModel> itemPairs = new ArrayList<FooterDataModel>();
        itemPairs.add(new FooterDataModel(R.drawable.foot_index_xml, R.drawable.footer_index_selected, "首页", false));
        itemPairs.add(new FooterDataModel(R.drawable.foot_type_xml, R.drawable.footer_type_selected, "分类", false));
        itemPairs.add(new FooterDataModel(R.drawable.foot_order_xml, R.drawable.footer_order_selected, "订单", false));
        itemPairs.add(new FooterDataModel(R.drawable.foot_personal_xml, R.drawable.footer_personal_selected, "我的", false));
        itemPairs.add(new FooterDataModel(R.drawable.foot_more_xml, R.drawable.footer_more_selected, "更多", false));
        return itemPairs;
    }

    public void switchOrder(int position){
        if(MainApplication.gOrderType == position){
            return;
        }
        MainApplication.gOrderType = position;
        mFooterFragment.changeDisplayItem(3);
        FragmentTransaction ftTemp = fm.beginTransaction();
        ftTemp.replace(R.id.fragment_view, mOrderFragmentList.get(MainApplication.gOrderType));
        ftTemp.commit();
    }
    /**
     * 切换fragment
     * @param position
     */
    @Override
    public void onFooterClick(int position) {
        /*
        if(position == 3 && !MainApplication.isLogin()){//订单未登录
            startActivity(new Intent(this, LoginActivity.class));
            MainApplication.STACK_FRAGMENT = position;
            return;
        }
        //*/
        mFooterFragment.changeDisplayItem(position);
        FragmentTransaction ftTemp = fm.beginTransaction();
        MainApplication.CURRENT_FRAGMENT = position;
        MainApplication.STACK_FRAGMENT = position;
        switch (position) {
            case 1:
                ftTemp.replace(R.id.fragment_view, mIndexFragment);
                break;
            case 2:
                ftTemp.replace(R.id.fragment_view, mCategoryFragment);
                break;
            case 3:
                ftTemp.replace(R.id.fragment_view, mOrderFragmentList.get(MainApplication.gOrderType));
                break;
            case 4:
                ftTemp.replace(R.id.fragment_view, mPersonalFragment);
                break;
            case 5:
                ftTemp.replace(R.id.fragment_view, mMoreFragment);
                break;
        }
        ftTemp.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(MainApplication.STACK_FRAGMENT > 0) {
            onFooterClick(MainApplication.STACK_FRAGMENT);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            /*
            if(MainApplication.CURRENT_FRAGMENT == 2){//mCategoryFragment
                if(mCategoryFragment.onBackKeyDown()){//返回true消费该事件
                    return false;
                }
            }
            //*/
            exitBy2Click(); // 调用双击退出函数
        }
        return false;
    }
    /**
     * 双击退出函数
     */
    private Boolean isGoingToExit = false;

    private void exitBy2Click() {
        if (isGoingToExit == false) {
            isGoingToExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isGoingToExit = false; // 取消退出
                }
            }, 2000);   // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        } else {
            finish();
            System.exit(0);
        }
    }
}
