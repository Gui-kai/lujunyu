package com.bm.container.view;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * SwipeRefreshLayout和ConvenientBanner广告轮播事件冲突解决
 * Created by kec on 2017/8/8.
 */

public class MySwipeRefreshLayout extends SwipeRefreshLayout {

    public MySwipeRefreshLayout(Context context) {
        super(context);
    }

    public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    float lastx = 0;
    float lasty = 0;
    boolean ismovepic = false;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {


        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            lastx = ev.getX();
            lasty = ev.getY();
            ismovepic = false;
            return super.onInterceptTouchEvent(ev);
        }

        final int action = MotionEventCompat.getActionMasked(ev);
//        Log.v(ev.getX() + "---" + ev.getY());

        int x2 = (int) Math.abs(ev.getX() - lastx);
        int y2 = (int) Math.abs(ev.getY() - lasty);

        //滑动图片最小距离检查
//        VLog.v("滑动差距 - >" + x2 + "--" + y2);
        if (x2 > y2) {
            if (x2 >= 100) ismovepic = true;
            return false;
        }

        //是否移动图片(下拉刷新不处理)
        if (ismovepic) {
//            VLog.v("滑动差距 - >" + x2 + "--" + y2);
            return false;
        }

        boolean isok = super.onInterceptTouchEvent(ev);

//        VLog.v("isok ->" + isok);

        return isok;
    }

}
