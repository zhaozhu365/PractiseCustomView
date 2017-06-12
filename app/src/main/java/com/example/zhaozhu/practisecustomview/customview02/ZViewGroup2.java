package com.example.zhaozhu.practisecustomview.customview02;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by zhaozhu on 17/6/10.
 */

public class ZViewGroup2 extends RelativeLayout {

    public ZViewGroup2(Context context) {
        super(context);
    }

    public ZViewGroup2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ZViewGroup2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
