package com.haolefu.dmall;

import android.app.Application;

import com.mob.MobSDK;

/**
 * Created by mxlk on 2018/8/3.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MobSDK.init(this);
    }
}
