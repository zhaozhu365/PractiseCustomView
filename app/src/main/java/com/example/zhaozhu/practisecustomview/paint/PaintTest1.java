package com.example.zhaozhu.practisecustomview.paint;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by zhaozhu on 16/8/20.
 */
public class PaintTest1 {

    public PaintTest1() {


    }

    Paint  mColorPaint;
    Paint  mBitmapPaint;

    private void initPaint(){
        //构建 Paint时直接加上去锯齿属性
        mColorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //直接使用mColorPaint的属性构建mBitmapPaint
        mBitmapPaint = new Paint(mColorPaint);

        mColorPaint.setARGB(0,0,0,0);
        mColorPaint.setAlpha(0);
        mColorPaint.setColor(Color.RED);
//        mColorPaint.setColorFilter();

    }

}
