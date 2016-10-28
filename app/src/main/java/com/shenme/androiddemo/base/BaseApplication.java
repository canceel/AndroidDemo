package com.shenme.androiddemo.base;

import android.app.Application;
import android.content.Context;
import android.support.v7.appcompat.BuildConfig;

import com.facebook.drawee.backends.pipeline.Fresco;

import butterknife.ButterKnife;

/**
 * Created by admin on 2016/6/29.
 */
public class BaseApplication extends Application {
    public static Context globalContext;

    @Override
    public void onCreate() {
        super.onCreate();
        globalContext = getApplicationContext();
        ButterKnife.setDebug(BuildConfig.DEBUG);
        Fresco.initialize(this);
    }
}
