package com.shenme.androiddemo.bean.shoppingcart;

import com.google.gson.annotations.SerializedName;
import com.shenme.androiddemo.base.BaseResult;

/**
 * Created by CANC on 2016/11/28.
 */

public class ShoppingCartResult extends BaseResult {
    @SerializedName("resultData")
    public ResponseShoppingCart data;
}
