package com.example.utsoft.sichendemo.netconnect.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.utsoft.sichendemo.R;
import com.example.utsoft.sichendemo.netconnect.presenter.INetConnectPresenter;
import com.example.utsoft.sichendemo.netconnect.presenter.NetConnectPresenterImp;

public class NetConnectActivity extends AppCompatActivity implements INetConnectView {

    public static void launchActivity(Context context) {
        context.startActivity(new Intent(context, NetConnectActivity.class));
    }

    private INetConnectPresenter iNetConnectPresenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_connect);
        iNetConnectPresenter = new NetConnectPresenterImp(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        iNetConnectPresenter.onFinishLoading(this);
    }

    @Override
    public void showProcessBar() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(true);
            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.setMessage("更新数据中，请稍后");
        }
        progressDialog.show();

    }

    @Override
    public void hideProcessBar() {
        progressDialog.hide();
    }

    @Override
    public void showNetError() {
        Toast.makeText(this, "暂无网络", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startNextActivity() {
        Toast.makeText(this, "跳到下个activity", Toast.LENGTH_SHORT).show();
    }
}
