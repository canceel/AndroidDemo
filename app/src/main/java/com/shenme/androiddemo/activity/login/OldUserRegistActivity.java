package com.shenme.androiddemo.activity.login;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.shenme.androiddemo.R;
import com.shenme.androiddemo.utils.IDorCardUtil;
import com.shenme.androiddemo.utils.LoadDialogUtil;
import com.shenme.androiddemo.utils.ToastUtil;
import com.shenme.androiddemo.utils.Utils;


public class OldUserRegistActivity extends BaseLoginBusinessActivity implements
        View.OnClickListener {

    private EditText oldSecurityCode;
    private EditText etIdCard;
    private EditText oldPwd;
    private EditText conforPwd;
    private Button btnSecurityCode;
    private Button btnRegister;
    private ImageView ivCheckBox;
    private TextView tvUserCheck;
    private TextView tvAgreement;
    private CountDownTimer time;
    private String phone;
    private boolean aggrementChecked = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_user_regist);
        findViews();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.security_code) {
            getSecurityCode();
            return;
        }
        if (id == R.id.register) {
            register();
        }
        if (id == R.id.tv_user_check || id == R.id.iv_check_box) {
            aggrementChecked = !aggrementChecked;
            //设置按钮
            ivCheckBox.setImageResource(aggrementChecked ? R.mipmap.icon_check_box_checked : R.mipmap.icon_check_box);
        }
        if (id == R.id.tv_agrement) {
            Intent intent = new Intent(this, ServiceAgreementActivity.class);
            startActivity(intent);
        }
    }

    private void findViews() {
        phone = getIntent().getStringExtra(PARAM_CELL);

        oldSecurityCode = (EditText) findViewById(R.id.old_input_securitycode);
        etIdCard = (EditText) findViewById(R.id.old_input_id_card);
        oldPwd = (EditText) findViewById(R.id.old_input_password);
        conforPwd = (EditText) findViewById(R.id.old_confor_password);
        btnSecurityCode = (Button) findViewById(R.id.security_code);
        btnRegister = (Button) findViewById(R.id.register);
        ivCheckBox = (ImageView) findViewById(R.id.iv_check_box);
        tvUserCheck = (TextView) findViewById(R.id.tv_user_check);
        tvAgreement = (TextView) findViewById(R.id.tv_agrement);
        btnSecurityCode.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        ivCheckBox.setOnClickListener(this);
        tvUserCheck.setOnClickListener(this);
        tvAgreement.setOnClickListener(this);

        oldSecurityCode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // 先隐藏键盘
                    ((InputMethodManager) oldSecurityCode.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    OldUserRegistActivity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    register();
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
                btnSecurityCode.setClickable(true);
                btnSecurityCode.setText("获取验证码");
            }
        };

    }


    //注册并登录
    private void register() {
        final String cardCode = etIdCard.getText().toString();
        final String securityCode = oldSecurityCode.getText().toString();
        final String password = oldPwd.getText().toString();
        final String conforPassword = conforPwd.getText().toString();

        if (TextUtils.isEmpty(cardCode)) {
            ToastUtil.show("请输入积分卡号或者身份证号");
            return;
        }
        if (!IDorCardUtil.isIDCard(cardCode) && !IDorCardUtil.isIntegrationCard(cardCode)) {
            ToastUtil.show("积分卡或者身份证错误");
            return;
        }
        if (TextUtils.isEmpty(securityCode)) {
            ToastUtil.show("请输入手机短信验证码");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtil.show("请输入密码");
            return;
        }
        if (password.length() < 6) {
            ToastUtil.show("密码长度必须大于6位");
            return;
        }
        if (TextUtils.isEmpty(conforPassword)) {
            ToastUtil.show("请再次输入密码");
            return;
        }
        if (conforPassword.length() < 6) {
            ToastUtil.show("密码长度必须大于6位");
            return;
        }
        if (!(password.equals(conforPassword))) {
            ToastUtil.show("密码不一致");
            return;
        }

        if (!aggrementChecked) {
            ToastUtil.show("请同意注册条款");
            return;
        }

        if (!Utils.isNetworkConnected(mContext)) {
            return;
        }

        final Dialog mDialog = LoadDialogUtil.createLoadingDialog(mContext);
        mDialog.show();
//        HttpLoad.UserModule.activateOldUser(mContext,
//                TAG,
//                phone,
//                password,
//                cardCode,
//                "",
//                securityCode,
//                new ResponseCallback<RegistResult>(mContext) {
//                    @Override
//                    public void onRequestSuccess(RegistResult result) {
//                        login(phone, password, mDialog);
//                    }
//
//                    @Override
//                    public void onReuquestFailed(ErrorBase error) {
//                        mDialog.dismiss();
//                        ToastUtil.show( error.message);
//                    }
//                });
    }

    //发送手机短信验证码
    private void getSecurityCode() {
        if (!Utils.isNetworkConnected(mContext)) {
            return;
        }
        final Dialog dialog = LoadDialogUtil.createLoadingDialog(mContext);
        dialog.show();
//        HttpLoad.UserModule.getMessageCode(mContext,
//                TAG,
//                phone,
//                HttpLoad.UserModule.MESSAGE_MODE_REGISTCELLPHONE,
//                new ResponseCallback<ResponseMessageBean>(mContext) {
//                    @Override
//                    public void onRequestSuccess(ResponseMessageBean result) {
//                        dialog.dismiss();
//                        time.start();
//                        ToastUtil.show( "短信验证码已发送，请注意查收");
//                    }
//
//                    @Override
//                    public void onReuquestFailed(ErrorBase error) {
//                        dialog.dismiss();
//                        ToastUtil.show( error.message);
//                    }
//                });
    }

}
