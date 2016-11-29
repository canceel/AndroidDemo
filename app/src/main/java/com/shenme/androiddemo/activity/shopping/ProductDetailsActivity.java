package com.shenme.androiddemo.activity.shopping;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shenme.androiddemo.R;
import com.shenme.androiddemo.base.BaseActivity;
import com.shenme.androiddemo.bean.product.Product;
import com.shenme.androiddemo.bean.product.ResponseProduct;
import com.shenme.androiddemo.net.ApiException;
import com.shenme.androiddemo.net.CodeHandledSubscriber;
import com.shenme.androiddemo.net.RetrofitUtil;
import com.shenme.androiddemo.utils.DateUtils;
import com.shenme.androiddemo.utils.LoadDialogUtil;
import com.shenme.androiddemo.utils.MyAnimationUtils;
import com.shenme.androiddemo.utils.ToastUtil;
import com.shenme.androiddemo.utils.Utils;


/**
 * Created by CANC on 2016/11/10.
 */

public class ProductDetailsActivity extends BaseActivity {
    public static final String PRODUCT_CODE = "productCode";
    public static final String PRAMA_IS_FROM_SHOPPINGCART = "from_shopping_cart";

    private ImageView shopCart; //购物车
    private SimpleDraweeView productIv;
    private TextView productName;
    private TextView productCode;
    private TextView onlineDate;
    private TextView offlineDate;
    private TextView productEan;
    private TextView description;
    private TextView salesVolume;
    private TextView numberOfGoodreviews;
    private TextView produceArea;
    private TextView weight;
    private TextView specification;
    private TextView price;
    private TextView isStockLevel;
    private TextView thumbnail;
    private Button btnAdd;

    private String productCodeString;
    private boolean isFromShoppingCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        productCodeString = getIntent().getStringExtra(PRODUCT_CODE);
        isFromShoppingCart = getIntent().getBooleanExtra(PRAMA_IS_FROM_SHOPPINGCART, false);
        findView();
        getData();
    }

    private void findView() {
        shopCart = (ImageView) findViewById(R.id.shop_cart);
        productIv = (SimpleDraweeView) findViewById(R.id.product_icon);
        productName = (TextView) findViewById(R.id.productName);
        productCode = (TextView) findViewById(R.id.product_code);
        onlineDate = (TextView) findViewById(R.id.onlineDate);
        offlineDate = (TextView) findViewById(R.id.offlineDate);
        productEan = (TextView) findViewById(R.id.productEan);
        description = (TextView) findViewById(R.id.description);
        salesVolume = (TextView) findViewById(R.id.salesVolume);
        numberOfGoodreviews = (TextView) findViewById(R.id.numberOfGoodreviews);
        produceArea = (TextView) findViewById(R.id.produceArea);
        weight = (TextView) findViewById(R.id.weight);
        specification = (TextView) findViewById(R.id.specification);
        price = (TextView) findViewById(R.id.price);
        isStockLevel = (TextView) findViewById(R.id.isStockLevel);
        thumbnail = (TextView) findViewById(R.id.thumbnail);
        btnAdd = (Button) findViewById(R.id.btn_add_shopping_cart);
    }


    public void getData() {
        final Dialog mDialog = LoadDialogUtil.createLoadingDialog(mContext);
        mDialog.show();
        RetrofitUtil.getHttpService().getProductDetail(productCodeString)
                .compose(new RetrofitUtil.CommonOptions<ResponseProduct>())
                .subscribe(new CodeHandledSubscriber<ResponseProduct>() {
                    @Override
                    protected void onError(ApiException apiException) {
                        if (mDialog != null) {
                            mDialog.dismiss();
                        }
                        ToastUtil.show(apiException.getDisplayMessage());
                    }

                    @Override
                    protected void onBusinessNext(ResponseProduct data) {
                        if (mDialog != null) {
                            mDialog.dismiss();
                        }
                        formateData(data.resultData);
                    }

                    @Override
                    public void onCompleted() {
                        if (mDialog != null) {
                            mDialog.dismiss();
                        }

                    }
                });
    }

    private void formateData(Product resultData) {
        productIv.setImageURI(resultData.getThumbnail());
        productName.setText(resultData.getProductName());
        productCode.setText(resultData.getProductCode());
        onlineDate.setText(DateUtils.getDateFromMillisecond(resultData.getOnlineDate()));
        offlineDate.setText(DateUtils.getDateFromMillisecond(resultData.getOfflineDate()));
        productEan.setText(resultData.getProductEan());
        description.setText(resultData.getDescription());
        salesVolume.setText(resultData.getSalesVolume());
        numberOfGoodreviews.setText(resultData.getNumberOfGoodreviews());
        produceArea.setText(resultData.getProduceArea());
        weight.setText(resultData.getWeight());
        specification.setText(resultData.getSpecification());
        price.setText(Utils.PriceFormat(resultData.getPrice()));
        thumbnail.setText(resultData.getThumbnail());
        isStockLevel.setText(resultData.getIsStockLevel());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyAnimationUtils.addToShoppingCart(ProductDetailsActivity.this, shopCart, productIv, productCodeString, null, 1, false);
            }
        });

        //点击购物车按钮
        shopCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFromShoppingCart) {
                    onBackPressed();
                } else {
                    Intent intent = new Intent(mContext, ShoppingCartActivity
                            .class);
                    startActivity(intent);
                }
            }
        });
    }

}
