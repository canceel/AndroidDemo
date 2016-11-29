package com.shenme.androiddemo.activity.shopping;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.shenme.androiddemo.R;
import com.shenme.androiddemo.activity.address.PickAddressActivity;
import com.shenme.androiddemo.adapter.ProductAdapter;
import com.shenme.androiddemo.base.BaseActivity;
import com.shenme.androiddemo.bean.shoppingcart.Entry;
import com.shenme.androiddemo.bean.shoppingcart.PaymentMode;
import com.shenme.androiddemo.bean.shoppingcart.ResponseOrderCreate;
import com.shenme.androiddemo.bean.shoppingcart.ResponseShoppingCart;
import com.shenme.androiddemo.bean.shoppingcart.ShoppingCartResult;
import com.shenme.androiddemo.fragment.ShoppingCartFragment;
import com.shenme.androiddemo.net.ApiException;
import com.shenme.androiddemo.net.CodeHandledSubscriber;
import com.shenme.androiddemo.net.RetrofitUtil;
import com.shenme.androiddemo.utils.LoadDialogUtil;
import com.shenme.androiddemo.utils.ToastUtil;
import com.shenme.androiddemo.utils.Utils;
import com.shenme.androiddemo.widget.AutoHeightListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 结算界面
 * Created by CANC on 2016/11/9.
 */

public class SettlementAvtivity extends BaseActivity implements View.OnClickListener {
    private static final int ADDRESS_PICK_CODE = 0;
    private static final int PAY_WAY = 1;
    private RelativeLayout rlSelectAddress;
    private RelativeLayout rlAddress;
    private TextView tvName;
    private TextView deliveryWayValue;
    private RelativeLayout relativeLayoutPayway;
    private TextView payWayValue;
    private AutoHeightListView productContainer;
    private TextView tvTotal;
    private TextView tvDispatchingPrice;
    private TextView tvCouponsOrderTotal;
    private Button btnPay;

    //地址数据
    private String addressId;
    private String addressString;
    //配送方式
    private String deliveryMode;
    //支付方式数据
    private String paymentCode;
    private String paymentMethod;
    //订单金额数据
    private String productTotalString;
    private String carriageString;
    private String subTotalString;

    private List<Entry> datas = new ArrayList<>();
    private ProductAdapter adapter;
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settlement);
        initView();
        getData(true);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initView() {
        rlAddress = (RelativeLayout) findViewById(R.id.rl_address);
        rlSelectAddress = (RelativeLayout) findViewById(R.id.rl_select_address);
        tvName = (TextView) findViewById(R.id.tv_name);
        deliveryWayValue = (TextView) findViewById(R.id.delivery_way_value);
        relativeLayoutPayway = (RelativeLayout) findViewById(R.id.relativeLayout_payway);
        payWayValue = (TextView) findViewById(R.id.pay_way_value);
        productContainer = (AutoHeightListView) findViewById(R.id.productContainer);
        tvTotal = (TextView) findViewById(R.id.tv_total);
        tvDispatchingPrice = (TextView) findViewById(R.id.tv_dispatching_price);
        tvCouponsOrderTotal = (TextView) findViewById(R.id.tv_coupons_order_total);
        btnPay = (Button) findViewById(R.id.btn_pay);

        rlAddress.setOnClickListener(this);
        rlSelectAddress.setOnClickListener(this);
        relativeLayoutPayway.setOnClickListener(this);
        btnPay.setOnClickListener(this);
    }

    //初始化结算数据
    private void getData(boolean showDialog) {
        final Dialog dialog = LoadDialogUtil.createLoadingDialog(this);
        if (showDialog) {
            dialog.show();
        }
        RetrofitUtil.getHttpService().getSettlement(Utils.getUserID(mContext), Utils.getToken(mContext))
                .compose(new RetrofitUtil.CommonOptions<ShoppingCartResult>())
                .subscribe(new CodeHandledSubscriber<ShoppingCartResult>() {
                    @Override
                    protected void onError(ApiException apiException) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                    }

                    @Override
                    protected void onBusinessNext(ShoppingCartResult data) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        formatSettlement(data.data);
                    }

                    @Override
                    public void onCompleted() {
                        if (dialog != null) {
                            dialog.dismiss();
                        }

                    }
                });

    }

    /**
     * 设置支付方式到购物车
     *
     * @param payment
     */
    public void setPaymentMethod(final PaymentMode payment) {
        final Dialog dialog = LoadDialogUtil.createLoadingDialog(this);
        dialog.show();
        RetrofitUtil.getHttpService().setPaymentToCart(
                Utils.getUserID(mContext), Utils.getToken(mContext), payment.getPaymentPk())
                .compose(new RetrofitUtil.CommonOptions<ShoppingCartResult>())
                .subscribe(new CodeHandledSubscriber<ShoppingCartResult>() {
                    @Override
                    protected void onError(ApiException apiException) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        ToastUtil.show(apiException.getDisplayMessage());

                    }

                    @Override
                    protected void onBusinessNext(ShoppingCartResult data) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        ToastUtil.show("添加支付方式成功");
                        formatSettlement(data.data);

                    }

                    @Override
                    public void onCompleted() {
                        if (dialog != null) {
                            dialog.dismiss();
                        }

                    }
                });
    }

    /**
     * 设置地址到购物车
     */
    private void addAddressToCart(final String addressId) {
        if (Utils.isNetworkConnected(this)) {
            final Dialog dialog = LoadDialogUtil.createLoadingDialog(this);
            dialog.show();
            RetrofitUtil.getHttpService().setAddressToCart(
                    Utils.getUserID(mContext), Utils.getToken(mContext), addressId)
                    .compose(new RetrofitUtil.CommonOptions<ShoppingCartResult>())
                    .subscribe(new CodeHandledSubscriber<ShoppingCartResult>() {
                        @Override
                        protected void onError(ApiException apiException) {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                            ToastUtil.show(apiException.getDisplayMessage());
                        }

                        @Override
                        protected void onBusinessNext(ShoppingCartResult data) {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                            formatSettlement(data.data);

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


    //格式化结算数据
    private void formatSettlement(ResponseShoppingCart data) {
        //设置产品数据
        if (data.entries != null && data.entries.size() > 0) {
            datas = data.entries;
        }
        //设置地址数据
        addressId = data.getAddrPk();
        addressString = data.getDeliveryAddress();
        //配送方式
        deliveryMode = data.getDeliveryMode();
        //设置支付数据
        paymentCode = data.getPaymentMode();
        paymentMethod = data.getPaymentName();
        //设置金额信息
        productTotalString = data.getTotalPrice();
        carriageString = data.getDeliverycost();
        subTotalString = data.getSubTotalPrice();


        setData();
    }

    //填充数据
    private void setData() {
        //设置商品数据
        adapter = new ProductAdapter(mContext, datas);
        productContainer.setAdapter(adapter);
        //设置地址
        if (TextUtils.isEmpty(addressString)) {
            rlSelectAddress.setVisibility(View.VISIBLE);
            rlAddress.setVisibility(View.GONE);
        } else {
            rlSelectAddress.setVisibility(View.GONE);
            rlAddress.setVisibility(View.VISIBLE);
            tvName.setText(addressString);
        }
        //设置配送方式
        deliveryWayValue.setText(deliveryMode);
        //设置支付方式
        payWayValue.setText(paymentMethod);
        //设置金额数据
        tvTotal.setText(Utils.PriceFormat(productTotalString));
        tvDispatchingPrice.setText(Utils.PriceFormat(carriageString));
        tvCouponsOrderTotal.setText(Utils.PriceFormat(subTotalString));
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.rl_select_address:
            case R.id.rl_address:
                if (TextUtils.isEmpty(addressString)) {
                    Intent intent = new Intent(this, PickAddressActivity.class);
                    startActivityForResult(intent, ADDRESS_PICK_CODE);
                } else {
                    Intent intent = new Intent(this, PickAddressActivity.class);
                    intent.putExtra(PickAddressActivity.ADDRESS_ID, addressId);
                    startActivityForResult(intent, ADDRESS_PICK_CODE);
                }
                break;
            case R.id.relativeLayout_payway:
                Intent intent = new Intent(this, PayWayActivity.class);
                intent.putExtra(PayWayActivity.PAYMENT_CODE, paymentCode);
                startActivityForResult(intent, PAY_WAY);
                break;
            case R.id.btn_pay:
                createOrder();
        }
    }

    //生成订单
    private void createOrder() {
        if (TextUtils.isEmpty(addressString)) {
            ToastUtil.show("请选择收货地址");
            return;
        } else if (TextUtils.isEmpty(paymentMethod)) {
            ToastUtil.show("请选择支付方式");
            return;
        }
        final Dialog dialog = LoadDialogUtil.createLoadingDialog(mContext);
        dialog.show();
        RetrofitUtil.getHttpService().createOrder(
                Utils.getUserID(mContext), Utils.getToken(mContext))
                .compose(new RetrofitUtil.CommonOptions<ResponseOrderCreate>())
                .subscribe(new CodeHandledSubscriber<ResponseOrderCreate>() {
                    @Override
                    protected void onError(ApiException apiException) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        Intent intent = new Intent(mContext, ResultActivity.class);
                        intent.putExtra(ResultActivity.STATUS, apiException.getCode());
                        intent.putExtra(ResultActivity.MESSAGE, apiException.getDisplayMessage());
                        mContext.startActivity(intent);
                        SettlementAvtivity.this.finish();
                    }

                    @Override
                    protected void onBusinessNext(ResponseOrderCreate data) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        Intent intent1 = new Intent(ShoppingCartFragment.UPDATE_CART);
                        mContext.sendBroadcast(intent1);
                        Intent intent = new Intent(mContext, ResultActivity.class);
                        intent.putExtra(ResultActivity.STATUS, data.getStatus());
                        intent.putExtra(ResultActivity.MESSAGE, data.getMessage());
                        mContext.startActivity(intent);
                        SettlementAvtivity.this.finish();
                    }

                    @Override
                    public void onCompleted() {

                    }
                });


    }

    //回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == PAY_WAY) {
                PaymentMode payment = data.getExtras().getBundle("data").getParcelable("payment");
                setPaymentMethod(payment);
            }
            if (requestCode == ADDRESS_PICK_CODE) {
                addressString = data.getStringExtra(PickAddressActivity.ADDRESS_DETAIL);
                addAddressToCart(data.getStringExtra(PickAddressActivity.ADDRESS_ID));
            }
        }
    }

    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("SettlementAvtivity Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
