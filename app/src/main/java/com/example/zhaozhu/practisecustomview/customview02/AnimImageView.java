package com.example.zhaozhu.practisecustomview.customview02;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.PropertyValuesHolder;
import com.nineoldandroids.animation.ValueAnimator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaozhu on 17/6/8.
 * 动画view
 */
public class AnimImageView extends ImageView {
    public AnimImageView(Context context) {
        this(context, null);
    }

    public AnimImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private AnimatorSet mAnimatorSet;

    public boolean isInited(){
        return mAnimatorSet != null;
    }

    /**
     * 只允许初始化一次
     */
    public void init(PropertyValuesHolder ... propertyValuesHolder) {
        if (isInited())
            return;

        if (propertyValuesHolder == null || propertyValuesHolder.length == 0) {
            return;
        }
        List<Animator> items = new ArrayList<>();
        for (PropertyValuesHolder holder : propertyValuesHolder) {
            ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(this, holder);
            animator.setDuration(duration);
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.setRepeatMode(ValueAnimator.RESTART);
            animator.setInterpolator(new LinearInterpolator());
            items.add(animator);
        }
        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(items);
    }

    public long duration = 5000L;//周期

    public void startAnim() {
        if (mAnimatorSet != null) {
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
        Log.e("zz", "AnimImageView onDetachedFromWindow");
        stopAnim();
    }
}
