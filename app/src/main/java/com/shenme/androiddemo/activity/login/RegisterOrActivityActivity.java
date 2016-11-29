package com.shenme.androiddemo.activity.login;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shenme.androiddemo.R;
import com.shenme.androiddemo.base.BaseActivity;
import com.shenme.androiddemo.bean.user.ResponseUserType;
import com.shenme.androiddemo.net.ApiException;
import com.shenme.androiddemo.net.CodeHandledSubscriber;
import com.shenme.androiddemo.net.RetrofitUtil;
import com.shenme.androiddemo.utils.LoadDialogUtil;
import com.shenme.androiddemo.utils.ToastUtil;
import com.shenme.androiddemo.utils.Utils;
import com.shenme.androiddemo.widget.MyClearEditText;


public class RegisterOrActivityActivity extends BaseActivity implements
        View.OnClickListener {

    private RelativeLayout headRelativeLayout;
    private MyClearEditText cellEditText;
    private Button confirmButton;
    private TextView gotoLoginTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_or);
        findViews();
    }

    private void findViews() {
        headRelativeLayout = (RelativeLayout) findViewById(R.id.head);
        cellEditText = (MyClearEditText) findViewById(R.id.cell);
        confirmButton = (Button) findViewById(R.id.confirm);
        gotoLoginTextView = (TextView) findViewById(R.id.gotoLogin);
        gotoLoginTextView.setOnClickListener(this);
        confirmButton.setOnClickListener(this);
        final ImageView cell_x = (ImageView) findViewById(R.id.cell_x);
        cell_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cellEditText.setText("");
            }
        });
        cellEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().isEmpty()) {
                    cell_x.setVisibility(View.VISIBLE);
                } else {
                    cell_x.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.gotoLogin) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra(LoginActivity.IS_FINISH, false);
            intent.putExtra(LoginActivity.PARAM_CELL, cellEditText.getText().toString());
            mContext.startActivity(intent);
        }
        if (id == R.id.confirm) {
            checkPhoneInfo();
        }
    }

    //检查注册手机信息
    private void checkPhoneInfo() {
        final String phone = cellEditText.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.show("请输入手机号码");
            return;
        }

        if (phone.length() != 11) {
            ToastUtil.show("请输入正确的手机号码");
            return;
        }

        if (!Utils.isNetworkConnected(mContext)) {
            return;
        }

        final Dialog mDialog = LoadDialogUtil.createLoadingDialog(mContext);
        mDialog.show();
        RetrofitUtil.getHttpService().getUserType(phone)
                .compose(new RetrofitUtil.CommonOptions<ResponseUserType>())
                .subscribe(new CodeHandledSubscriber<ResponseUserType>() {
                    @Override
                    protected void onError(ApiException apiException) {
                        if (mDialog != null) {
                            mDialog.dismiss();
                        }
                        ToastUtil.show(apiException.getMessage());

                    }

                    @Override
                    protected void onBusinessNext(ResponseUserType data) {
                        if (mDialog != null) {
                            mDialog.dismiss();
                        }
                        handleUserType(phone, data);
                    }

                    @Override
                    public void onCompleted() {
                        if (mDialog != null) {
                            mDialog.dismiss();
                        }

                    }
                });
    }

    //根据用户类型处理注册流程
    private void handleUserType(String phone, ResponseUserType result) {
        switch (result.customer.getUserType()) {
            case ResponseUserType.TYPE_ONLINE_NEW:
                ToastUtil.show("该手机号已注册，请登录");
                Intent intent = new Intent(mContext, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(LoginActivity.IS_FINISH, false);
                intent.putExtra(BaseLoginBusinessActivity.PARAM_CELL, phone);
                mContext.startActivity(intent);
                break;
            case ResponseUserType.TYPE_OFFLINE_OLD:
                Intent intentOld = new Intent(mContext, OldUserRegistActivity.class);
                intentOld.putExtra(BaseLoginBusinessActivity.PARAM_CELL, phone);
                intentOld.putExtra(BaseLoginBusinessActivity.PARAM_USER_TYPE, result.customer.getUserType());
                startActivity(intentOld);
                break;
            case ResponseUserType.TYPE_NEW:
            case ResponseUserType.TYPE_ONLINE_NO:
                Intent intentOnline = new Intent(mContext, NewRegisterActivity.class);
                intentOnline.putExtra(BaseLoginBusinessActivity.PARAM_CELL, phone);
                intentOnline.putExtra(BaseLoginBusinessActivity.PARAM_USER_TYPE, result.customer.getUserType());
                startActivity(intentOnline);
                break;
        }
    }

}


