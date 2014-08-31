package com.hpercent.snail.app.controllers;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.hpercent.snail.app.R;

/**
 * Created by koudejian on 14-8-30.
 */
public class HederOrderController extends BaseController {
    private TextView mTvTitle = null;
    private View mHeader = null;
    private Dialog mPopupDialog = null;
    MultiAutoCompleteTextView text = null;
    public HederOrderController(Context context, View view) {
        super(context, view);
    }
    @Override
    public void init() {
        mTvTitle = (TextView) findViewById(R.id.header_title);
        mHeader = findViewById(R.id.header_order_view);

        mHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出类型窗口
                showPopupWindow();
            }
        });
    }
    private void showPopupWindow() {
        initPopupDialog();
        if (mPopupDialog.isShowing()) {
            mPopupDialog.cancel();
        } else {
            mPopupDialog.show();
        }
    }
    private Dialog initPopupDialog() {
        if (mPopupDialog == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.activity_main, null);
//            view.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mPopupDialog.cancel();
//                }
//            });
            mPopupDialog = new Dialog(mContext);
            initDialog(mPopupDialog, view);
        }

        return mPopupDialog;
    }
    private void initDialog(Dialog dialog, View view) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.setContentView(view);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.gravity = Gravity.TOP | Gravity.RIGHT;
        params.y += mContext.getResources().getDimensionPixelSize(R.dimen.header_height);
        dialog.getWindow().setAttributes(params);
    }
}
