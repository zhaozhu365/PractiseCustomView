package com.example.zhaozhu.practisecustomview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;

import com.example.zhaozhu.practisecustomview.customview02.PanelView;
import com.example.zhaozhu.practisecustomview.customview02.SimpleLineChart;

public class MainActivity extends Activity {

    private SimpleLineChart mSimpleLineChart;

    private PanelView mPanelView,mPanelView2;
    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


    }
}
