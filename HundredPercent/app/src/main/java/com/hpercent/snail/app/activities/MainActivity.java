package com.hpercent.snail.app.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.Toast;

import com.hpercent.snail.app.MainApplication;
import com.hpercent.snail.app.R;
import com.hpercent.snail.app.fragments.CategoryFragment;
import com.hpercent.snail.app.fragments.FooterFragment;
import com.hpercent.snail.app.fragments.IndexFragment;
import com.hpercent.snail.app.fragments.MoreFragment;
import com.hpercent.snail.app.fragments.OrderFragment;
import com.hpercent.snail.app.fragments.PersonalFragment;
import com.hpercent.snail.app.models.FooterDataModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by koudejian on 14-8-26.
 */
public class MainActivity extends BaseFragmentActivity {

    private static android.support.v4.app.FragmentManager fm = null;
    private FooterFragment mFooterFragment = null;

    private IndexFragment mIndexFragment = null;
    private CategoryFragment mCategoryFragment = null;
    private OrderFragment mOrderFragment = null;
    private PersonalFragment mPersonalFragment = null;
    private MoreFragment mMoreFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        mIndexFragment = new IndexFragment();
        mCategoryFragment = new CategoryFragment();
        mOrderFragment = new OrderFragment();
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
        itemPairs.add(new FooterDataModel(R.drawable.foot_index_xml, R.drawable.footer_index_selected, "分类", false));
        itemPairs.add(new FooterDataModel(R.drawable.foot_index_xml, R.drawable.footer_index_selected, "订单", false));
        itemPairs.add(new FooterDataModel(R.drawable.foot_index_xml, R.drawable.footer_index_selected, "我的", false));
        itemPairs.add(new FooterDataModel(R.drawable.foot_index_xml, R.drawable.footer_index_selected, "更多", false));
        return itemPairs;
    }

    /**
     * 切换fragment
     * @param position
     */
    @Override
    public void onFooterClick(int position) {
        mFooterFragment.changeDisplayItem(position);
        FragmentTransaction ftTemp = fm.beginTransaction();
        MainApplication.CURRENT_FRAGMENT = position;
        switch (position) {
            case 1:
                ftTemp.replace(R.id.fragment_view, mIndexFragment);
                break;
            case 2:
                ftTemp.replace(R.id.fragment_view, mCategoryFragment);
                break;
            case 3:
                ftTemp.replace(R.id.fragment_view, mOrderFragment);
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
