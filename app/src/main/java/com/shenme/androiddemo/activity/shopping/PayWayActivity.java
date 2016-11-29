package com.shenme.androiddemo.activity.shopping;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.shenme.androiddemo.R;
import com.shenme.androiddemo.adapter.PaymentAdapter;
import com.shenme.androiddemo.adapter.PaymentAdapter.PaymentSelectListener;
import com.shenme.androiddemo.base.BaseActivity;
import com.shenme.androiddemo.bean.shoppingcart.PaymentMode;
import com.shenme.androiddemo.bean.shoppingcart.ResponsePayMentList;
import com.shenme.androiddemo.net.ApiException;
import com.shenme.androiddemo.net.CodeHandledSubscriber;
import com.shenme.androiddemo.net.RetrofitUtil;
import com.shenme.androiddemo.utils.LoadDialogUtil;
import com.shenme.androiddemo.utils.ToastUtil;
import com.shenme.androiddemo.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/17.
 */
public class PayWayActivity extends BaseActivity {
    public static final String PAYMENT_CODE = "paymentCode";
    private ListView lvPayWayList;
    private PaymentAdapter adapter;
    private List<PaymentMode> datas = new ArrayList<>();
    private String paycode;
    private String cartId;
    private PaymentSelectListener listener = new PaymentSelectListener() {
        @Override
        public void selectPayment(PaymentMode payment) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelable("payment", payment);
            intent.putExtra("data", bundle);
            setResult(RESULT_OK, intent);
            finish();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_way);

        initData();
        setView();
        getPayWayList();
    }

    private void setView() {
        lvPayWayList = (ListView) findViewById(R.id.pay_way_list);

    }

    private void initData() {
        Intent intent = getIntent();
        paycode = intent.getStringExtra(PAYMENT_CODE);
        cartId = intent.getStringExtra("cartId");

    }

    private void getPayWayList() {
        if (Utils.isNetworkConnected(this)) {
            final Dialog mDialog = LoadDialogUtil.createLoadingDialog(this);
            mDialog.show();
            RetrofitUtil.getHttpService().getPaymentList(Utils.getUserID(mContext), Utils.getToken(mContext))
                    .compose(new RetrofitUtil.CommonOptions<ResponsePayMentList>())
                    .subscribe(new CodeHandledSubscriber<ResponsePayMentList>() {
                        @Override
                        protected void onError(ApiException apiException) {
                            if (mDialog != null) {
                                mDialog.dismiss();
                            }
                            ToastUtil.show(apiException.getDisplayMessage());
                        }

                        @Override
                        protected void onBusinessNext(ResponsePayMentList data) {
                            if (mDialog != null) {
                                mDialog.dismiss();
                            }
                            if (data.paymentModes != null) {
                                datas = data.paymentModes;
                                adapter = new PaymentAdapter(mContext, datas, paycode, listener);
                                lvPayWayList.setAdapter(adapter);
                            }

                        }

                        @Override
                        public void onCompleted() {
                            if (mDialog != null) {
                                mDialog.dismiss();
                            }
                        }
                    });
        }
    }

}