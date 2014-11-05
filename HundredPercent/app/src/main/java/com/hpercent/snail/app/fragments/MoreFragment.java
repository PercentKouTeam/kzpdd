package com.hpercent.snail.app.fragments;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.hpercent.snail.app.R;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;

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
            popShareWindow();
        }

    }

    public void popShareWindow(){
        // 首先在您的Activity中添加如下成员变量
        final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");
        // 设置分享内容
        mController.setShareContent("友盟社会化组件（SDK）让移动应用快速整合社交分享功能，http://www.umeng.com/social");
        // 设置分享图片, 参数2为图片的url地址
        mController.setShareMedia(new UMImage(mContext,
                "http://www.umeng.com/images/pic/banner_module_social.png"));
        // 设置分享图片，参数2为本地图片的资源引用
        //mController.setShareMedia(new UMImage(getActivity(), R.drawable.icon));
        // 设置分享图片，参数2为本地图片的路径(绝对路径)
        //mController.setShareMedia(new UMImage(getActivity(),
        //                                BitmapFactory.decodeFile("/mnt/sdcard/icon.png")));

        // 设置分享音乐
        //UMusic uMusic = new UMusic("http://sns.whalecloud.com/test_music.mp3");
        //uMusic.setAuthor("GuGu");
        //uMusic.setTitle("天籁之音");
        // 设置音乐缩略图
        //uMusic.setThumb("http://www.umeng.com/images/pic/banner_module_social.png");
        //mController.setShareMedia(uMusic);

        // 设置分享视频
        //UMVideo umVideo = new UMVideo(
        //          "http://v.youku.com/v_show/id_XNTE5ODAwMDM2.html?f=19001023");
        // 设置视频缩略图
        //umVideo.setThumb("http://www.umeng.com/images/pic/banner_module_social.png");
        //umVideo.setTitle("友盟社会化分享!");
        //mController.setShareMedia(umVideo);
        mController.openShare((android.app.Activity) mContext, false);
    }
}
