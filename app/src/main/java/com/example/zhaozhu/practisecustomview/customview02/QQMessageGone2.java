package com.example.zhaozhu.practisecustomview.customview02;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zhaozhu on 16/8/29.
 */
public class QQMessageGone2 extends View {

    private Paint mPaint;
    private Path mPath;

    private float mPaintStrokeWidth = 3;

    //固定的圆
    private float r = 50;
    private float a0 = 300;
    private float b0 = 200;

    //移动的圆
    private final float R = 50;
    private float A0;
    private float B0;
    //上一次移动的圆的圆心坐标
    private float lastA0;
    private float lastB0;

    //两圆心中点坐标
    private float midX;
    private float midY;

    //圆心x,y轴的差
    private float deltaX;
    private float deltaY;

    //控制是否消失
    private boolean IS_DISAPPEAR_UP = false;
    // 手指释放
    private boolean IS_ACTION_UP = false;

    private void setMidX() {
        midX = (A0 + a0) / 2;
    }

    private void setMidY() {
        midY = (B0 + b0) / 2;
    }

    //圆心X轴坐标差
    private void setDeltaX() {
        deltaX = A0 - a0;
    }

    //圆心Y轴坐标差
    private void setDeltaY() {
        deltaY = B0 - b0;
    }

    //圆心之间的距离
    private float getDistance() {
        return (float) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
    }

    private float getSin() {
        return deltaY / getDistance();
    }

    private float getCos() {
        return deltaX / getDistance();
    }

    /**
     * 特别注意区分4个象限：因为这里获取Sin,Cos不是通过Math来计算角度的，而是通过三角形的3个边计算的
     *
     * @return
     */
    private float geta1() {
        if (B0 - b0 >= 0) {
            if (A0 - a0 >= 0) {
                return a0 - r * getSin();
            } else {
                return a0 + r * getSin();
            }
        } else {
            if (A0 - a0 >= 0) {
                return a0 + r * getSin();
            } else {
                return a0 - r * getSin();
            }
        }
    }

    private float getb1() {
        if (A0 - a0 >= 0) {
            if (B0 - b0 >= 0) {
                return b0 + r * getCos();
            } else {
                return b0 - r * getCos();
            }
        } else {
            if (B0 - b0 >= 0) {
                return b0 - r * getCos();
            } else {
                return b0 + r * getCos();
            }
        }
    }

    private float geta2() {
        if (B0 - b0 >= 0) {
            if (A0 - a0 >= 0) {
                return a0 + r * getSin();
            } else {
                return a0 - r * getSin();
            }
        } else {
            if (A0 - a0 >= 0) {
                return a0 - r * getSin();
            } else {
                return a0 + r * getSin();
            }
        }
    }

    private float getb2() {
        if (A0 - a0 >= 0) {
            if (B0 - b0 >= 0) {
                return b0 - r * getCos();
            } else {
                return b0 + r * getCos();
            }
        } else {
            if (B0 - b0 >= 0) {
                return b0 + r * getCos();
            } else {
                return b0 - r * getCos();
            }
        }
    }

    private float getA1() {
        if (B0 - b0 >= 0) {
            if (A0 - a0 >= 0) {
                return A0 - R * getSin();
            } else {
                return A0 + R * getSin();
            }
        } else {
            if (A0 - a0 >= 0) {
                return A0 + R * getSin();
            } else {
                return A0 - R * getSin();
            }
        }
    }

    private float getB1() {
        if (A0 - a0 >= 0) {
            if (B0 - b0 >= 0) {
                return B0 + R * getCos();
            } else {
                return B0 - R * getCos();
            }
        } else {
            if (B0 - b0 >= 0) {
                return B0 - R * getCos();
            } else {
                return B0 + R * getCos();
            }
        }
    }

    private float getA2() {
        if (B0 - b0 >= 0) {
            if (A0 - a0 >= 0) {
                return A0 + R * getSin();
            } else {
                return A0 - R * getSin();
            }
        } else {
            if (A0 - a0 >= 0) {
                return A0 - R * getSin();
            } else {
                return A0 + R * getSin();
            }
        }
    }

    private float getB2() {
        if (A0 - a0 >= 0) {
            if (B0 - b0 >= 0) {
                return B0 - R * getCos();
            } else {
                return B0 + R * getCos();
            }
        } else {
            if (B0 - b0 >= 0) {
                return B0 + R * getCos();
            } else {
                return B0 - R * getCos();
            }
        }
    }


    public QQMessageGone2(Context context) {
        this(context, null);
    }

    public QQMessageGone2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QQMessageGone2(Context context, AttributeSet attrs, int defStyleAttr) {
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

        if (!IS_ACTION_UP) {
            //distance<=500画两个圆
            if (getDistance() <= 500) {
                //r根据distance变化
                if (getDistance() >= 200) {
                    r = 15;
                } else {
                    r = 50 - 35 * getDistance() / 200;
                }
                Log.e("zhaozhu", r + "");

                mPath.moveTo(geta1(), getb1());
                mPath.quadTo(midX, midY, getA1(), getB1());
                mPath.lineTo(getA2(), getB2());
                mPath.quadTo(midX, midY, geta2(), getb2());
                mPath.lineTo(geta1(), getb1());
                mPath.close();
                canvas.drawPath(mPath, mPaint);

                canvas.drawCircle(a0, b0, r, mPaint);
                canvas.drawCircle(A0, B0, R, mPaint);
            } else {
                //distance>500只画一个圆
                canvas.drawCircle(A0, B0, R, mPaint);
            }
        }else {
            //手指释放时的逻辑
            if (getDistance() <= 500) {
                //distance<=500,恢复原状
                r = 50;
                canvas.drawCircle(a0, b0, r, mPaint);
            } else {
                //distance>500只,消失
                // TODO 消失动画

            }

        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        IS_ACTION_UP = false;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastA0 = event.getX();
                lastB0 = event.getY();

                A0 = event.getX();
                B0 = event.getY();

                setMidX();
                setMidY();
                setDeltaX();
                setDeltaY();
                break;
            case MotionEvent.ACTION_MOVE:
                A0 = event.getX();
                B0 = event.getY();

                setMidX();
                setMidY();
                setDeltaX();
                setDeltaY();

                lastA0 = event.getX();
                lastB0 = event.getY();

                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                if (getDistance() > R * 3) {
                    IS_DISAPPEAR_UP = false;
                    IS_ACTION_UP = true;
                    invalidate();
                }
                break;
        }
        return true;
    }
}
