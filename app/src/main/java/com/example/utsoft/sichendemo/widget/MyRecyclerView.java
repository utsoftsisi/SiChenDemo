package com.example.utsoft.sichendemo.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.orhanobut.logger.Logger;

/**
 * Created by chensi on 2017/3/30.
 */

public class MyRecyclerView extends RecyclerView {

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private View mCurrentView;

    /**
     * 滚动时回调的接口
     */
    private OnItemScrollChangeListener mItemScrollChangeListener;

    public void setOnItemScrollChangeListener(OnItemScrollChangeListener mItemScrollChangeListener) {
        this.mItemScrollChangeListener = mItemScrollChangeListener;
    }

    public interface OnItemScrollChangeListener {
        void onChange(View view, int position);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mCurrentView = getChildAt(0);
        if (mItemScrollChangeListener != null) {
            mItemScrollChangeListener.onChange(mCurrentView, getChildAdapterPosition(mCurrentView));
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        //监听ACTION_MOVE，用户手指滑动时，不断把当前可见的第一个View回调回去
        if (e.getAction() == MotionEvent.ACTION_MOVE) {
            mCurrentView = getChildAt(0);
            Logger.i(getChildAdapterPosition(getChildAt(0)) + "");
            if (mItemScrollChangeListener != null) {
                mItemScrollChangeListener.onChange(mCurrentView, getChildAdapterPosition(mCurrentView));
            }
        }
        return super.onTouchEvent(e);
    }

}
