//
//  UserDetailResult.h
//  ShenghuoJia
//
//  Created by zoulinlin on 15/7/11.
//  Copyright (c) 2015年 YongHui. All rights reserved.
//

package com.shenme.androiddemo.bean.user;

import com.google.gson.annotations.SerializedName;
import com.shenme.androiddemo.base.BaseResult;

public class UserDetailResult extends BaseResult {
    @SerializedName("resultData")
    public Customer customer;
}
