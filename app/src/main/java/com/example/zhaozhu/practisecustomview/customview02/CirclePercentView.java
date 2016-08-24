package com.example.zhaozhu.practisecustomview.customview02;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.zhaozhu.practisecustomview.PxUtils;
import com.example.zhaozhu.practisecustomview.R;

/**
 * Created by zhaozhu on 16/8/24.
 * Android 自定义view --圆形百分比（进度条）
 * http://blog.csdn.net/wingichoy/article/details/50334595
 */
public class CirclePercentView extends View {

    //圆的半径
    private float mRadius;

    //色带的宽度
    private float mStripeWidth;

    //小圆的颜色
    private int mSmallColor;
    //大圆颜色
    private int mBigColor;

    //中心百分比文字大小
    private float mCenterTextSize;

    //动画位置百分比进度
    private int mCurPercent;

    //总体大小
    private int mHeight;

    private int mWidth;

    //实际百分比进度
    private int mPercent;
    //圆心坐标
    private float x;
    private float y;

    //要画的弧度
    private int mEndAngle;

    Paint mPaint;

    public CirclePercentView(Context context) {
        this(context, null);
    }

    public CirclePercentView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CirclePercentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CirclePercentView, defStyleAttr, 0);
        mRadius = ta.getDimensionPixelSize(R.styleable.CirclePercentView_radius, PxUtils.dpToPx(100, context));
        mStripeWidth = ta.getDimensionPixelSize(R.styleable.CirclePercentView_stripeWidth, PxUtils.dpToPx(30, context));
        mCenterTextSize = ta.getDimensionPixelSize(R.styleable.CirclePercentView_centerTextSize, PxUtils.dpToPx(20, context));
        mSmallColor = ta.getColor(R.styleable.CirclePercentView_smallColor, 0xffafb4db);
        mBigColor = ta.getColor(R.styleable.CirclePercentView_bigColor, 0xff6950a1);
        mCurPercent = ta.getInteger(R.styleable.CirclePercentView_percent, 0);

        ta.recycle();

        mPaint = new Paint();
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurPercent += 10;
                mCurPercent %= 360;
                postInvalidate();
                Log.e("aaaaa", mCurPercent + "");
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //如果为确定大小值，则圆的半径为宽度/2
        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            mRadius = widthSize / 2;
            x = widthSize / 2;
            y = heightSize / 2;
            mWidth = widthSize;
            mHeight = heightSize;
        } else {
            //如果为wrap_content 那么View大小为圆的半径大小*2
            mWidth = (int) (mRadius * 2);
            mHeight = (int) (mRadius * 2);
            x = mRadius;
            y = mRadius;
        }
        //设置视图的大小
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        //方法1，外面的边会出现overdraw
//        mPaint.setColor(mSmallColor);
//        float temp = mRadius - mStripeWidth;
//        canvas.drawCircle(x, y, mRadius, mPaint);
//
//        mPaint.reset();
//        mPaint.setColor(mBigColor);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(mStripeWidth);
////        canvas.drawArc(x - temp, y - temp, x + temp, y + temp, 270, mCurPercent, false, mPaint);
//        //上面那行的写法错误
//        //注意，当画笔粗细较为明显时，矩形的起终点需要相应的加上和减去mStripeWidth / 2，即画笔的中心点（画笔的宽度=中心点向两边扩展 mStripeWidth / 2的长度）
//        canvas.drawArc(0 + mStripeWidth / 2, 0 + mStripeWidth / 2, mWidth - mStripeWidth / 2, mHeight - mStripeWidth / 2, 270, mCurPercent, false, mPaint);

        //方法2，不会出现overdraw
        mPaint.setColor(mSmallColor);
        float temp = mRadius - mStripeWidth;
        canvas.drawCircle(x, y, temp, mPaint);

        //重置以便复用画笔
        mPaint.reset();
        mPaint.setColor(mBigColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStripeWidth);
        //先画已经通过的部分
        //注意，当画笔粗细较为明显时，矩形的起终点需要相应的加上和减去mStripeWidth / 2，即画笔的中心点（画笔的宽度=中心点向两边扩展 mStripeWidth / 2的长度）
        canvas.drawArc(0 + mStripeWidth / 2, 0 + mStripeWidth / 2, mWidth - mStripeWidth / 2, mHeight - mStripeWidth / 2, 270, mCurPercent, false, mPaint);
        //再画上下的部分,颜色(在这里与背景一致)和终止角度要变化
        mPaint.setColor(mSmallColor);
        canvas.drawArc(0 + mStripeWidth / 2, 0 + mStripeWidth / 2, mWidth - mStripeWidth / 2, mHeight - mStripeWidth / 2, (270 + mCurPercent) % 360, 360 - mCurPercent, false, mPaint);

        //重置以便复用画笔
        mPaint.reset();
        String text = (int) (mCurPercent / 360.0F * 100) + "%";
        mPaint.setTextSize(mCenterTextSize);
        mPaint.setColor(Color.WHITE);
        float textLenght = mPaint.measureText(text);
        canvas.drawText(text, x - textLenght / 2, y, mPaint);
    }
}
