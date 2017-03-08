package com.example.utsoft.sichendemo.base;

import android.app.Application;
import android.content.Context;

import com.example.utsoft.sichendemo.dao.GreenDaoManager;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by Windows on 2017/2/22.
 */

public class BaseApplication extends Application {

    private RefWatcher mRefWatcher;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mRefWatcher = LeakCanary.install(this);
        mContext = getApplicationContext();
        //GreenDao的初始化
        GreenDaoManager.getInstance();
    }

    public static RefWatcher getRefWatcher(Context context) {
        BaseApplication application = (BaseApplication) context.getApplicationContext();
        return application.mRefWatcher;
    }

    public static Context getContext() {
        return mContext;
    }

}
