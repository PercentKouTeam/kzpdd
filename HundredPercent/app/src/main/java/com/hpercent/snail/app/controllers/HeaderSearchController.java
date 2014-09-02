package com.hpercent.snail.app.controllers;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.hpercent.snail.app.R;

/**
 * Created by enterli on 2014/9/1.
 */
public class HeaderSearchController extends BaseController {
    private TextView mSearchView = null;
    String[] books = new String[]{"rollen", "rollenholt", "rollenren", "roll", "hehe"};
    private Dialog dialog = null;

    public HeaderSearchController(Context context, View view) {
        super(context, view);
    }

    public void init() {
        mSearchView = (TextView) findViewById(R.id.searchText);
        mSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView) v).setText(""); //清空
                dialog();
            }
        });
    }

    /**
     * 显示搜索框
     */
    public void dialog() {
        LayoutInflater inflater = ((Activity) this.mContext).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_search, null);
        //设置自动完成控件
        ArrayAdapter<String> av = new ArrayAdapter<String>(this.mContext,
                android.R.layout.simple_dropdown_item_1line, books);
        AutoCompleteTextView auto = (AutoCompleteTextView) view.findViewById(R.id.headerSearchAuto);
        auto.setAdapter(av);
        //设置取消
        TextView textView = (TextView) view.findViewById(R.id.headerSearchCancel);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog = new Dialog(this.mContext, R.style.Dialog_Fullscreen);
        dialog.setContentView(view);
        dialog.show();
    }
}
