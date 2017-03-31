package com.example.utsoft.sichendemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.example.utsoft.sichendemo.R;
import com.example.utsoft.sichendemo.adpter.RecyclerGalleryAdapter;
import com.example.utsoft.sichendemo.widget.MyRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestRecyclerGalleryActivity extends AppCompatActivity {

    @BindView(R.id.iv_content)
    ImageView ivContent;
    @BindView(R.id.rv_gallery)
    MyRecyclerView rvGallery;

    private RecyclerGalleryAdapter mAdapter;
    private List<Integer> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_recycler_gallery);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        mDatas = new ArrayList<>(Arrays.asList(R.drawable.a,
                R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e,
                R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.l));
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvGallery.setLayoutManager(linearLayoutManager);
        mAdapter = new RecyclerGalleryAdapter(this, mDatas);
        rvGallery.setAdapter(mAdapter);

        rvGallery.setOnItemScrollChangeListener(new MyRecyclerView.OnItemScrollChangeListener() {
            @Override
            public void onChange(View view, int position) {
                ivContent.setImageResource(mDatas.get(position));
            }
        });

        mAdapter.setOnItemClickListener(new RecyclerGalleryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ivContent.setImageResource(mDatas.get(position));
            }
        });
    }
}
