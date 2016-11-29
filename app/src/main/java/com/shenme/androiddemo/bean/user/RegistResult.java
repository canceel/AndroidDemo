package com.shenme.androiddemo.bean.user;

import com.shenme.androiddemo.base.BaseResult;

public class RegistResult extends BaseResult {
    //返回该账户对应的会员层级的会员编码字段
    public String member_id;
    public String card_id;
    //该账户对应的会员层级的昵称
    public String alias;
}
