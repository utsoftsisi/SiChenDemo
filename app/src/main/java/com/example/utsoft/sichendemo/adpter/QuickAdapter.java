package com.example.utsoft.sichendemo.adpter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.utsoft.sichendemo.R;
import com.example.utsoft.sichendemo.entity.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chensi on 2017/2/24.
 */

public class QuickAdapter extends BaseQuickAdapter<Person> {


    public QuickAdapter(Context context, List<Person> data) {
        super(context, R.layout.activity_refresh_list_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Person person) {
        baseViewHolder.setText(R.id.tv_name, person.getName())
                .setText(R.id.tv_age, person.getAge()+"");

    }

}
