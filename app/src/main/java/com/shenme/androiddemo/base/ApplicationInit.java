package com.shenme.androiddemo.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2015/11/11.
 */
public class ApplicationInit extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

}
