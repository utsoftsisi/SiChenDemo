package com.example.utsoft.sichendemo.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.utsoft.sichendemo.R;
import com.example.utsoft.sichendemo.entity.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chensi on 2017/3/29.
 */

public class RecyclerCategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Category> lists = new ArrayList<>();
    private LayoutInflater layoutInflater;

    public RecyclerCategoryAdapter(Context context, List<Category> lists) {
        this.context = context;
        this.lists = lists;
        this.layoutInflater = LayoutInflater.from(context);
    }


    //用于决定元素的布局使用哪种类型，传入的是数据源List的下标position，返回的是一个int型标志，传递给onCreateViewHolder的第二个参数。
    @Override
    public int getItemViewType(int position) {
        return lists.get(position).getType();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Category.SECOND_TYPE) {
            return new SecondViewHolder(layoutInflater.inflate(R.layout.activity_test_recycler_category_second_item, null));
        } else {
            return new ThirdViewHolder(layoutInflater.inflate(R.layout.activity_test_recycler_category_third_item, null));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case Category.SECOND_TYPE:
                SecondViewHolder secondViewHolder = (SecondViewHolder) holder;
                secondViewHolder.secondCategory.setText(lists.get(position).getCategoryName());
                break;
            case Category.THIRD_TYPE:
                ThirdViewHolder thirdViewHolder = (ThirdViewHolder) holder;
                thirdViewHolder.thirdCategory.setText(lists.get(position).getCategoryName());
                break;

        }


    }

    @Override
    public int getItemCount() {
        if (lists != null) {
            return lists.size();
        } else {
            return 0;
        }

    }

    public class SecondViewHolder extends RecyclerView.ViewHolder {

        private TextView secondCategory;

        public SecondViewHolder(View itemView) {
            super(itemView);
            secondCategory = (TextView) itemView.findViewById(R.id.tv_category_second);
        }
    }

    public class ThirdViewHolder extends RecyclerView.ViewHolder {

        private TextView thirdCategory;

        public ThirdViewHolder(View itemView) {
            super(itemView);
            thirdCategory = (TextView) itemView.findViewById(R.id.tv_category_third);
        }
    }
}
