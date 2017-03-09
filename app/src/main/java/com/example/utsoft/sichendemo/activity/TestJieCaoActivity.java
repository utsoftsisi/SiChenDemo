package com.example.utsoft.sichendemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.utsoft.sichendemo.R;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerSimple;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class TestJieCaoActivity extends AppCompatActivity {

    public static void launchActivity(Context context) {
        context.startActivity(new Intent(context, TestJieCaoActivity.class));
    }

    private JCVideoPlayerSimple jcVideoPlayerSimple;
    private JCVideoPlayerStandard jcVideoPlayerStandard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_jie_cao);
        initView();
        loadData();
    }

    private void initView() {
        jcVideoPlayerSimple = (JCVideoPlayerSimple) findViewById(R.id.custom_videoplayer);
        jcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.custom_videoplayer_standard);
    }

    private void loadData() {
        /**
         * 第一个参数为视频的URI
         * 第三个参数为视频标题
         */
        jcVideoPlayerSimple.setUp("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4",
                JCVideoPlayerSimple.SCREEN_LAYOUT_NORMAL, "嫂子闭眼睛");

        jcVideoPlayerStandard.setUp("http://2449.vod.myqcloud.com/2449_bfbbfa3cea8f11e5aac3db03cda99974.f20.mp4",
                JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "嫂子想我没");
        //加载缩略图
        Glide.with(this)
                .load("http://p.qpic.cn/videoyun/0/2449_bfbbfa3cea8f11e5aac3db03cda99974_1/640")
                .into(jcVideoPlayerStandard.thumbImageView);
    }

    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.allSreeen:
                /**
                 * 第一个参数为上下文
                 * 第二个参数为全屏播放类
                 * 第三个参数为视频地址
                 * 第四个参数为视频的标题
                 */
                JCVideoPlayer.startFullscreen(this,
                        JCVideoPlayerStandard.class,
                        "http://2449.vod.myqcloud.com/2449_43b6f696980311e59ed467f22794e792.f20.mp4",
                        "一行代码实现全屏视频播放");

                break;
            case R.id.btn_list:
                Intent intent = new Intent(this, ListJieCaoActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

}
