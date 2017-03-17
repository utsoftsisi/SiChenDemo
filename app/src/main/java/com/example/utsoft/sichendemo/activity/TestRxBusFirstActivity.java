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
import com.example.utsoft.sichendemo.util.RxBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class TestRxBusFirstActivity extends AppCompatActivity {

    @BindView(R.id.btn_jump)
    Button btnJump;
    @BindView(R.id.tv_message)
    TextView tvMessage;

    private Disposable disposable;

    public static void launchActivity(Context context) {
        context.startActivity(new Intent(context, TestRxBusFirstActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_even_bus_first);
        ButterKnife.bind(this);
        setListener();
        onEvent();
    }

    private void setListener() {
        btnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestRxBusSecondActivity.launchActivity(TestRxBusFirstActivity.this);
            }
        });
    }
    //订阅(subscribe)事件
    private void onEvent() {
        RxBus.getDefaultInstance().toObservable(MessageEvent.class)
                .subscribe(new Observer<MessageEvent>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(MessageEvent messageEvent) {
                        String msg = "RxBus收到了数据：" + messageEvent.getMessage();
                        tvMessage.setText(msg);
                        Toast.makeText(TestRxBusFirstActivity.this, msg, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
