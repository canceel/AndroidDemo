package com.shenme.androiddemo.constants;

/**
 * <p/>
 * URL调用
 */
public class Constant {
    //    public static final String BaseHost = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/";
    public static final String DATE_10 = "10/1";

    public static final String BaseHost = "http://10.1.41.204:8099";
    public static final String BASE_URL = "";

    /**
     * 微服务
     * 开始
     */
    /**
     * 用户相关模块
     */

    //获取用户类型
    public static final String API_GET_USER_TYPE = BASE_URL
            + "/users/getUserType";
    //用户注册
    public static final String API_USER_REGIST = BASE_URL
            + "/users/register";
    //用户登录
    public static final String API_USER_LOGIN = BASE_URL
            + "/users/login";
    //获取用户
    public static final String API_GET_USER_INFO = BASE_URL
            + "/users/getUser";
    //用户更新
    public static final String API_USER_MODIFY_INFO = BASE_URL
            + "/users/updateprofile";

    /**
     * 产品模块
     */

    //获取商品列表信息
    public static final String API_PRODUCT_LIST = BASE_URL +
            "/products/productSearch";

    //获取商品详情
    public static final String API_PRODUCT_DETAIL = BASE_URL
            + "/products/{productcode}";

    /**
     * 购物车模块
     */

    //添加商品到购物车
    public static final String API_ADD_PRODUCT_TO_CART = BASE_URL
            + "/users/{userid}/yhcarts/addToCart";
    //获取购物车
    public static final String API_SHOPPING_CART = BASE_URL +
            "/users/{userid}/yhcarts";

    //获取结算数据
    public static final String API_GET_SETTLEMENT = BASE_URL
            + "/users/{userid}/yhcarts/step2";
    /**
     * 支付模块
     */
    //获取支付方式列表
    public static final String API_GET_PAYMENT_LIST = BASE_URL +
            "/payment/{userid}/paymentmodes";
    //设置支付方式到购物车
    public static final String API_SET_PAYMENT_TO_CART = BASE_URL +
            "/users/{userid}/yhcarts/setPaymentmode";

    //获取收货人地址列表
    public static final String API_GET_CONSIGNEE_ADDRESS_LIST = BASE_URL +
            "/users/{userid}/yhAddress/addresses";

    //设置地址到购物车
    public static final String API_SET_ADDRESS_TO_CART = BASE_URL +
            "/users/{userid}/yhcarts/setDeliveryAddress";

    //创建订单
    public static final String API_CREATE_ORDER = BASE_URL +
            "/users//{userid}/yhorder/placeOrder";


    /**
     * 微服务
     * 结束
     */


}
