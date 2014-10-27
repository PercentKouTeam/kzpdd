package com.hpercent.snail.app.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.hpercent.snail.app.R;


public class CusCheckBox extends ImageView {

    private Context mContext = null;
    private Drawable mOnDrawable = null;
    private Drawable mOffDrawable = null;
    private boolean mValue = true;
    private final OnClickListener mListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            changeValue();
        }
    };
    public CusCheckBox(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init();
    }
    public CusCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }
    public CusCheckBox(Context context) {
        super(context);
        mContext = context;
        init();
    }
    public void init(){
        mOnDrawable = mContext.getResources().getDrawable(R.drawable.checkbox_read);
        mOffDrawable = mContext.getResources().getDrawable(R.drawable.checkbox_not_read);
        //默认勾选
        setImageDrawable(mOnDrawable);
        mValue = true;
    }
    public boolean getValue(){
        return mValue;
    }
    public void changeValue(){
        if(mValue){
            setImageDrawable(mOffDrawable);
            mValue = false;
        }else{
            setImageDrawable(mOnDrawable);
            mValue = true;
        }
    }
}
