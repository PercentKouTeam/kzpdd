package com.hpercent.snail.app.controllers.order;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hpercent.snail.app.R;
import com.hpercent.snail.app.activities.MainActivity;
import com.hpercent.snail.app.adapters.OrderTypeAdapter;
import com.hpercent.snail.app.controllers.BaseController;
import com.hpercent.snail.app.controllers.HeaderOrderController;
import com.hpercent.snail.app.models.OrderMenuModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koudejian on 14-11-4.
 */
public class OrderTypeController extends BaseController {

    private View mParentView = null;
    private ListView mListView = null;
    private List<OrderMenuModel> mList= new ArrayList<OrderMenuModel>();
    private OrderTypeAdapter mOrderTypeAdapter = null;

    private HeaderOrderController mHeaderOrderController = null;


    public OrderTypeController(Context context, View view) {
        super(context, view);
        init();
    }

    /*
    private OnItemSelectedListener mOnItemSelectedListener = null;

    public interface OnItemSelectedListener{
        public void onSelected(int position);
    }
    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener){
        this.mOnItemSelectedListener = onItemSelectedListener;
    }
    //*/
    public void init() {
        mHeaderOrderController = new HeaderOrderController(mContext, mView);
        mHeaderOrderController.setOnHeaderClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mParentView != null && mParentView.getVisibility() == View.INVISIBLE){
                    show();
                }else{
                    hide();
                }

            }
        });

        mParentView = findViewById(R.id.menu);
        mListView = (ListView) findViewById(R.id.listview);

        mList.add(new OrderMenuModel("办理中", "0"));
        mList.add(new OrderMenuModel("待办理", "1"));
        mList.add(new OrderMenuModel("待付款", "2"));
        mList.add(new OrderMenuModel("待评价", "3"));
        mList.add(new OrderMenuModel("已完成", "4"));
        mList.add(new OrderMenuModel("退款单"));
        mList.add(new OrderMenuModel("已取消"));

        mOrderTypeAdapter = new OrderTypeAdapter(mContext, mList);
        mListView.setAdapter(mOrderTypeAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                changeItem(position);
                MainActivity.getInstance().switchOrder(position);
//                if(mOnItemSelectedListener != null){
//                    mOnItemSelectedListener.onSelected(position);
//                }
            }
        });
    }

    public void changeItem(int type){
        if(mHeaderOrderController != null){
            mHeaderOrderController.setTitle(mList.get(type).getName());
        }
        hide();
    }

    public void show(){
        if(mParentView != null){
            mParentView.setVisibility(View.VISIBLE);
        }
    }

    public void hide(){
        if(mParentView != null){
            mParentView.setVisibility(View.INVISIBLE);
        }
    }
}
