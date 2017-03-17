package com.example.utsoft.sichendemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.utsoft.sichendemo.R;
import com.example.utsoft.sichendemo.entity.MessageEvent;
import com.example.utsoft.sichendemo.util.RxBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestRxBusSecondActivity extends AppCompatActivity {

    @BindView(R.id.btn_jump_to_first)
    Button btnJumpToFirst;

    public static void launchActivity(Context context) {
        context.startActivity(new Intent(context, TestRxBusSecondActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_even_bus_second);
        ButterKnife.bind(this);
        setListener();
    }

    private void setListener() {
        btnJumpToFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBus.getDefaultInstance().post(new MessageEvent("wo shi chensisi"));
            }
        });
    }


}
