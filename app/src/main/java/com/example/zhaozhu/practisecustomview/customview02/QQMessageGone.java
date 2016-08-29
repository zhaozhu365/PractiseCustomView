package com.example.zhaozhu.practisecustomview.customview02;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zhaozhu on 16/8/29.
 */
public class QQMessageGone extends View {

    private Paint mPaint;
    private Path mPath;

    //移动的圆的Y轴
    private float mMoveCircleY = 200;

    private float mCircleY = 200;
    private float mCircleX = 300;
    private float mCircleRadius = 30;
    private final float mMoveCircleRadius = mCircleRadius;
    private float mSupX;
    private float mSupY;

    private float lastY;
    private boolean isUp;
    private float mPaintStrokeWidth = 3;

    //控制是否消失
    private boolean isCanDraw = true;

    public QQMessageGone(Context context) {
        this(context, null);
    }

    public QQMessageGone(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QQMessageGone(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(mPaintStrokeWidth);

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPath.reset();

        mSupY = mCircleY + (mMoveCircleY - mCircleY) / 2;
        if (isCanDraw) {
            if (mMoveCircleY - mCircleY < mMoveCircleRadius * 3) {
                //左边的线

                mPath.moveTo(mCircleX - mCircleRadius, mCircleY);
                mPath.quadTo(mCircleX, mSupY, mCircleX - mMoveCircleRadius, mMoveCircleY);
                mPath.lineTo(mCircleX + mMoveCircleRadius, mMoveCircleY);
                mPath.quadTo(mCircleX, mSupY, mCircleX + mCircleRadius, mCircleY);
                mPath.lineTo(mCircleX - mCircleRadius, mCircleY);
                mPath.close();

                canvas.drawPath(mPath, mPaint);

                mPaint.setStyle(Paint.Style.FILL);
                if (isUp) {
                    canvas.drawCircle(mCircleX, mCircleY, mCircleRadius--, mPaint);
                    canvas.drawCircle(mCircleX, mMoveCircleY, mMoveCircleRadius, mPaint);
                } else {
                    canvas.drawCircle(mCircleX, mCircleY, mCircleRadius++, mPaint);
                    canvas.drawCircle(mCircleX, mMoveCircleY, mMoveCircleRadius, mPaint);
                }
            }else {
                mPaint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(mCircleX, mMoveCircleY, mMoveCircleRadius, mPaint);
            }
        }

        //super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mMoveCircleY = event.getY();
                if (mMoveCircleY < lastY) {
                    isUp = false;
                } else {
                    isUp = true;
                }
                lastY = mMoveCircleY;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                if (mMoveCircleY - mCircleY > mMoveCircleRadius * 3) {
                    isCanDraw = false;
                    invalidate();
                }
                break;
        }
        return true;
    }
}
