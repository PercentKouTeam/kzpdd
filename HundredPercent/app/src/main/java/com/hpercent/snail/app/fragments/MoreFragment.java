package com.hpercent.snail.app.fragments;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.hpercent.snail.app.R;

/**
 * Created by koudejian on 14-7-30.
 */
public class MoreFragment extends BaseFragment implements View.OnClickListener{

    private TextView mTvTitle = null;

    //定义button
    private View mBtShare = null;
    private View mBtNotice = null;
    private View mBtCache = null;
    private View mBtComment = null;
    private View mBtUpdate = null;
    private View mBtAbout = null;
    private View mBtMark = null;
    private View mBtRecommend = null;
    /**
     * load layout for different fragment.
     * @return View
     */
    @Override
    public View setLayout() {
        return mInflater.inflate(R.layout.fragment_more, mContainer, false);
    }

    @Override
    public void initViews() {
        //add your own logic here.
        mTvTitle = (TextView) findViewById(R.id.header_title);
        mTvTitle.setText("更多");

        mBtShare = findViewById(R.id.share_rl);
        mBtNotice = findViewById(R.id.notice_rl);
        mBtCache = findViewById(R.id.cache_rl);
        mBtComment = findViewById(R.id.comment_rl);
        mBtUpdate = findViewById(R.id.update_rl);
        mBtAbout = findViewById(R.id.about_rl);
        mBtMark = findViewById(R.id.mark_rl);
        mBtRecommend = findViewById(R.id.recommend_rl);

        mBtShare.setOnClickListener(this);
        mBtNotice.setOnClickListener(this);
        mBtCache.setOnClickListener(this);
        mBtComment.setOnClickListener(this);
        mBtUpdate.setOnClickListener(this);
        mBtAbout.setOnClickListener(this);
        mBtMark.setOnClickListener(this);
        mBtRecommend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mBtShare){

        }
        else if(v == mBtNotice){

        }
        else if(v == mBtCache){

        }
        else if(v == mBtComment){

        }
        else if(v == mBtUpdate){

        }
        else if(v == mBtAbout){

        }
        else if(v == mBtMark){
            Uri uri = Uri.parse("market://details?id=" + getActivity().getPackageName());
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else if(v == mBtRecommend){

        }

    }
}
