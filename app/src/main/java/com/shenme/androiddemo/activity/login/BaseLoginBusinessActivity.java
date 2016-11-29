package com.shenme.androiddemo.activity.login;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import com.shenme.androiddemo.base.BaseActivity;
import com.shenme.androiddemo.bean.user.LoginResult;
import com.shenme.androiddemo.net.ApiException;
import com.shenme.androiddemo.net.CodeHandledSubscriber;
import com.shenme.androiddemo.net.RetrofitUtil;
import com.shenme.androiddemo.utils.ToastUtil;
import com.shenme.androiddemo.utils.Utils;


/**
 * allipper 登录逻辑基类
 */
public class BaseLoginBusinessActivity extends BaseActivity {


    public static final String IS_LOGIN = "is_login";
    public static final String PARAM_CELL = "param_cell";
    public static final String PARAM_USER_TYPE = "param_user_type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 发起用户登录
     */
    protected void login(String account, String password, final Dialog dialog) {
        RetrofitUtil.getHttpService().login(account, password)
                .compose(new RetrofitUtil.CommonOptions<LoginResult>())
                .subscribe(new CodeHandledSubscriber<LoginResult>() {
                    @Override
                    protected void onError(ApiException apiException) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        ToastUtil.show(apiException.getDisplayMessage());
                    }

                    @Override
                    protected void onBusinessNext(LoginResult data) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        Utils.saveAccessToken(mContext, data.customer.getAccess_token(), data.customer.getRefresh_token(), data.customer.getExpires_in());
                        //保存登录信息
                        Utils.saveUserInfo(mContext, data.customer);
                        toOriginalPage();
                    }

                    @Override
                    public void onCompleted() {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                    }
                });
    }


    //回到调用地点
    private void toOriginalPage() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(intent);
    }

//    //登录成功后发送广播
//    protected void sendBrodCastToIndex(Boolean isLogin) {
//        Intent it = new Intent(IndexActivity.BC_UPDATE_USER_INFO);
//        it.putExtra(IS_LOGIN, isLogin);
//        sendBroadcast(it);
//    }

}