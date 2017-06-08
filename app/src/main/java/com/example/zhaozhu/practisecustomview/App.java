package com.example.zhaozhu.practisecustomview;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhaozhu on 17/6/8.
 */

public class App extends Application {

    //全局应用上下文
    private static Context mAppContext;
    private static Context mAppContextTmp;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContextTmp = this;
        //初始化全局Application
        mAppContext = this;
        //初始化Application
    }

    /*
     * 获得全局上下文
     */
    public static Context getAppContext() {
        if (mAppContext == null) {
            return mAppContextTmp;
        }
        return mAppContext;
    }


}
