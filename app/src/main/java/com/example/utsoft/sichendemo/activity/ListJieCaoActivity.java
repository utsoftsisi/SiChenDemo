package com.example.utsoft.sichendemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.utsoft.sichendemo.R;
import com.example.utsoft.sichendemo.adpter.ListJieCaoAdapter;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class ListJieCaoActivity extends AppCompatActivity {

    private ListView mListView;
    private List<String> listUrl = new ArrayList<>();
    private List<String> listTitle = new ArrayList<>();
    private List<String> listThum = new ArrayList<>();
    //视频地址
    private String path1 = "http://video.jiecao.fm/5/1/%E8%87%AA%E5%8F%96%E5%85%B6%E8%BE%B1.mp4";
    private String path2 = "http://gslb.miaopai.com/stream/ed5HCfnhovu3tyIQAiv60Q__.mp4";
    //视频缩略图地址
    private String path3 = "http://img4.jiecaojingxuan.com/2016/5/1/3430ec64-e6a7-4d8e-b044-9d408e075b7c.jpg";
    private String path4 = "http://img4.jiecaojingxuan.com/2016/3/14/2204a578-609b-440e-8af7-a0ee17ff3aee.jpg";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_jie_cao);
        init();
    }

    private void init() {
        while (listUrl.size() < 10) {
            listUrl.add(path1);
            listUrl.add(path2);
            listThum.add(path3);
            listThum.add(path4);
        }
        for (int i = 1; i < 11; i++) {
            listTitle.add("这是第" + i + "个视频");
        }
        mListView = (ListView) findViewById(R.id.listView);
        ListAdapter adapter = new ListJieCaoAdapter(this, listUrl, listTitle, listThum);
        mListView.setAdapter(adapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

}
