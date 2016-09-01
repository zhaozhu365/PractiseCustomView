package com.example.zhaozhu.practisecustomview.customview02;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by zhaozhu on 16/8/29.
 */
public class QQMessageGone2 extends View {

    private Paint mPaint;
    private Path mPath;

    private float mPaintStrokeWidth = 3;

    //固定的圆
    private float r = 50;
//    private float a0 = 300;
//    private float b0 = 200;
    private float a0;
    private float b0;

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

    // bitmap画笔
    private Paint mBitmapPaint;
    // 图片
    private Bitmap mBitmap;

    //Bitmap相关矩形
    // 原图截取的矩形
    private Rect mSrcRect;
    // 目标位置
    private RectF mDestRect;

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
        //初始化paint
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(mPaintStrokeWidth);

        //初始化path
        mPath = new Path();

        //初始化Bitmap画笔
        mBitmapPaint = new Paint();
        mBitmapPaint.setFilterBitmap(true);
        mBitmapPaint.setDither(true);

        //初始化加载图片
        // TODO 在ValueAnimator中加载图片

        //初始化Bitmap相关矩形
        mSrcRect = new Rect();
        mDestRect = new RectF();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        //获取view的初始位置坐标
        //注意,这里的left,top,right,bottom是view相对与父布局的坐标
        //而a0,b0是相对于view自身的坐标,所以不能是(right + left) / 2,必须是(right - left) / 2
        a0 = (right - left) / 2;
        b0 = (bottom - top) / 2;
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
                //Log.e("zhaozhu", r + "");

                mPath.moveTo(geta1(), getb1());
                mPath.quadTo(midX, midY, getA1(), getB1());
                mPath.lineTo(getA2(), getB2());
                mPath.quadTo(midX, midY, geta2(), getb2());
                mPath.lineTo(geta1(), getb1());
                mPath.close();
                canvas.drawPath(mPath, mPaint);

                canvas.drawCircle(a0, b0, r, mPaint);
                // 当两个圆重合时,只画一个就可以
                if (getDistance() != 0) {
                    canvas.drawCircle(A0, B0, R, mPaint);
                }
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
                // TODO 消失动画,用ValueAnimator来进行时间控制
                if (mBitmap != null) {
                    mSrcRect.set(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
                    mDestRect.set(A0 - R, B0 - R, A0 + R, B0 + R);
                    canvas.drawBitmap(mBitmap, mSrcRect, mDestRect, mBitmapPaint);
                }
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
                    //invalidate();
                    start();
                }
                break;
        }
        return true;
    }

    // ValueAnimator的回调
    boolean flag1 = true;
    boolean flag2 = true;
    boolean flag3 = true;
    boolean flag4 = true;
    boolean flag5 = true;
    boolean flag6 = true;

    public void start(){
        final ValueAnimator animator = ValueAnimator.ofInt(1, 6);

        flag1 = true;
        flag2 = true;
        flag3 = true;
        flag4 = true;
        flag5 = true;
        flag6 = true;

        animator.setDuration(660);
        animator.setRepeatCount(0);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                switch (value){
                    case 1:
                        if (flag1) {
                            flag1 = false;
                            mBitmap = ((BitmapDrawable)getResources().getDrawable(com.example.zhaozhu.practisecustomview.R.drawable.tips_bubble_idp)).getBitmap();
                            postInvalidate();
                            Log.e("zhaozhu", value + "");
                        }
                        break;
                    case 2:
                        if (flag2) {
                            flag2 = false;
                            mBitmap = ((BitmapDrawable)getResources().getDrawable(com.example.zhaozhu.practisecustomview.R.drawable.tips_bubble_idq)).getBitmap();
                            postInvalidate();
                            Log.e("zhaozhu", value + "");
                        }
                        break;
                    case 3:
                        if (flag3) {
                            flag3 = false;
                            mBitmap = ((BitmapDrawable)getResources().getDrawable(com.example.zhaozhu.practisecustomview.R.drawable.tips_bubble_idr)).getBitmap();
                            postInvalidate();
                            Log.e("zhaozhu", value + "");
                        }
                        break;
                    case 4:
                        if (flag4) {
                            flag4 = false;
                            mBitmap = ((BitmapDrawable)getResources().getDrawable(com.example.zhaozhu.practisecustomview.R.drawable.tips_bubble_ids)).getBitmap();
                            postInvalidate();
                            Log.e("zhaozhu", value + "");
                        }
                        break;
                    case 5:
                        if (flag5) {
                            flag5 = false;
                            mBitmap = ((BitmapDrawable)getResources().getDrawable(com.example.zhaozhu.practisecustomview.R.drawable.tips_bubble_idt)).getBitmap();
                            postInvalidate();
                            Log.e("zhaozhu", value + "");
                        }
                        break;
                    case 6:
                        if (flag6) {
                            flag6 = false;
                            //TODO 如何回收？下面这种回收方式在第二次使用时会报异常
                            //mBitmap.recycle();
                            mBitmap = null;
                            postInvalidate();
                            Log.e("zhaozhu", value + "");
                        }
                        break;
                }
            }
        });
        animator.start();
    }
}
