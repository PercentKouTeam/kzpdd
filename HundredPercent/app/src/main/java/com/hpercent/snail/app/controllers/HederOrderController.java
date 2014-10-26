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
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;

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
                test();
                //弹出类型窗口
//                showPopupWindow();
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

    public void test(){
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
