package com.hpercent.snail.app.fragments.order;

import android.view.View;

import com.hpercent.snail.app.R;
import com.hpercent.snail.app.activities.MainActivity;
import com.hpercent.snail.app.adapters.order.OrderHandingAdapter;
import com.hpercent.snail.app.models.OrderHandingModel;
import com.hpercent.snail.app.views.swipe.SwipeMenu;
import com.hpercent.snail.app.views.swipe.SwipeMenuCreator;
import com.hpercent.snail.app.views.swipe.SwipeMenuItem;
import com.hpercent.snail.app.views.swipe.SwipeMenuListView;

import java.util.LinkedList;

/**
 * Created by koudejian on 14-9-30.
 */
public class OrderHandingFragment extends BaseOrderFragment {

    private LinkedList<OrderHandingModel> mList = null;
    private OrderHandingAdapter mAdapter;
    private SwipeMenuListView mListView;
    /**
     * load layout for different fragment.
     * @return View
     */
    @Override
    public View setLayout() {
        return mInflater.inflate(R.layout.fragment_order_handing, mContainer, false);
    }

    @Override
    public void initView() {
        mOrderTypeController.changeItem(MainActivity.ORDER_HANDING);

        mList = new LinkedList<OrderHandingModel>();
        mList.add(new OrderHandingModel());
        mList.add(new OrderHandingModel());
        mList.add(new OrderHandingModel());
        mList.add(new OrderHandingModel());

        //swipe listview
        mListView = (SwipeMenuListView) findViewById(R.id.swipe_listview);

        mAdapter = new OrderHandingAdapter(mContext, mList);

        mListView.setAdapter(mAdapter);

        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(mContext);
                // set item background
                openItem.setBackground(mContext.getResources().getDrawable(R.drawable.selector_comment_btn_orange));
                // set item width
                openItem.setWidth(dp2px(90));
                // set a icon
                openItem.setIcon(R.drawable.order_icon_star);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(mContext);
                // set item background
                deleteItem.setBackground(mContext.getResources().getDrawable(R.drawable.selector_comment_btn_orange));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.order_icon_share);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        mListView.setMenuCreator(creator);

        // step 2. listener item click event
        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                OrderHandingModel item = mList.get(position);
                switch (index) {
                    case 0:
                        popMessage("1");
                        break;
                    case 1:
                        popMessage("2");
                        break;
                }
                return false;
            }
        });


    }

}
