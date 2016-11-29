package com.shenme.androiddemo.utils;

/**
 * Created by CANC on 2016/7/25.
 */
public class ActivityFlagUtils {

    public static final String IS_SHOPPING_CART_LOGIN = "IS_SHOPPING_CART_LOGIN";
    public static final String IS_MINE_FRAGMENT_LOGIN = "IS_MINE_FRAGMENT_LOGIN";
    public static final String IS_FROM_DRINK = "IS_FROM_DRINK";
    public static final String IS_FROM_PICKADDRESS = "IS_FROM_DRINK";

    public static String mFlag = null;

    public static String getFlag() {
        return mFlag;
    }

    public static void setFlag(String mFlag) {
        ActivityFlagUtils.mFlag = mFlag;
    }
}
