package com.shenme.androiddemo.bean.user;

import com.google.gson.annotations.SerializedName;
import com.shenme.androiddemo.base.BaseResult;

public class LoginResult extends BaseResult {
    //用户信息
    @SerializedName("resultData")
    public Customer customer;
}
