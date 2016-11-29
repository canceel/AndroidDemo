package com.shenme.androiddemo.bean.address;

import com.google.gson.annotations.SerializedName;
import com.shenme.androiddemo.base.BaseResult;

import java.util.List;

/**
 * Created by CANC on 2016/11/28.
 */

public class ResponseAddress extends BaseResult {
    @SerializedName("dataList")
    public List<Address> dataList;
}
