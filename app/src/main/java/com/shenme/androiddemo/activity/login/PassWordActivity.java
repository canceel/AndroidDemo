package com.shenme.androiddemo.activity.login;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shenme.androiddemo.R;
import com.shenme.androiddemo.utils.LoadDialogUtil;
import com.shenme.androiddemo.utils.ToastUtil;
import com.shenme.androiddemo.utils.Utils;


public class PassWordActivity extends BaseLoginBusinessActivity implements
        View.OnClickListener {
    private static final String TAG = PassWordActivity.class.getSimpleName();

    private EditText edInputSecurityCode;
    private EditText edInputCellNo;
    private EditText edNewPassWord;
    private EditText edConforNewPassWord;
    private CountDownTimer time;
    private Button btnSecurityCode;
    private Button btnModify;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        findViews();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.security_code:
                getSecurityCode();
                break;
            case R.id.modify:
                modifyPassword();
                break;
        }
    }

    private void findViews() {
        edInputCellNo = (EditText) findViewById(R.id.input_cellno);
        edInputSecurityCode = (EditText) findViewById(R.id.input_securitycode);
        edNewPassWord = (EditText) findViewById(R.id.new_password);
        edConforNewPassWord = (EditText) findViewById(R.id.confor_newpassword);
        btnSecurityCode = (Button) findViewById(R.id.security_code);
        btnModify = (Button) findViewById(R.id.modify);
        btnSecurityCode.setOnClickListener(this);
        btnModify.setOnClickListener(this);
        edConforNewPassWord.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // 先隐藏键盘
                    ((InputMethodManager) edConforNewPassWord.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    PassWordActivity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    modifyPassword();
                    return true;
                }
                return false;
            }
        });

        time = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                btnSecurityCode.setClickable(false);
                btnSecurityCode.setText(millisUntilFinished / 1000 + "s");

            }

            @Override
            public void onFinish() {
                btnSecurityCode.setText("获取验证码");
                btnSecurityCode.setClickable(true);
                edInputCellNo.setEnabled(true);
            }
        };
    }

    //忘记密码修改密码
    private void modifyPassword() {
        final String phoneNum = edInputCellNo.getText().toString();
        final String securityCode = edInputSecurityCode.getText().toString();
        final String newPassword = edNewPassWord.getText().toString();
        final String conforNewPassword = edConforNewPassWord.getText().toString();

        if (TextUtils.isEmpty(phoneNum)) {

            ToastUtil.show( "请输入手机号码");
            return;
        }

        if (TextUtils.isEmpty(securityCode)) {
            ToastUtil.show( "请输入手机短信验证码");
            return;
        }
        if (TextUtils.isEmpty(newPassword)) {
            ToastUtil.show( "请输密码");
            return;
        }
        if (TextUtils.isEmpty(conforNewPassword)) {
            ToastUtil.show( "请再次输入密码");
            return;
        }
        if (!(newPassword.equals(conforNewPassword))) {
            ToastUtil.show( "密码不一致");
            return;
        }
        if (!Utils.isNetworkConnected(this)) {
            return;
        }

        final Dialog mDialog = LoadDialogUtil.createLoadingDialog(this);
        mDialog.show();
//        HttpLoad.UserModule.resetPassword(this, TAG, phoneNum, newPassword,securityCode,
//                new ResponseCallback<ResponseBase>(this) {
//
//                    @Override
//                    public void onRequestSuccess(ResponseBase result) {
//                        login(phoneNum, newPassword, mDialog);
//                    }
//
//                    @Override
//                    public void onReuquestFailed(ErrorBase error) {
//                        mDialog.dismiss();
//                        ToastUtil.show( error);
//                    }
//                });
    }

    private void getSecurityCode() {
        final String phoneNum = edInputCellNo.getText().toString();
        if (TextUtils.isEmpty(phoneNum)) {
            ToastUtil.show( "请输入手机号码");
            return;
        }
        if (!Utils.isNetworkConnected(mContext)) {
            return;
        }

        final Dialog dialog = LoadDialogUtil.createLoadingDialog(mContext);
        dialog.show();
//        HttpLoad.UserModule.getMessageCode(mContext,
//                TAG,
//                phoneNum,
//                HttpLoad.UserModule.MESSAGE_MODE_ALIDATEOLDMOBILE,
//                new ResponseCallback<ResponseMessageBean>(mContext) {
//                    @Override
//                    public void onRequestSuccess(ResponseMessageBean result) {
//                        dialog.dismiss();
//                        time.start();
//                        edInputCellNo.setEnabled(false);
//                        ToastUtils.show(mContext, "短信验证码已发送，请注意查收");
//                    }
//
//                    @Override
//                    public void onReuquestFailed(ErrorBase error) {
//                        dialog.dismiss();
//                        ToastUtils.show(mContext, error);
//                    }
//                });
    }

}
