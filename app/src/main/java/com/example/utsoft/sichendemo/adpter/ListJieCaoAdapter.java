package com.example.utsoft.sichendemo.adpter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.example.utsoft.sichendemo.R;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by chensi on 2017/3/9.
 */

public class ListJieCaoAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> listUrl;
    private List<String> listTitle;
    private List<String> listThum;

    public ListJieCaoAdapter(Context context, List<String> listUrl, List<String> listTitle, List<String> listThum) {
        this.mContext = context;
        this.listUrl = listUrl;
        this.listTitle = listTitle;
        this.listThum = listThum;
    }

    @Override
    public int getCount() {
        return listUrl.size();
    }

    @Override
    public Object getItem(int i) {
        return listUrl.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_list_jiecao_item, null);
            viewHolder.mJCVideoPlayerStandard = (JCVideoPlayerStandard) convertView.findViewById(R.id.jc_player);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mJCVideoPlayerStandard.setUp(listUrl.get(position), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, listTitle.get(position));
        //加载图片作为视频的显示图片
        Glide.with(mContext)
                .load(listThum.get(position))
                .into(viewHolder.mJCVideoPlayerStandard.thumbImageView);
        //或者
        //viewHolder.mJCVideoPlayerStandard.thumbImageView.setImageURI(Uri.parse(listThum.get(position)));
        return convertView;
    }

    class ViewHolder {
        JCVideoPlayerStandard mJCVideoPlayerStandard;
    }

}
