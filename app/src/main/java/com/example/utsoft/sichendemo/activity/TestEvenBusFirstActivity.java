package com.example.utsoft.sichendemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utsoft.sichendemo.R;
import com.example.utsoft.sichendemo.entity.MessageEvent;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestEvenBusFirstActivity extends AppCompatActivity {

    @BindView(R.id.btn_jump)
    Button btnJump;
    @BindView(R.id.tv_message)
    TextView tvMessage;

    public static void launchActivity(Context context) {
        context.startActivity(new Intent(context, TestEvenBusFirstActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_even_bus_first);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        setListener();
    }

    private void setListener() {
        btnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestEvenBusSecondActivity.launchActivity(TestEvenBusFirstActivity.this);
                Logger.i(Thread.currentThread().getId()+"1");
            }
        });
    }

    @Subscribe
    public void onEvent(MessageEvent event) {
        String msg = "onEventMainThread收到了消息：" + event.getMessage();
        tvMessage.setText(msg);
        Logger.i(Thread.currentThread().getId()+"3");
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
