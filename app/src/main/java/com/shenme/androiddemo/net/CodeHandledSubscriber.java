package com.shenme.androiddemo.net;

import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.shenme.androiddemo.base.BaseResult;

import org.json.JSONException;

import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by admin on 2016/6/30.
 */
public abstract class CodeHandledSubscriber<T> extends Subscriber<T> {
    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;
    private static final String INVALID_TOKEN = "10005";
    private static final String REQUEST_SUCCESS = "20000";
    private static final String SYSTEM_BUSY = "10010";
    //权限不足
    private static final String INSUFFICIENT_PERMISSIONS = "30010";
    //出错提示
    private final String networkMsg = "网络错误";
    private final String parseMsg = "解析错误";
    private final String unknownMsg = "未知错误";

    @Override
    public void onError(Throwable e) {
        Throwable throwable = e;
//        LogUtil.t(e);
        while (throwable.getCause() != null) {
            e = throwable;
            throwable = throwable.getCause();
        }
        ApiException ex;
        if (e instanceof HttpException) {
            /*均视为网络错误*/
            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, httpException.code());
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                    // 权限错误，需要实现
                    // onPermissionError(ex);
                    // break;
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex.setDisplayMessage(networkMsg);
                    onError(ex);
                    break;
            }
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            /*均视为解析错误*/
            ex = new ApiException(e, ApiException.PARSE_ERROR);
            ex.setDisplayMessage(parseMsg);
            onError(ex);
        } else if (e instanceof NetStatusException) {
            /*网络状态错误*/
            NetStatusException statusException = (NetStatusException) e;
            ex = new ApiException(statusException, statusException.getErrorCode());
            ex.setDisplayMessage(statusException.getMessage());
            onError(ex);
        } else if (e instanceof SocketTimeoutException) {
            /*网络超时*/
            ex = new ApiException(e, ApiException.UNKNOWN);
            ex.setDisplayMessage("网络超时");
            onError(ex);
        } else if (e instanceof ResultException) {
            /*服务器返回错误*/
            ResultException resultException = (ResultException) e;
            ex = new ApiException(resultException, resultException.getErrorCode());
            ex.setDisplayMessage(resultException.getMessage());
            onError(ex);
        } else {
            /*未知错误*/
            ex = new ApiException(e, ApiException.UNKNOWN);
            ex.setDisplayMessage(unknownMsg);
            onError(ex);
        }
    }

    protected abstract void onError(ApiException apiException);

    protected abstract void onBusinessNext(T data);

    @Override
    public void onNext(T t) {
        if (t instanceof BaseResult) {
            BaseResult baseResultEntity = (BaseResult) t;
            if (REQUEST_SUCCESS.equals(baseResultEntity.getStatus())) {
                onBusinessNext(t);
            } else {
                onError(new ApiException(Integer.parseInt(baseResultEntity.getStatus()), baseResultEntity.getMessage()));
            }
        }
    }
}
