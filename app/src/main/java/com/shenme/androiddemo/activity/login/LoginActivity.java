package com.shenme.androiddemo.activity.login;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.shenme.androiddemo.R;
import com.shenme.androiddemo.utils.ActivityFlagUtils;
import com.shenme.androiddemo.utils.LoadDialogUtil;
import com.shenme.androiddemo.utils.SharedPre;
import com.shenme.androiddemo.utils.SharedPreUtils;
import com.shenme.androiddemo.utils.ToastUtil;
import com.shenme.androiddemo.utils.Utils;
import com.shenme.androiddemo.widget.MyClearEditText;


public class LoginActivity extends BaseLoginBusinessActivity implements View.OnClickListener {
    public static final String IS_FINISH = "is_finish";
    public static final String IS_H5_LOGIN = "is_h5_login";
    public static final String IS_SHOPPING_CART_LOGIN = "IS_SHOPPING_CART_LOGIN";
    public static final String IS_MINE_FRAGMENT_LOGIN = "IS_MINE_FRAGMENT_LOGIN";
    public static final String IS_IMMEDIATE_LOGIN = "IS_IMMEDIATE_LOGIN";
    public static final String IS_FROM_DRINK = "IS_FROM_DRINK";
    public static final String IS_FROM_PICKADDRESS = "IS_FROM_PICKADDRESS";


    private EditText edCellNo;
    private EditText edPassWord;
    private Button btnLogin;
    private Button btnRegister;
    private Button btnForgetPassword;
    private RelativeLayout rlBg;
    private boolean isH5Login = false;
    public boolean isShoppingCartLogin = false;
    public boolean isMineFragment = false;
    public boolean isPickaddress = false;
    public boolean isFromDrink = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        isH5Login = getIntent().getBooleanExtra(IS_H5_LOGIN, false);
        isShoppingCartLogin = getIntent().getBooleanExtra(IS_SHOPPING_CART_LOGIN, false);
        isMineFragment = getIntent().getBooleanExtra(IS_MINE_FRAGMENT_LOGIN, false);
        isPickaddress = getIntent().getBooleanExtra(IS_FROM_PICKADDRESS, false);
        isFromDrink = getIntent().getBooleanExtra(IS_FROM_DRINK, false);
        findViews();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        switch (intent.getFlags()) {
            case Intent.FLAG_ACTIVITY_CLEAR_TOP:
                if (intent.getStringExtra(PARAM_CELL) != null) {
                    String phone = intent.getStringExtra(PARAM_CELL);
                    SharedPreUtils.putString(mContext, SharedPre.User.MOBILE, phone);
                } else if (isH5Login) {
                    Intent intent1 = new Intent();
                    intent.putExtra(IS_H5_LOGIN, true);
                    setResult(RESULT_OK, intent1);
                    onBackPressed();
                } else if (isShoppingCartLogin) {
                    Intent intent1 = new Intent();
                    intent.putExtra(IS_SHOPPING_CART_LOGIN, true);
                    ActivityFlagUtils.setFlag(ActivityFlagUtils.IS_SHOPPING_CART_LOGIN);
                    setResult(RESULT_OK, intent1);
                    onBackPressed();
                } else if (isMineFragment) {
                    Intent intent1 = new Intent();
                    intent.putExtra(IS_MINE_FRAGMENT_LOGIN, true);
                    ActivityFlagUtils.setFlag(ActivityFlagUtils.IS_MINE_FRAGMENT_LOGIN);
                    setResult(RESULT_OK, intent1);
                    onBackPressed();
                } else if (isPickaddress) {
                    Intent intent1 = new Intent();
                    intent.putExtra(IS_FROM_PICKADDRESS, true);
                    ActivityFlagUtils.setFlag(ActivityFlagUtils.IS_FROM_PICKADDRESS);
                    setResult(RESULT_OK, intent1);
                    onBackPressed();
                } else if (isFromDrink) {
                    Intent intent1 = new Intent();
                    intent1.putExtra(IS_FROM_DRINK, true);
                    ActivityFlagUtils.setFlag(ActivityFlagUtils.IS_FROM_DRINK);
                    setResult(RESULT_OK, intent1);
                    onBackPressed();
                }
                if (intent.getBooleanExtra(IS_FINISH, true)) {//来之登录注册界面不需要退出
                    Intent tempIntent = new Intent();
                    tempIntent.putExtra(IS_H5_LOGIN, true);
                    setResult(RESULT_OK, tempIntent);
                    onBackPressed();
                } else {
                    String phone = intent.getStringExtra(PARAM_CELL);
                    if (!TextUtils.isEmpty(phone)) {
                        edCellNo.setText(phone);
                    } else {
                        edCellNo.setText("");
                    }
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.login) {
            checkAndLogin();
            return;
        }
        if (id == R.id.register) {
            register();
            return;
        }
        if (id == R.id.forget_password) {
            forgetPassword();
        }

    }

    private void findViews() {
        edCellNo = (MyClearEditText) findViewById(R.id.cell_no);
        edPassWord = (MyClearEditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.login);
        rlBg = (RelativeLayout) findViewById(R.id.heardboom);
        RelativeLayout.LayoutParams ll = (RelativeLayout.LayoutParams) rlBg.getLayoutParams();
        ll.width = Utils.getScreenWidth(mContext);
        ll.height = (int) (410 * 1f / 640 * Utils.getScreenWidth(mContext));
        rlBg.setLayoutParams(ll);
        btnRegister = (Button) findViewById(R.id.register);
        btnForgetPassword = (Button) findViewById(R.id.forget_password);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        btnForgetPassword.setOnClickListener(this);
        edPassWord.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // 先隐藏键盘
                    ((InputMethodManager) edPassWord.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    LoginActivity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    checkAndLogin();
                    return true;
                }
                return false;
            }
        });
        if (SharedPreUtils.getString(mContext, SharedPre.User.MOBILE) != null) {
            edCellNo.setText(SharedPreUtils.getString(mContext, SharedPre.User.MOBILE));
        }

    }

    //检查用户信息并登录
    private void checkAndLogin() {
        final String account = edCellNo.getText().toString();
        final String password = edPassWord.getText().toString();

        if (TextUtils.isEmpty(account)) {
            ToastUtil.show("请输入手机号码");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            ToastUtil.show("请输入密码");
            return;
        }
        if (!Utils.isNetworkConnected(this)) {
            return;
        }

        final Dialog dialog = LoadDialogUtil.createLoadingDialog(this);
        dialog.show();
        login(account, password, dialog);
    }

    //忘记密码
    private void forgetPassword() {
        Intent intentPassWordActivity = new Intent(this, PassWordActivity.class);
        startActivity(intentPassWordActivity);
    }

    //用户注册
    private void register() {
        Intent intentRegisterActivity = new Intent(this, RegisterOrActivityActivity.class);
//        Intent intentRegisterActivity = new Intent(this, NewRegisterActivity.class);
        startActivity(intentRegisterActivity);
    }

}