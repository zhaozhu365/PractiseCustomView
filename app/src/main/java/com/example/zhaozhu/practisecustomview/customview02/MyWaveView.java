package com.example.zhaozhu.practisecustomview.customview02;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhaozhu on 16/8/22.
 * wing的博客
 * 新手自定义view练习实例之（二） 波浪view
 * http://blog.csdn.net/wingichoy/article/details/50460213
 */
public class MyWaveView extends View {




    public MyWaveView(Context context) {
        this(context, null);
    }

    public MyWaveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyWaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



}
