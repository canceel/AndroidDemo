package com.shenme.androiddemo.bean.product;

import com.google.gson.annotations.SerializedName;
import com.shenme.androiddemo.base.BaseResult;

/**
 * Created by CANC on 2016/11/28.
 */

public class ResponseProduct extends BaseResult {

    @SerializedName("resultData")
    public Product resultData;
}
