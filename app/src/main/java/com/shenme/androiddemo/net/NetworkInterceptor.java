package com.shenme.androiddemo.net;


import android.util.Log;

import java.io.IOException;
import java.net.URLDecoder;

import okhttp3.Interceptor;
import okhttp3.Request;
import okio.Buffer;
import okio.BufferedSource;

/**
 *
 */
public class NetworkInterceptor implements Interceptor {
    private static final String TAG = "NetworkInterceptor";

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request oriRequest = chain.request();
        //打印url
        Log.d(TAG, URLDecoder.decode(oriRequest.toString(), "UTF-8"));
        //打印requestBody
        if ("POST".equals(oriRequest.method())) {
            Buffer buffer1 = new Buffer();
            oriRequest.body().writeTo(buffer1);
            String con = buffer1.readUtf8();
            if (con.length() > 0) {
                Log.d(TAG, "requestBody：" + con);
            } else {
                Log.d(TAG, "requestBody：length=" + con.length());
            }
        }
        // 添加请求头
        //Request newRequest = oriRequest.newBuilder()
        //.addHeader("udid", AppUtil.getSysUdid(CommonApplication.getInstance()))
        //.addHeader("type", CommonApplication.type.toString())
        // build();
        // Log.d("NetworkInterceptor", newRequest.headers().toString());

        //打印responseBody
        okhttp3.Response response = chain.proceed(oriRequest);
        okhttp3.ResponseBody responseBody = response.body();
        if (responseBody.contentLength() != 0) {
            BufferedSource bufferedSource = responseBody.source();
            bufferedSource.request(Long.MAX_VALUE);
            Buffer buffer = bufferedSource.buffer();
            Log.d(TAG, "responseBody：" + buffer.clone().readUtf8());
        } else {
            Log.d(TAG, "responseBody：contentLength=" + responseBody.contentLength());
        }
        return response;
    }

}
