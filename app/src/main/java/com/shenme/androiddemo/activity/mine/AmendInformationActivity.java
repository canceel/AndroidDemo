package com.shenme.androiddemo.activity.mine;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.shenme.androiddemo.R;
import com.shenme.androiddemo.base.BaseActivity;
import com.shenme.androiddemo.bean.user.Customer;
import com.shenme.androiddemo.bean.user.LoginResult;
import com.shenme.androiddemo.net.ApiException;
import com.shenme.androiddemo.net.CodeHandledSubscriber;
import com.shenme.androiddemo.net.RetrofitUtil;
import com.shenme.androiddemo.utils.LoadDialogUtil;
import com.shenme.androiddemo.utils.ToastUtil;
import com.shenme.androiddemo.utils.Utils;

import java.util.HashMap;
import java.util.Map;


public class AmendInformationActivity extends BaseActivity {

    private final static String TAG = AmendInformationActivity.class.getSimpleName();

    private EditText edUserNameValue;
    private EditText etNickNameValue;
    private RadioGroup rgSex;
    private RadioButton rbMale;
    private RadioButton rbFemale;

    private Customer customer = null;
    private String sexString = "男";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_amend_information);
        findViews();
        initData();
        initViews();
    }

    private void initData() {
        customer = Utils.getUserInfo(mContext);
    }

    private void findViews() {
        edUserNameValue = (EditText) findViewById(R.id.user_name_value_ed);
        etNickNameValue = (EditText) findViewById(R.id.nickname_et);
        rgSex = (RadioGroup) findViewById(R.id.sex);
        rbMale = (RadioButton) findViewById(R.id.male);
        rbFemale = (RadioButton) findViewById(R.id.female);
    }

    private void initViews() {
        Utils.unSupportExpression(edUserNameValue);
        Utils.unSupportExpression(etNickNameValue);

        if (customer != null) {
            if (!TextUtils.isEmpty(customer.getName())) {
                edUserNameValue.setText(customer.getName());
            }
            etNickNameValue.setText(customer.getNickName());
            // 设置光标位置为结尾
            CharSequence charSequence = etNickNameValue.getText();
            if (charSequence instanceof Spannable) {
                Spannable spannable = (Spannable) charSequence;
                Selection.setSelection(spannable, spannable.length());
            }
        }
        //获取性别
        rgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id) {
                    case R.id.male:
                        sexString = rbMale.getText().toString();
                        ToastUtil.show(sexString);
                        break;
                    case R.id.female:
                        sexString = rbFemale.getText().toString();
                        ToastUtil.show(sexString);
                        break;
                }
            }
        });

    }

    //更新用户信息
    public void save(View view) {
        final String nickname = etNickNameValue.getText().toString();
        final String username = edUserNameValue.getText().toString();
        if (TextUtils.isEmpty(nickname)) {
            ToastUtil.show("请输入昵称");
        } else if (TextUtils.isEmpty(username)) {
            ToastUtil.show("请输入你的真实姓名");
        } else {
            if (Utils.isNetworkConnected(mContext)) {
                final Dialog dialog = LoadDialogUtil.createLoadingDialog(mContext);
                dialog.show();
                Map<String, String> map = new HashMap<>();
                RetrofitUtil.getHttpService().updateUserInfo(map)
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
                                //保存用户信息到SP中
                                customer.setNickName(data.customer.getNickName());
                                customer.setUserName(data.customer.getUserName());
                                Utils.saveUserInfo(mContext, data.customer);
                                setResult(RESULT_OK);
                                finish();
                            }

                            @Override
                            public void onCompleted() {
                                if (dialog != null) {
                                    dialog.dismiss();
                                }

                            }
                        });
            }
        }

    }
}
