package com.shenme.androiddemo.utils;

import android.content.Context;
import android.content.Intent;

import com.shenme.androiddemo.activity.login.LoginActivity;
import com.shenme.androiddemo.activity.shopping.ProductDetailsActivity;
import com.shenme.androiddemo.bean.shoppingcart.ShoppingCartResult;
import com.shenme.androiddemo.net.ApiException;
import com.shenme.androiddemo.net.CodeHandledSubscriber;
import com.shenme.androiddemo.net.RetrofitUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CANC on 2016/11/28.
 */

public abstract class LinkToUtils {
    /**
     * 判断token过期
     * 判断是否登录,未登录---直接调入登录,返回true
     *
     * @param context
     * @return
     */
    public static boolean checkAndLoginActivity(Context context) {
        //判断token   //判断是否登录
        if (Utils.getToken(context) == null || !Utils.isLoginUser(context)) {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 微服务加入购物车
     */
    public static void addProductToCart(final Context context, String productCode, String qty) {
        Map<String, String> data = new HashMap<>();
        data.put("access_token", Utils.getToken(context));
        data.put("productCode", productCode);
        data.put("qty", qty);
        RetrofitUtil.getHttpService().addProductToCart(
                Utils.getUserID(context), data)
                .compose(new RetrofitUtil.CommonOptions<ShoppingCartResult>())
                .subscribe(new CodeHandledSubscriber<ShoppingCartResult>() {
                    @Override
                    protected void onError(ApiException apiException) {
                        ToastUtil.show(apiException.getDisplayMessage());
                    }

                    @Override
                    protected void onBusinessNext(ShoppingCartResult data) {
                        ToastUtil.show("加入购物车成功");
                    }

                    @Override
                    public void onCompleted() {

                    }
                });
    }

    /**
     * 进入商品详情
     *
     * @param context
     * @param productCode
     * @param isFromShoppingCart
     */
    public static void gotoProductDetail(Context context, String productCode, boolean isFromShoppingCart) {
        Intent intent = new Intent(context, ProductDetailsActivity.class);
        intent.putExtra(ProductDetailsActivity.PRODUCT_CODE, productCode);
        intent.putExtra(ProductDetailsActivity.PRAMA_IS_FROM_SHOPPINGCART, isFromShoppingCart);
        context.startActivity(intent);
    }
}
