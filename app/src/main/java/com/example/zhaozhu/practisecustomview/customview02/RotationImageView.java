package com.example.zhaozhu.practisecustomview.customview02;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.PropertyValuesHolder;
import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by zhaozhu on 17/6/8.
 * 自动旋转的view
 */
public class RotationImageView extends ImageView {
    public RotationImageView(Context context) {
        this(context, null);
    }

    public RotationImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotationImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private ObjectAnimator mAnimator;

    private void init() {
        PropertyValuesHolder holder = PropertyValuesHolder.ofFloat("rotation", 0, 360);
        mAnimator = ObjectAnimator.ofPropertyValuesHolder(this, holder);
        mAnimator.setDuration(duration);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.setRepeatMode(ValueAnimator.RESTART);
        mAnimator.setInterpolator(new LinearInterpolator());
    }

    public long duration = 5000L;//周期

    public void startAnim() {
        if (mAnimator != null) {
            mAnimator.start();
        } else {
            init();
            mAnimator.start();
        }
    }

    public void stopAnim() {
        if (mAnimator != null) {
            mAnimator.cancel();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.e("zz", "RotationImageView onDetachedFromWindow");
        stopAnim();
    }
}
