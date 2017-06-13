package com.example.zhaozhu.practisecustomview.customviewgroup;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * ViewDragHelper练习
 * http://blog.csdn.net/lmj623565791/article/details/46858663
 * <p>
 * author: zhaozhu
 * Created on 17/6/13
 */
public class VDHLayout extends LinearLayout {

    private ViewDragHelper mDragger;
    private View mDragView;
    private View mAutoBackView;
    private View mEdgeTrackerView;
    private Point mAutoBackOriginPos = new Point();

    public VDHLayout(Context context) {
        this(context, null);
    }

    public VDHLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VDHLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {

            //是否捕获子view的回调
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                //return true;

                //mEdgeTrackerView禁止直接移动,mEdgeTrackerView通过onEdgeDragStarted回调来捕获
                return child == mDragView || child == mAutoBackView;
            }

            //手指释放的时候回调
            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);

                if (releasedChild == mAutoBackView) {
                    //ViewDragHelper.settleCapturedViewAt内部使用Scroller.startScroll,需要和View.computeScroll配合使用
                    mDragger.settleCapturedViewAt(mAutoBackOriginPos.x, mAutoBackOriginPos.y);
                    invalidate();
                }
            }

            //TODO 侧滑菜单就是通过这里来捕获的子view
            //在边界拖动时回调
            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
                super.onEdgeDragStarted(edgeFlags, pointerId);
                //mEdgeTrackerView禁止直接移动,mEdgeTrackerView通过ViewDragHelper.captureChildView来捕获
                mDragger.captureChildView(mEdgeTrackerView, pointerId);
            }

            //childView横向或者纵向的移动的范围
            @Override
            public int getViewHorizontalDragRange(View child) {
                //return super.getViewHorizontalDragRange(child);
                return getMeasuredWidth() - child.getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
            }
            //childView横向或者纵向的移动的范围
            @Override
            public int getViewVerticalDragRange(View child) {
                //return super.getViewVerticalDragRange(child);
                return getMeasuredHeight() - child.getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
            }

            //child移动的边界控制
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                int leftBound = getPaddingLeft();
                int rightBound = getWidth() - child.getWidth() - getPaddingRight() - leftBound;
                left = Math.min(Math.max(left, leftBound), rightBound);
                return left;
            }
            //child移动的边界控制
            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                int topBound = getPaddingTop();
                int bottomBound = getHeight() - child.getHeight() - getPaddingBottom() - topBound;
                top = Math.min(Math.max(top, topBound), bottomBound);
                return top;
            }

        });
        //在onEdgeDragStarted回调方法中，主动通过captureChildView对其进行捕获，
        //该方法可以绕过tryCaptureView，所以我们的tryCaptureView虽然并为返回true，但却不影响。
        // 注意如果需要使用边界检测需要添加上setEdgeTrackingEnabled
        mDragger.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragger.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragger.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //onLayout结束后，才能获取到子view的位置，获取左上角顶点的坐标
        mAutoBackOriginPos.x = mAutoBackView.getLeft();
        mAutoBackOriginPos.y = mAutoBackView.getTop();
    }

    //ViewDragHelper.settleCapturedViewAt内部使用Scroller.startScroll,需要和View.computeScroll配合使用
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mDragger.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mDragView = getChildAt(0);
        mAutoBackView = getChildAt(1);
        mEdgeTrackerView = getChildAt(2);
    }
}
