package com.shenme.androiddemo.activity.mine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.shenme.androiddemo.R;
import com.shenme.androiddemo.base.BaseActivity;
import com.shenme.androiddemo.bean.user.Customer;
import com.shenme.androiddemo.utils.CropUtils;
import com.shenme.androiddemo.utils.Utils;
import com.shenme.androiddemo.widget.CircleImageView;

public class MineInformationActivity extends BaseActivity {

    private static final String TAG = MineInformationActivity.class.getSimpleName();
    private static final int PHOTO_REQUEST_CAREMA = 0;
    private static final int PHOTO_REQUEST_GALLERY = 1;
    private static final int PHOTO_REQUEST_CUT = 2;

    private static final int CUSTOMER_INFO_MODIFY = 3;
    //用于保存剪裁后图片的URI
    private Uri imageUri = CropUtils.buildSavedUri();
    //用于保存拍照图片的URI
    private Uri imagePhotoUri = CropUtils.buildPhotoUri();
    private TextView tvUserNameValue;
    private TextView tvNickNameValue;
    private TextView tvMobileValue;
    private CircleImageView ibUserIcon;

    private Customer customer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_information);
        findViews();
        initData();
        initViews();
    }

    private void initData() {
        customer = Utils.getUserInfo(mContext);
    }

    private void findViews() {
        tvUserNameValue = (TextView) findViewById(R.id.user_name_value);
        ibUserIcon = (CircleImageView) findViewById(R.id.user_icon);
        tvNickNameValue = (TextView) findViewById(R.id.nickname_value);
        tvMobileValue = (TextView) findViewById(R.id.mobile_tv);
    }

    private void initViews() {
        if (customer != null) {
            tvUserNameValue.setText(customer.getUserName());
            tvNickNameValue.setText(customer.getNickName());
            tvMobileValue.setText(customer.getMobileNumber());
        }
    }

    public void back(View view) {
        onBackPressed();
    }

    public void infor_redact(View view) {
        Intent i = new Intent(this, AmendInformationActivity.class);
        startActivityForResult(i, CUSTOMER_INFO_MODIFY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}

