package com.example.zhaozhu.practisecustomview.customview01;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.zhaozhu.practisecustomview.R;

/**
 * Created by zhaozhu on 16/8/17.
 */
public class CustomTitleView02 extends View {

    String mTitleText;
    int mTitleTextColor;
    int mTitleTextSzie;
    Rect mBount;
    Paint mPaint;

    public CustomTitleView02(Context context) {
        this(context, null);
    }

    public CustomTitleView02(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTitleView02(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTitleView02, defStyleAttr, 0);
        int n = ta.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = ta.getIndex(i);
            switch (attr) {
                case R.styleable.CustomTitleView02_mTitleText:
                    mTitleText = ta.getString(attr);
                    break;
                case R.styleable.CustomTitleView02_mTitleColor:
                    mTitleTextColor = ta.getColor(attr, Color.RED);
                    break;
                case R.styleable.CustomTitleView02_mTitleSize:
                    mTitleTextSzie = ta.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
            }
        }
        ta.recycle();

        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSzie);

        mBount = new Rect();
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBount);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.RED);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        mPaint.setColor(mTitleTextColor);
        canvas.drawText(mTitleText, getWidth() / 2 - mBount.width() / 2, getHeight() / 2 + mBount.height() / 2, mPaint);
    }
}
