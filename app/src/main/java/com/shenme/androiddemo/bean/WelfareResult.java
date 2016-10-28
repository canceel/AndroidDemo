package com.shenme.androiddemo.bean;

import com.google.gson.annotations.SerializedName;
import com.shenme.androiddemo.base.BaseResult;

import java.util.List;

/**
 * Created by CANC on 2016/10/18.
 */

public class WelfareResult extends BaseResult {
    @SerializedName("results")
    private List<Welfare> welfares;

    public List<Welfare> getWelfares() {
        return welfares;
    }

    public void setWelfares(List<Welfare> welfares) {
        this.welfares = welfares;
    }
}
