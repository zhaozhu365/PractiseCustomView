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
 * 2阶贝塞尔曲线,
 */
public class TestBézierCurve extends View {

    private Paint paint;
    private Path path;

    private float mSupX;
    private float mSupY;

    public TestBézierCurve(Context context) {
        this(context, null);
    }

    public TestBézierCurve(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestBézierCurve(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        mSupX = 300;
        mSupY = 200;
        path = new Path();
        path.moveTo(200, 200);
        path.quadTo(mSupX, mSupY, 400, 200);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        path.reset();

        path.moveTo(200, 200);
        path.quadTo(mSupX, mSupY, 600, 200);
        canvas.drawPath(path, paint);

        canvas.drawPoint(mSupX, mSupY, paint);

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
