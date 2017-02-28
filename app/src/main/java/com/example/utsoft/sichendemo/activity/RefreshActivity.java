package com.example.utsoft.sichendemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.utsoft.sichendemo.R;
import com.example.utsoft.sichendemo.adpter.QuickAdapter;
import com.example.utsoft.sichendemo.entity.Person;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

public class RefreshActivity extends AppCompatActivity {

    @BindView(R.id.rcv)
    RecyclerView rcv;
    @BindView(R.id.ptr)
    PtrFrameLayout ptr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        ButterKnife.bind(this);
        List<Person> data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add(new Person(10000 + i, "sisi"));
        }
        final QuickAdapter adapter = new QuickAdapter(this, data);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setAdapter(adapter);


        /**
         * 经典 风格的头部实现
         */
        final PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(this);
        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);

        // mPtrFrame = (PtrFrameLayout) findViewById(R.id.ptr);
        ptr.setHeaderView(header);
        // mPtrFrame.setPinContent(true);//刷新时，保持内容不动，仅头部下移,默认,false
        ptr.addPtrUIHandler(header);
        //mPtrFrame.setKeepHeaderWhenRefresh(true);//刷新时保持头部的显示，默认为true
        //mPtrFrame.disableWhenHorizontalMove(true);//如果是ViewPager，设置为true，会解决ViewPager滑动冲突问题。
        ptr.setPtrHandler(new PtrHandler() {

            //需要加载数据时触发
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                System.out.println("MainActivity.onRefreshBegin");
                ptr.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptr.refreshComplete();
                        //mPtrFrame.autoRefresh();//自动刷新
                    }
                }, 1800);

            }

            /**
             * 检查是否可以执行下来刷新，比如列表为空或者列表第一项在最上面时。
             */
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                System.out.println("MainActivity.checkCanDoRefresh");
                // 默认实现，根据实际情况做改动
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
                // return true;
            }
        });


    }
}