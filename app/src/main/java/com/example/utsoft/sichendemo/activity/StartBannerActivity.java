package com.example.utsoft.sichendemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.utsoft.sichendemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGABannerUtil;

public class StartBannerActivity extends AppCompatActivity {

    @BindView(R.id.banner_splash_pager)
    BGABanner bannerSplashPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_banner);
        ButterKnife.bind(this);
        loadImageData();
    }

    private void loadImageData() {
        List<View> views = new ArrayList<>();
        views.add(BGABannerUtil.getItemImageView(this, R.drawable.img_banner1));
        views.add(BGABannerUtil.getItemImageView(this, R.drawable.img_banner2));
        views.add(BGABannerUtil.getItemImageView(this, R.drawable.img_banner3));
        View lastView = getLayoutInflater().inflate(R.layout.activity_banner_last_view, null);
        views.add(lastView);
        lastView.findViewById(R.id.btn_guide_enter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartBannerActivity.this, MainActivity.class));
                finish();
            }
        });
        bannerSplashPager.setData(views);
    }
}
