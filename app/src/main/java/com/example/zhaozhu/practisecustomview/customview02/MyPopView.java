package com.example.zhaozhu.practisecustomview.customview02;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhaozhu on 16/8/22.
 * wing的博客
 * 新手自定义view练习实例之（一） 泡泡弹窗
 * http://blog.csdn.net/wingichoy/article/details/50455412
 */
public class MyPopView extends View {

    Paint mPaint;

    int mWidth;
    int mHeight;

    int mRectWidth;
    int mRectHeight;

    float mRectPercent = 0.8f;

    public MyPopView(Context context) {
        this(context, null);
    }

    public MyPopView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyPopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int withMode = MeasureSpec.getMode(widthMeasureSpec);
        int withSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (withMode == MeasureSpec.EXACTLY) {
            mWidth = withSize;
            mRectWidth = (int) (mWidth * mRectPercent);
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
            mRectHeight = (int) (mHeight * mRectPercent + 0.1);
        }

        setMeasuredDimension(mWidth, mHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#2C97DE"));
        mPaint.setStyle(Paint.Style.FILL);

        canvas.drawRoundRect(new RectF(0, 0, mRectWidth, mRectHeight), 10, 10, mPaint);

        Path path = new Path();
        path.moveTo(mRectWidth / 2 - 30, mRectHeight);
        path.lineTo(mRectWidth / 2, mRectHeight + 20);
        path.lineTo(mRectWidth / 2 + 30, mRectHeight);
        path.close();

        canvas.drawPath(path, mPaint);
    }
}
