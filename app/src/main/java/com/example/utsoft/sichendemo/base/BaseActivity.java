package com.example.utsoft.sichendemo.base;

import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApplication.getRefWatcher(this).watch(this);
    }
}
