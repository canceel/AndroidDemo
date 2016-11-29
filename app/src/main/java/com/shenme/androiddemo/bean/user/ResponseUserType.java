package com.shenme.androiddemo.bean.user;

import com.google.gson.annotations.SerializedName;
import com.shenme.androiddemo.base.BaseResult;

public class ResponseUserType extends BaseResult {

    public final static String TYPE_NEW = "1";
    public final static String TYPE_OFFLINE_OLD = "2";
    public final static String TYPE_ONLINE_NO = "3";
    public final static String TYPE_ONLINE_NEW = "4";


    /**
     * customer_type : 4 ------------1(新用户)，2(线下老用户),3(已是线上会员需要激活),4(线上会员)
     * email :
     * mobile : 13063390926
     * registeredchannel : STORE
     */
    @SerializedName("resultData")
    public Customer customer;

}
