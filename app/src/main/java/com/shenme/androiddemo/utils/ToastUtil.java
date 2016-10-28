package com.shenme.androiddemo.utils;

import android.widget.Toast;

import com.shenme.androiddemo.base.BaseApplication;


/**
 * Created by admin on 2016/7/5.
 */
public class ToastUtil {
    private static boolean isDebug = true;

    public static void show(String s) {
        showShort(s);
    }

    public static void showShort(String s) {
        Toast.makeText(BaseApplication.globalContext, s, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(String s) {
        Toast.makeText(BaseApplication.globalContext, s, Toast.LENGTH_LONG).show();
    }

    public static void showDebug(String s) {
        if (isDebug) {
            showShort(s);
        }
    }
}
