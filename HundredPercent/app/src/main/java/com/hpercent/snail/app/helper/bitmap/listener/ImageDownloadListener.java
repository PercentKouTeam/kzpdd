package com.hpercent.snail.app.helper.bitmap.listener;


import com.hpercent.snail.app.helper.bitmap.DownloadImageTask;

/**
 *
 * 图片下载完成时监听
 * Created by koudejian on 14-1-14.
 *
 */
public interface ImageDownloadListener {
    //下载完成时回调
    void OnSuccessed(DownloadImageTask temp);
}
