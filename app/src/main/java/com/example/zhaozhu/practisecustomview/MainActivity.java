package com.example.zhaozhu.practisecustomview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import com.example.zhaozhu.practisecustomview.customview02.PanelView;
import com.example.zhaozhu.practisecustomview.customview02.SimpleLineChart;
import com.example.zhaozhu.practisecustomview.customviewgroup.HSlidingPaneLayout2;

public class MainActivity extends Activity {

    private SimpleLineChart mSimpleLineChart;

    private PanelView mPanelView, mPanelView2;
    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

//        mSimpleLineChart = (SimpleLineChart) findViewById(R.id.simpleLineChart);
//        String[] xItem = {"1", "2", "3", "4", "5", "6", "7"};
//        String[] yItem = {"10k", "20k", "30k", "40k", "50k"};
//        if (mSimpleLineChart == null)
//            Log.e("wing", "null!!!!");
//        mSimpleLineChart.setXItem(xItem);
//        mSimpleLineChart.setYItem(yItem);
//        HashMap<Integer, Integer> pointMap = new HashMap();
//        for (int i = 0; i < xItem.length; i++) {
//            pointMap.put(i, (int) (Math.random() * 5));
//        }
//        mSimpleLineChart.setData(pointMap);

//        mPanelView = (PanelView) findViewById(R.id.panelView);
//        mPanelView2 = (PanelView) findViewById(R.id.panelView2);
//        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
//        mPanelView.setText("已完成");
//        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                mPanelView.setPercent(progress);
//                mPanelView2.setPercent(progress);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });

        //TODO 消息去除控件自动添加到root上
//        QQMessageGone2 messageGone2 = new QQMessageGone2(this);
////        messageGone2.setBackgroundColor(Color.parseColor("#123456"));
//        messageGone2.seta0(500);
//        messageGone2.setb0(500);
//        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        addContentView(messageGone2, layoutParams);

        /**在ListView中如何操作*/
        //TODO 在列表中,小红点消失后,需要更改对应item数据,更改完后,如何刷新界面呢？
        //TODO (方法1)更改完数据源后,用recyclerview的notifyItemDataChanged,就可以更新单个item,不需要将整个ListView刷新
        //TODO (方法2)那要不使用RecyclerView呢？那就在更改完数据源后，调用ListView.onItemClickListener，可以获取到item中的
        //TODO 所有的view，然后直接调用小红点view.setVisibility(GONE)即可。
        /***/

        //TODO QQMessageGone3 的示例
//        setContentView(R.layout.activity_main2);
//        final ImageView bubbles = (ImageView) findViewById(R.id.bubbles_anim);
//        final AnimationDrawable drawable = (AnimationDrawable) bubbles.getDrawable();
//
//        QQMessageGone3 gone3 = (QQMessageGone3) findViewById(R.id.QQMessageGone3_test);
//        gone3.seta0(500);
//        gone3.setb0(500);
//        gone3.setRedPointDismissListener(new QQMessageGone3.RedPointDismissListener() {
//            @Override
//            public void onDismiss(float x, float y) {
//                //设置bubbles的位置
//                bubbles.setTranslationX(x - bubbles.getWidth() / 2);
//                bubbles.setTranslationY(y - bubbles.getHeight() / 2);
//                bubbles.setVisibility(View.VISIBLE);
//                if (drawable != null) {
//                    if (drawable.isRunning()) {
//                        drawable.stop();
//                        drawable.start();
//                    } else {
//                        drawable.start();
//                    }
//                }
//            }
//
//            @Override
//            public void onFinishAnim(float x, float y) {
//                bubbles.setVisibility(View.GONE);
//                if (drawable != null) {
//                    if (drawable.isRunning()) {
//                        drawable.stop();
//                    }
//                }
//            }
//
//            @Override
//            public void onShakeAnim(float x, float y) {
//
//            }
//        });

        //TODO pathMeasure示例
//        setContentView(R.layout.activity_main_pathmeasure);
//        Button run;
//        RelativeLayout rlt_animation_layout;
//        run = (Button) findViewById(R.id.but_run);
//        rlt_animation_layout = (RelativeLayout) findViewById(R.id.rlt_animation_layout);
//        rlt_animation_layout.setVisibility(View.VISIBLE);
//
//        final FllowerAnimation fllowerAnimation;
//        fllowerAnimation = new FllowerAnimation(this);
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
//        fllowerAnimation.setLayoutParams(params);
//        rlt_animation_layout.addView(fllowerAnimation);
//
//        run.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fllowerAnimation.startAnimation();
//            }
//        });

        //TODO 公示计算获取路径
//        setContentView(R.layout.activity_main3);
//        RotationImageView rotationImageView = (RotationImageView) findViewById(R.id.RotationImageView);
//        AnimImageView animImageView = (AnimImageView) findViewById(R.id.AnimImageView);
//        CirclePathView startView = (CirclePathView) findViewById(R.id.CirclePathView);
//
//        rotationImageView.startAnim();
//        startView.startAnim();
//
//        PropertyValuesHolder rotationHolder = PropertyValuesHolder.ofFloat("rotation", 0, 360);
//        PropertyValuesHolder alphaHolder = PropertyValuesHolder.ofFloat("alpha", 0, 1, 0);
//        animImageView.init(rotationHolder, alphaHolder);
//        animImageView.startAnim();

        //TODO HSlidingPaneLayout mOverhangSize = -32px;
//        setContentView(R.layout.activity_main5);
//        final HSlidingPaneLayout slidingPaneLayout = (HSlidingPaneLayout) findViewById(R.id.slide);
//        final View leftMenu = findViewById(R.id.left_menu);
//        final View content = findViewById(R.id.right_content);
//
//        slidingPaneLayout.setSliderFadeColor(Color.TRANSPARENT);
//        slidingPaneLayout.setPanelSlideListener(new HSlidingPaneLayout.PanelSlideListener() {
//            @Override
//            public void onPanelSlide(View panel, float slideOffset) {
//                Log.e("zz", "content " + (content == panel) + " leftMenu " + (leftMenu == panel) + " slideOffset = " + slideOffset);
//            }
//            @Override
//            public void onPanelOpened(View panel) {
//                Log.e("zz", "onPanelOpened");
//            }
//            @Override
//            public void onPanelClosed(View panel) {
//                Log.e("zz", "onPanelClosed");
//            }
//        });
//
//        content.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                slidingPaneLayout.openPane();
//            }
//        });

//        //TODO 原生SlidingPaneLayout mOverhangSize = 32dp ;所以左侧菜单会空出来一块
//        setContentView(R.layout.activity_main6);
//        final SlidingPaneLayout slidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.slide);
//        final View leftMenu = findViewById(R.id.left_menu);
//        final View content = findViewById(R.id.right_content);
//
//        slidingPaneLayout.setSliderFadeColor(Color.TRANSPARENT);
//        slidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
//            @Override
//            public void onPanelSlide(View panel, float slideOffset) {
//                Log.e("zz", "content " + (content == panel) + " leftMenu " + (leftMenu == panel) + " slideOffset = " + slideOffset);
//            }
//            @Override
//            public void onPanelOpened(View panel) {
//                Log.e("zz", "onPanelOpened");
//            }
//            @Override
//            public void onPanelClosed(View panel) {
//                Log.e("zz", "onPanelClosed");
//            }
//        });
//
//        content.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                slidingPaneLayout.openPane();
//            }
//        });
//        leftMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                slidingPaneLayout.closePane();
//            }
//        });

        //TODO HSlidingPaneLayout2 mOverhangSize = 0px;
        setContentView(R.layout.activity_main7);
        final HSlidingPaneLayout2 slidingPaneLayout = (HSlidingPaneLayout2) findViewById(R.id.slide);
        final View leftMenu = findViewById(R.id.left_menu);
        final View content = findViewById(R.id.right_content);

        slidingPaneLayout.setSliderFadeColor(Color.TRANSPARENT);
        slidingPaneLayout.setPanelSlideListener(new HSlidingPaneLayout2.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.e("zz", "content " + (content == panel) + " leftMenu " + (leftMenu == panel) + " slideOffset = " + slideOffset);
            }

            @Override
            public void onPanelOpened(View panel) {
                Log.e("zz", "onPanelOpened");
            }

            @Override
            public void onPanelClosed(View panel) {
                Log.e("zz", "onPanelClosed");
            }
        });

        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidingPaneLayout.openPane();
            }
        });
        leftMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidingPaneLayout.closePane();
            }
        });



    }
}
