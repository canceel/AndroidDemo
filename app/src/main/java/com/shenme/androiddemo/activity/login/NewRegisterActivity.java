package com.shenme.androiddemo.activity.login;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shenme.androiddemo.R;
import com.shenme.androiddemo.bean.user.LoginResult;
import com.shenme.androiddemo.bean.user.ResponseUserType;
import com.shenme.androiddemo.net.ApiException;
import com.shenme.androiddemo.net.CodeHandledSubscriber;
import com.shenme.androiddemo.net.RetrofitUtil;
import com.shenme.androiddemo.utils.LoadDialogUtil;
import com.shenme.androiddemo.utils.ToastUtil;
import com.shenme.androiddemo.utils.Utils;

import java.util.HashMap;
import java.util.Map;


public class NewRegisterActivity extends BaseLoginBusinessActivity implements
        View.OnClickListener {
    private static final String TAG = NewRegisterActivity.class.getSimpleName();

    private EditText edInputSecurityCode;
    private EditText edInputPassWord;
    private EditText edConforPassWord;
    private CountDownTimer time;
    private Button btnSecurityCode;
    private Button btnRegister;
    private String phone;
    private TextView title;
    private ImageView ivCheckBox;
    private TextView tvUserCheck;
    private TextView tvAgreement;
    private String usertype;
    private boolean aggrementChecked = true;


    private RelativeLayout confor_password_rl;
    private RelativeLayout input_password_ll;
    private RelativeLayout security_code_rl;
    private ImageView input_x;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViews();
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.security_code:
                getSecurityCode();
                break;
            case R.id.register:
                register();
                break;
            case R.id.tv_user_check:
            case R.id.iv_check_box:
                aggrementChecked = !aggrementChecked;
                //设置按钮
                ivCheckBox.setImageResource(aggrementChecked ? R.mipmap.icon_check_box_checked : R.mipmap.icon_check_box);
                break;
            case R.id.tv_agrement:
                Intent intent = new Intent(this, ServiceAgreementActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void findViews() {
        phone = getIntent().getStringExtra(PARAM_CELL);
        usertype = getIntent().getStringExtra(PARAM_USER_TYPE);

        input_password_ll = (RelativeLayout) findViewById(R.id.input_password_rl);
        confor_password_rl = (RelativeLayout) findViewById(R.id.confor_password_rl);
        security_code_rl = (RelativeLayout) findViewById(R.id.security_code_rl);
        edInputSecurityCode = (EditText) findViewById(R.id.input_securitycode);
        edInputPassWord = (EditText) findViewById(R.id.input_password);
        edConforPassWord = (EditText) findViewById(R.id.confor_password);
        input_x = (ImageView) findViewById(R.id.input_x);
        btnSecurityCode = (Button) findViewById(R.id.security_code);
        btnRegister = (Button) findViewById(R.id.register);
        title = (TextView) findViewById(R.id.title);
        Button register = (Button) findViewById(R.id.register);
        ivCheckBox = (ImageView) findViewById(R.id.iv_check_box);
        tvUserCheck = (TextView) findViewById(R.id.tv_user_check);
        tvAgreement = (TextView) findViewById(R.id.tv_agrement);
        if (usertype == ResponseUserType.TYPE_ONLINE_NO) {
            title.setText("老用户激活");
            register.setText("立即激活");
        }
        btnSecurityCode.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        tvUserCheck.setOnClickListener(this);
        tvAgreement.setOnClickListener(this);
        ivCheckBox.setOnClickListener(this);
        //密码
        input_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edInputPassWord.setText("");
            }
        });
        edInputPassWord.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean isfocus) {
                if (isfocus) {
                    input_password_ll.setBackgroundResource(R.drawable.background_radius_white_boder_yellow);
                    if (!edInputPassWord.getText().toString().isEmpty()) {
                        input_x.setVisibility(View.VISIBLE);
                    }
                } else {
                    input_password_ll.setBackgroundResource(R.drawable.background_radius_white_boder_grey);
                    input_x.setVisibility(View.GONE);
                }
            }
        });
        edInputPassWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().isEmpty()) {
                    input_x.setVisibility(View.VISIBLE);
                } else {
                    input_x.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        final ImageView confor_password_x = (ImageView) findViewById(R.id.confor_password_x);
        confor_password_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edConforPassWord.setText("");
            }
        });
        edConforPassWord.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean isfocus) {
                if (isfocus) {
                    confor_password_rl.setBackgroundResource(R.drawable.background_radius_white_boder_yellow);
                    if (!edConforPassWord.getText().toString().isEmpty()) {
                        confor_password_x.setVisibility(View.VISIBLE);
                    }
                } else {
                    confor_password_rl.setBackgroundResource(R.drawable.background_radius_white_boder_grey);
                    confor_password_x.setVisibility(View.GONE);
                }
            }
        });
        edConforPassWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().isEmpty()) {
                    confor_password_x.setVisibility(View.VISIBLE);
                } else {
                    confor_password_x.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        final ImageView input_securitycode_x = (ImageView) findViewById(R.id.input_securitycode_x);
        input_securitycode_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edInputSecurityCode.setText("");
            }
        });
        edInputSecurityCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean isfocus) {
                if (isfocus) {
                    security_code_rl.setBackgroundResource(R.drawable.background_radius_white_boder_yellow);
                    if (!edInputSecurityCode.getText().toString().isEmpty()) {
                        input_securitycode_x.setVisibility(View.VISIBLE);
                    }
                } else {
                    security_code_rl.setBackgroundResource(R.drawable.background_radius_white_boder_grey);
                    input_securitycode_x.setVisibility(View.GONE);
                }
            }
        });
        edInputSecurityCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().isEmpty()) {
                    input_securitycode_x.setVisibility(View.VISIBLE);
                } else {
                    input_securitycode_x.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

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
        final String securityCode = edInputSecurityCode.getText().toString();
        final String password = edInputPassWord.getText().toString();
        final String conforPassword = edConforPassWord.getText().toString();
        if (TextUtils.isEmpty(password)) {
            ToastUtil.show("请输入密码");
            return;
        }
        if (TextUtils.isEmpty(conforPassword)) {
            ToastUtil.show("请再次输入密码");
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
        if (!Utils.isNetworkConnected(this)) {
            return;
        }

        final Dialog mDialog = LoadDialogUtil.createLoadingDialog(this);
        mDialog.show();

        Map<String, String> data = new HashMap<>();
        data.put("mobile", phone);
        data.put("password", password);
        data.put("registeredchannel", "APP");
        data.put("supRegistChannel", "APP");
        data.put("usertype", String.valueOf(usertype));
        RetrofitUtil.getHttpService().register(data)
                .compose(new RetrofitUtil.CommonOptions<LoginResult>())
                .subscribe(new CodeHandledSubscriber<LoginResult>() {
                    @Override
                    protected void onError(ApiException apiException) {
                        if (mDialog != null) {
                            mDialog.dismiss();
                        }
                        ToastUtil.show(apiException.getMessage());
                    }

                    @Override
                    protected void onBusinessNext(LoginResult data) {
                        login(phone, password, mDialog);
                    }

                    @Override
                    public void onCompleted() {
                        if (mDialog != null) {
                            mDialog.dismiss();
                        }
                    }
                });
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
//                usertype == ResponseUserType.TYPE_ONLINE_NO ? HttpLoad.UserModule.MESSAGE_MODE_ALIDATEOLDMOBILE
//                        : HttpLoad.UserModule.MESSAGE_MODE_REGISTCELLPHONE,
//                new ResponseCallback<ResponseMessageBean>(mContext) {
//                    @Override
//                    public void onRequestSuccess(ResponseMessageBean result) {
//                        dialog.dismiss();
//                        time.start();
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
