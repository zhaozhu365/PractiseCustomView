package com.example.zhaozhu.practisecustomview.customview02;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.zhaozhu.practisecustomview.UIUtils;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.PropertyValuesHolder;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by zhaozhu on 17/6/8.
 * 沿一个圆路径转转圈的view
 *
 * view的起始点，在以radius为半径的圆的左顶点;
 * 起始点的角度为0，正方向为顺时针方向
 *
 */
public class CirclePathView extends ImageView {

    public CirclePathView(Context context) {
        this(context, null);
    }

    public CirclePathView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CirclePathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private ObjectAnimator mStarScaleXAnimator;
    private ObjectAnimator mStarScaleYAnimator;
    private ValueAnimator angleAnimator;
    private AnimatorSet mAnimatorSet;

    private void init() {
        PropertyValuesHolder starScaleXHolder = PropertyValuesHolder.ofFloat("scaleX", 0, 1.1f, 0.9f, 1, 0.8f);
        PropertyValuesHolder starScaleYHolder = PropertyValuesHolder.ofFloat("scaleY", 0, 1.1f, 0.9f, 1, 0.8f);

        mStarScaleXAnimator = ObjectAnimator.ofPropertyValuesHolder(this, starScaleXHolder);
        mStarScaleXAnimator.setDuration(duration);
        mStarScaleXAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mStarScaleXAnimator.setRepeatMode(ValueAnimator.RESTART);
        mStarScaleXAnimator.setInterpolator(new DecelerateInterpolator());

        mStarScaleYAnimator = ObjectAnimator.ofPropertyValuesHolder(this, starScaleYHolder);
        mStarScaleYAnimator.setDuration(duration);
        mStarScaleYAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mStarScaleYAnimator.setRepeatMode(ValueAnimator.RESTART);
        mStarScaleYAnimator.setInterpolator(new DecelerateInterpolator());

        //起点和终点的角度
        angleAnimator = ValueAnimator.ofFloat(startAngle, endAngle);
        angleAnimator.setDuration(duration);
        angleAnimator.setRepeatCount(ValueAnimator.INFINITE);
        angleAnimator.setRepeatMode(ValueAnimator.RESTART);
        angleAnimator.setInterpolator(new LinearInterpolator());
        angleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (float) valueAnimator.getAnimatedValue();
                updatePosition(value);
            }
        });

        if (mAnimatorSet != null) {
            mAnimatorSet.cancel();
            mAnimatorSet = null;
        }
        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.play(mStarScaleXAnimator)
                .with(mStarScaleYAnimator)
                .with(angleAnimator);
    }


    public float startAngle = -10;//起始角度
    public float endAngle = 90;//终止角度
    public float radiusDp = 13.5f;//路径圆的半径dp
    public long duration = 3000L;//周期

    /**
     * 更新view位置
     */
    private void updatePosition(float value) {
        int radius = UIUtils.dip2px(radiusDp);//旋转半径
        float mFraction = value / 90;
        float angle = (float) (Math.PI / 2 * mFraction);
        float translationX = (float) (radius * (1 - Math.cos(angle)));
        float translationY = (float) (radius * (0 - Math.sin(angle)));
        ViewHelper.setTranslationX(CirclePathView.this, translationX);
        ViewHelper.setTranslationY(CirclePathView.this, translationY);
    }

    public void startAnim() {
        if (mAnimatorSet != null) {
            mAnimatorSet.start();
        } else {
            init();
            mAnimatorSet.start();
        }
    }

    public void stopAnim() {
        if (mAnimatorSet != null) {
            mAnimatorSet.cancel();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.e("zz", "CirclePathView onDetachedFromWindow");
        stopAnim();
    }

}
