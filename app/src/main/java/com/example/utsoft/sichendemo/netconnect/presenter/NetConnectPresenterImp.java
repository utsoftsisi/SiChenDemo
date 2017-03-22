package com.example.utsoft.sichendemo.netconnect.presenter;

import android.content.Context;
import android.os.Handler;

import com.example.utsoft.sichendemo.netconnect.model.INetConnect;
import com.example.utsoft.sichendemo.netconnect.model.NetConnectModel;
import com.example.utsoft.sichendemo.netconnect.view.INetConnectView;

/**
 * Created by 陈思 on 2017/3/22 14:45.
 * Function:presenter作为model和view的桥梁，实现两者的交互
 * Desc:
 * model操作后(iNetConnect.isNetConnect(context)),结果由INetConnectView带回，如：
 * iNetConnectView.startNextActivity();
 * iNetConnectView.showNetError();
 */

public class NetConnectPresenterImp implements INetConnectPresenter {
    private INetConnect iNetConnect;
    private INetConnectView iNetConnectView;

    public NetConnectPresenterImp(INetConnectView iNetConnectView) {
        this.iNetConnectView = iNetConnectView;
        iNetConnect = new NetConnectModel();
    }

    @Override
    public void onFinishLoading(final Context context) {
        iNetConnectView.showProcessBar();
        //延时3s是为了看到ProgressDialog的效果。
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (iNetConnect.isNetConnect(context)) {
                    iNetConnectView.startNextActivity();
                } else {
                    iNetConnectView.showNetError();
                }
                iNetConnectView.hideProcessBar();
            }
        }, 3000);


    }
}
