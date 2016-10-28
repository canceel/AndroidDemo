package com.shenme.androiddemo.net;


import com.shenme.androiddemo.bean.WelfareResult;
import com.shenme.androiddemo.constants.Constant;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by admin on 2016/6/24.
 */
public interface HttpService {

    @GET(Constant.DATE_10)
    Observable<WelfareResult> getImage();
}
