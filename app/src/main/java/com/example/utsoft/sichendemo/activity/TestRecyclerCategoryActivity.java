package com.example.utsoft.sichendemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utsoft.sichendemo.R;
import com.example.utsoft.sichendemo.adpter.RecyclerCategoryAdapter;
import com.example.utsoft.sichendemo.entity.Category;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestRecyclerCategoryActivity extends AppCompatActivity {

    @BindView(R.id.rv_category)
    RecyclerView rvCategory;

    private RecyclerCategoryAdapter adapter;
    private List<Category> lists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_recycler);
        ButterKnife.bind(this);
        initData();
        initView();

    }

    private void initView() {
        adapter = new RecyclerCategoryAdapter(this, lists);
        adapter.setOnItemClickListener(new RecyclerCategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView secondCategory = (TextView) view.findViewById(R.id.tv_category_second);
                Toast.makeText(TestRecyclerCategoryActivity.this, secondCategory.getText().toString() + position, Toast.LENGTH_LONG).show();

            }
        });
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //spanSize表示一个item的跨度，跨度了多少个span
                switch (adapter.getItemViewType(position)) {
                    case Category.SECOND_TYPE:
                        return 3;
                    case Category.THIRD_TYPE:
                        return 1;
                    default:
                        return 1;
                }
            }
        });
        rvCategory.setLayoutManager(mLayoutManager);
        rvCategory.setAdapter(adapter);

    }

    private void initData() {
        lists.add(new Category("饼干", 0));
        lists.add(new Category("奶油饼干", 1));
        lists.add(new Category("威化", 1));
        lists.add(new Category("曲奇", 1));
        lists.add(new Category("传统糕点", 0));
        lists.add(new Category("凤梨酥", 1));
        lists.add(new Category("杏仁饼", 1));
        lists.add(new Category("烧饼", 1));
        lists.add(new Category("花生酥", 1));
        lists.add(new Category("西式糕点", 0));
        lists.add(new Category("巧克力派", 1));
        lists.add(new Category("酥心卷", 1));
        lists.add(new Category("面包", 1));
        lists.add(new Category("泡芙", 1));
        lists.add(new Category("蛋挞", 1));


    }
}
