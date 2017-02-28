package com.example.utsoft.sichendemo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.utsoft.sichendemo.R;
import com.example.utsoft.sichendemo.fragment.MyFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlycoActivity extends AppCompatActivity {

    @BindView(R.id.tablayout)
    SlidingTabLayout tablayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private List<MyFragment> mFagments = new ArrayList<>();
    private String[] mTitles = {"Tab1", "Tab2", "Tab3", "Tab4", "Tab5", "Tab6"};

    private MyPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flyco);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        for (String s : mTitles) {
            mFagments.add(MyFragment.getInstance(s));
        }
        //getChildFragmentManager() 如果是嵌套在fragment中就要用这个
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tablayout.setViewPager(viewPager, mTitles);

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFagments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFagments.get(position);
        }
    }


}
