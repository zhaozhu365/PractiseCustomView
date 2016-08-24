package com.example.zhaozhu.practisecustomview.customview02;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.example.zhaozhu.practisecustomview.PxUtils;
import com.example.zhaozhu.practisecustomview.R;

/**
 * Created by zhaozhu on 16/8/22.
 * wing的博客
 * 新手自定义view练习实例之（二） 波浪view
 * http://blog.csdn.net/wingichoy/article/details/50460213
 */
public class MyWaveView extends View {

    private Context mContext;
    private int mWaveCount;
    private int mWaveWidth;
    private int mMode;
    //圆角模式
    public final int MODE_CIRCLE = -1;
    //三角模式
    public final int MODE_TRIANGLE = -2;
    private int mColor;
    private int mWidth;
    private float mRectWidth;
    private int mHeight;
    private float mRectHeight;
    private float mWaveHeight;
    // 半径
    private float mRadius;

    public MyWaveView(Context context) {
        this(context, null);
    }

    public MyWaveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyWaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyVaveView, defStyleAttr, 0);
        mWaveCount = ta.getInt(R.styleable.MyVaveView_waveCount, 10);
        mWaveWidth = ta.getInt(R.styleable.MyVaveView_waveWidth, 20);
        mMode = ta.getInteger(R.styleable.MyVaveView_mode, -2);
        mColor = ta.getColor(R.styleable.MyVaveView_android_color, Color.parseColor("#2C97DE"));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSzie = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //矩形宽度为view的80%
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSzie;
            mRectWidth = (float) (mWidth * 0.8);

            //如果是wrap_content 直接给一个定值
        } else if (widthMode == MeasureSpec.AT_MOST) {
            mWidth = PxUtils.dpToPx(300, mContext);
            mRectWidth = (float) (mWidth * 0.8);
        }

        //矩形高度为view的80%
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
            mRectHeight = (float) (mHeight * 0.8);

            //如果是wrap_content 直接给一个定值
        } else if (heightMode == MeasureSpec.AT_MOST) {
            mHeight = PxUtils.dpToPx(200, mContext);
            mRectHeight = (float) (mHeight * 0.8);
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(mColor);

        //计算每个三角形的高？
        mWaveHeight = mRectHeight / mWaveCount;

        //计算padding
        float padding = (mWidth - mRectWidth) / 2;
        //绘制矩形
        canvas.drawRect(padding, padding, mRectWidth + padding, mRectHeight + padding, p);

        // 绘制波浪
        if (mMode == MODE_TRIANGLE) {//绘制三角
            //绘制右边的波浪
            float startX = padding + mRectWidth;
            float startY = padding;
            Path path = new Path();
            path.moveTo(startX, startY);
            for (int i = 0; i < mWaveCount; i++) {
                path.lineTo(startX + mWaveWidth, startY + mWaveHeight * i + mWaveHeight / 2);
                path.lineTo(startX, startY + mWaveHeight * (i + 1));
            }
            path.close();
            canvas.drawPath(path, p);

            //绘制左边的波浪
            startX = padding;
            startY = padding;
            path.moveTo(startX, startY);

            for (int i = 0; i < mWaveCount; i++) {

                path.lineTo(startX - mWaveWidth, startY + mWaveHeight * i + mWaveHeight / 2);
                path.lineTo(startX, startY + mWaveHeight * (i + 1));
            }
            path.close();
            canvas.drawPath(path, p);
        } else if (mMode == MODE_CIRCLE) {//绘制圆形,可以通过path来绘制圆，也可以直接用canvas的方法来绘制圆
//            // 1、这里mRadius算出来的其实是直径
//            /** 这种方式计算出的波浪数量其实是不对的，正确的应该是下面[2]的计算方式*/
//            mRadius = mRectHeight / mWaveCount;
//            //绘制右边的波浪
//            float startX = padding + mRectWidth;
//            float startY = padding;
//            // 这里i循环的次数为什么和三角形不一样相同，因为[1]mRadius是直径
//            for (int i = 0; i < mWaveCount / 2; i++) {
//                canvas.drawCircle(startX, startY + i * 2 * mRadius + mRadius, mRadius, p);
//            }
//
//            //绘制左边的波浪
//            startX = padding;
//            startY = padding;
//            for (int i = 0; i < mWaveCount / 2; i++) {
//                canvas.drawCircle(startX, startY + i * 2 * mRadius + mRadius, mRadius, p);
//            }

            // 2、这里mRadius算出来的是正确的半径
            /** 这种方式计算出的波浪数量是对的*/
            mRadius = mRectHeight / mWaveCount / 2;
            //绘制右边的波浪
            float startX = padding + mRectWidth;
            float startY = padding;
            // 这里i循环的次数和三角形相同，因为[2]mRadius是半径
            for (int i = 0; i < mWaveCount; i++) {
                canvas.drawCircle(startX, startY + i * 2 * mRadius + mRadius, mRadius, p);
            }

            //绘制左边的波浪
            startX = padding;
            startY = padding;
            for (int i = 0; i < mWaveCount; i++) {
                canvas.drawCircle(startX, startY + i * 2 * mRadius + mRadius, mRadius, p);
            }
        } else {
            super.onDraw(canvas);
        }

    }
}
