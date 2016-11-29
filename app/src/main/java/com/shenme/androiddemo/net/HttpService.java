package com.shenme.androiddemo.net;


import com.shenme.androiddemo.bean.WelfareResult;
import com.shenme.androiddemo.bean.address.ResponseAddress;
import com.shenme.androiddemo.bean.product.ResponseProduct;
import com.shenme.androiddemo.bean.product.ResponseProductList;
import com.shenme.androiddemo.bean.shoppingcart.ResponseOrderCreate;
import com.shenme.androiddemo.bean.shoppingcart.ResponsePayMentList;
import com.shenme.androiddemo.bean.shoppingcart.ShoppingCartResult;
import com.shenme.androiddemo.bean.user.LoginResult;
import com.shenme.androiddemo.bean.user.ResponseUserType;
import com.shenme.androiddemo.constants.Constant;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by admin on 2016/6/24.
 */
public interface HttpService {

    @GET(Constant.DATE_10)
    Observable<WelfareResult> getImage();


    // 登录
    @POST(Constant.API_USER_LOGIN)
    Observable<LoginResult> login(@Query("mobile") String mobile, @Query("password") String password);

    //注册
    @POST(Constant.API_USER_REGIST)
    Observable<LoginResult> register(@QueryMap Map<String, String> map);

    //获得用户类型
    @GET(Constant.API_GET_USER_TYPE)
    Observable<ResponseUserType> getUserType(@Query("mobile") String mobile);

    //获取用户信息
    @GET(Constant.API_GET_USER_INFO)
    Observable<LoginResult> getUserInfo(@Query("access_token") String accessToken);

    //更新用户信息
    @POST(Constant.API_USER_MODIFY_INFO)
    Observable<LoginResult> updateUserInfo(@QueryMap Map<String, String> map);

    //商品详情
    @GET(Constant.API_PRODUCT_DETAIL)
    Observable<ResponseProduct> getProductDetail(@Path("productcode") String productcode);

    //获取商品列表
    @GET(Constant.API_PRODUCT_LIST)
    Observable<ResponseProductList> getProductList(@QueryMap Map<String, String> map);

    //获取购物车
    @GET(Constant.API_SHOPPING_CART)
    Observable<ShoppingCartResult> getShoppingCart(@Path("userid") String userid,
                                                   @Query("access_token") String accessToken);

    //加入购物车
    @POST(Constant.API_ADD_PRODUCT_TO_CART)
    Observable<ShoppingCartResult> addProductToCart(@Path("userid") String userid,
                                                    @QueryMap Map<String, String> map);


    //获取结算数据
    @GET(Constant.API_GET_SETTLEMENT)
    Observable<ShoppingCartResult> getSettlement(@Path("userid") String userid,
                                                 @Query("access_token") String accessToken);

    //获取支付方式列表
    @GET(Constant.API_GET_PAYMENT_LIST)
    Observable<ResponsePayMentList> getPaymentList(@Path("userid") String userid,
                                                   @Query("access_token") String accessToken);

    //添加支付方式到购物车
    @POST(Constant.API_SET_PAYMENT_TO_CART)
    Observable<ShoppingCartResult> setPaymentToCart(@Path("userid") String userid,
                                                    @Query("access_token") String accessToken,
                                                    @Query("paymentMode") String paymentModed);

    //获取收货地址列表
    @GET(Constant.API_GET_CONSIGNEE_ADDRESS_LIST)
    Observable<ResponseAddress> getConsigneeAddressList(@Path("userid") String userid,
                                                        @Query("access_token") String accessToken);

    //添加地址到购物车
    @POST(Constant.API_SET_ADDRESS_TO_CART)
    Observable<ShoppingCartResult> setAddressToCart(@Path("userid") String userid,
                                                    @Query("access_token") String accessToken,
                                                    @Query("addressId") String addressId);

    //创建订单
    @POST(Constant.API_CREATE_ORDER)
    Observable<ResponseOrderCreate> createOrder(@Path("userid") String userid,
                                                @Query("access_token") String accessToken);
}
