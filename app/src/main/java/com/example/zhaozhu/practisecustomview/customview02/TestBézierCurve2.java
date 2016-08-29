package com.example.zhaozhu.practisecustomview.customview02;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zhaozhu on 16/8/29.
 * 3阶贝塞尔曲线,
 */
public class TestBézierCurve2 extends View {

    private Paint paint;
    private Path path;

    private float mSupX;
    private float mSupY;

    public TestBézierCurve2(Context context) {
        this(context, null);
    }

    public TestBézierCurve2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestBézierCurve2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        mSupX = 300;
        mSupY = 200;
        path = new Path();
        path.moveTo(200, 200);
        path.cubicTo(mSupX, mSupY, mSupX, mSupX, 800, 200);

    }

    private float scaleX = 2.0f;
    private float scaleY = 1.2f;

    @Override
    protected void onDraw(Canvas canvas) {
        path.reset();

        path.moveTo(200, 200);

        //path.cubicTo(mSupX, mSupY, mSupX * (float) (Math.random() * 2), mSupX * (float) (Math.random()), 800, 200);
        path.cubicTo(mSupX, mSupY, mSupX * scaleX, mSupX * scaleY, 800, 200);

        canvas.drawPath(path, paint);

        canvas.drawPoint(mSupX, mSupY, paint);
        canvas.drawPoint(mSupX * scaleX, mSupY * scaleY, paint);

        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mSupX = event.getX();
                mSupY = event.getY();
                invalidate();
        }

        return true;
    }
}
