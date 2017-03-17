package com.example.utsoft.sichendemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.utsoft.sichendemo.R;
import com.example.utsoft.sichendemo.entity.MessageEvent;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestEvenBusSecondActivity extends AppCompatActivity {

    @BindView(R.id.btn_jump_to_first)
    Button btnJumpToFirst;

    public static void launchActivity(Context context) {
        context.startActivity(new Intent(context, TestEvenBusSecondActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_even_bus_second);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        setListener();
    }

    private void setListener() {
        btnJumpToFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new MessageEvent("wo shi sisi"));
                Logger.i(Thread.currentThread().getId()+"2");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent (MessageEvent event) {
        btnJumpToFirst.setText("自己发的：wo shi sisi");
    }
}
