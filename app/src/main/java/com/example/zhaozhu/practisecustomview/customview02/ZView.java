package com.example.zhaozhu.practisecustomview.customview02;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zhaozhu on 17/6/10.
 */

public class ZView extends View {
    public ZView(Context context) {
        super(context);
    }

    public ZView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ZView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
        Log.e("zz", "ZView: onTouchEvent: Action = " + event.getAction());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return super.onTouchEvent(event);
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }

}
