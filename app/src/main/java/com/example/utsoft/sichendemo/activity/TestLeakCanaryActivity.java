package com.example.utsoft.sichendemo.activity;

import android.os.Bundle;
import android.os.Handler;

import com.example.utsoft.sichendemo.base.BaseActivity;
import com.example.utsoft.sichendemo.R;

public class TestLeakCanaryActivity extends BaseActivity {

    private final Handler mhandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_leak_canary);
        mhandler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },3*60*1000);
        finish();
    }
}
