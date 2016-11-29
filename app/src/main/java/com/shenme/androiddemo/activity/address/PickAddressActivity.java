package com.shenme.androiddemo.activity.address;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.shenme.androiddemo.R;
import com.shenme.androiddemo.activity.login.LoginActivity;
import com.shenme.androiddemo.adapter.AddressListAdapter;
import com.shenme.androiddemo.base.BaseActivity;
import com.shenme.androiddemo.bean.address.Address;
import com.shenme.androiddemo.bean.address.ResponseAddress;
import com.shenme.androiddemo.net.ApiException;
import com.shenme.androiddemo.net.CodeHandledSubscriber;
import com.shenme.androiddemo.net.RetrofitUtil;
import com.shenme.androiddemo.utils.LoadDialogUtil;
import com.shenme.androiddemo.utils.ToastUtil;
import com.shenme.androiddemo.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class PickAddressActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "PickAddressActivity";
    public static final String ADDRESS_ID = "address_id";
    public static final String ADDRESS_DETAIL = "address_detail";
    private static final int PICK_REUQEST_CODE = 0;
    public static final int PICK_ADDRESS_LOGIN = 100;

    private List<Address> datas = new ArrayList<>();
    private ListView listView;
    private View emptyView;
    private AddressListAdapter adapter;
    private RelativeLayout btnManagerAddress;

    private String addressId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_address);
        addressId = getIntent().getStringExtra(ADDRESS_ID);
        setView();
        getAddressDate();
    }

    private void setView() {

        listView = (ListView) findViewById(R.id.listView);
        btnManagerAddress = (RelativeLayout) findViewById(R.id.btn_manager_address);
        btnManagerAddress.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra(ADDRESS_ID, datas.get(position).getAddrPk());
                intent.putExtra(ADDRESS_DETAIL, datas.get(position).getFullName());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void getAddressDate() {
        if (!Utils.isNetworkConnected(mContext)) {
            return;
        }
        if (Utils.isLoginUser(mContext)) {
            final Dialog dialog = LoadDialogUtil.createLoadingDialog(this);
            dialog.show();
            RetrofitUtil.getHttpService().getConsigneeAddressList(
                    Utils.getUserID(mContext), Utils.getToken(mContext))
                    .compose(new RetrofitUtil.CommonOptions<ResponseAddress>())
                    .subscribe(new CodeHandledSubscriber<ResponseAddress>() {
                        @Override
                        protected void onError(ApiException apiException) {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                            ToastUtil.show(apiException.getDisplayMessage());
                        }

                        @Override
                        protected void onBusinessNext(ResponseAddress data) {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                            if (data != null && data.dataList != null && data.dataList.size() > 0) {
                                datas = data.dataList;
                                adapter = new AddressListAdapter(mContext, datas, addressId);
                                listView.setAdapter(adapter);
                            }
                        }

                        @Override
                        public void onCompleted() {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                        }
                    });
        } else {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.putExtra(LoginActivity.IS_FROM_PICKADDRESS, true);
            startActivityForResult(intent, PICK_ADDRESS_LOGIN);
        }
    }

    public void onClick(View v) {
        int id = v.getId();
//        if (id == R.id.btn_manager_address) {
//            Intent intent = new Intent(this, AddAddressActivity.class);
//            intent.putExtra("action", AddAddressActivity.ADD_ADDRESS);
//            String type = getIntent().getStringExtra("typepage");
//            if (type != null) {
//                intent.putExtra("typepage", type);
//            }
//            startActivityForResult(intent, PICK_REUQEST_CODE);
//        }
    }

//    public void add_address(View view) {
//        Intent intent = new Intent(this, AddAddressActivity.class);
//        intent.putExtra("action", AddAddressActivity.ADD_ADDRESS);
//        startActivityForResult(intent, PICK_REUQEST_CODE);
//
//    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_ADDRESS_LOGIN) {
                getAddressDate();
            } else if (requestCode == PICK_REUQEST_CODE) {
                getAddressDate();
            }
        }
        if (!Utils.isLoginUser(mContext)) {
            onBackPressed();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}