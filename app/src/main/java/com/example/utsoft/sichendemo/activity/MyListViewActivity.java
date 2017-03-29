package com.example.utsoft.sichendemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.utsoft.sichendemo.R;
import com.example.utsoft.sichendemo.adpter.MyListViewAdapter;
import com.example.utsoft.sichendemo.widget.MyListView;

import java.util.ArrayList;
import java.util.List;

public class MyListViewActivity extends AppCompatActivity {

    private MyListView myListView;
    private MyListViewAdapter adapter;
    private List<String> contentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_listview);
        initList();
        myListView = (MyListView) findViewById(R.id.lv);
        myListView.setOnDeleteListener(new MyListView.OnDeleteListener() {
            @Override
            public void onDelete(int index) {
                contentList.remove(index);
                adapter.notifyDataSetChanged();
            }
        });
        adapter = new MyListViewAdapter(this, 0, contentList);
        myListView.setAdapter(adapter);

    }

    private void initList() {
        for (int i = 0; i < 1000; i++) {
            contentList.add("Content Item" + i);
        }
    }

}
