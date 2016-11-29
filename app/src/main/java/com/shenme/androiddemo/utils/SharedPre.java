package com.shenme.androiddemo.utils;

/**
 * Created by Administrator on 2015/7/8.
 */
public class SharedPre {

//    public static abstract class Constant {
//        public static final String GUEST_USER = "guest_user";
//        public static final String Third_Platform_USER = "third_platform_user";
//        public static final String APP_USER = "app_user";
//    }

    public static abstract class App {
        public static final String VERSION = "app_version";//版本信息
        public static final String APP_VERSION_NAME = "app_version_name";//版本信息
        public static final String SYSTEM_VERSION = "system_version";//系统版本信息
        public static final String PHONE_INFO = "phone_info";//系统型号
        public static final String CITY = "app_city";//门店编码
        public static final String CITY_CODE = "app_city_code";//城市code
        public static final String REGION_CODE = "app_region_code";//省code


        public static final String ACCESS_TOKEN = "app_access_token";
        public static final String REFRESH_TOKEN = "app_refresh_token";//刷新token
        public static final String EXPIRES_IN = "app_expires_in";//过期时间
        public static final String UPDATE_DATE = "app_update_date";//获取Token时的本地时间

        public static final String STORE_ID = "app_store_id";//门店编码
        public static final String VIP_STORE_ID = "app_vip_store_id";//高端门店编码
        public static final String STREET_NAME = "app_street_name";//门店名称
        public static final String ADDRESS_ID = "app_address_id";//默认地址ID

        public static final String START_IMAGE = "app_start_image";//启动页
        public static final String ISGUIDE = "app_isguide";//引导页
        public static final String MESSAGE_PUSH = "app_message_push";//消息推送
    }

    public static abstract class UploadLocation {
        public static final String CITY_NAME = "upload_location_city_name"; //显示的城市
        public static final String LNG = "upload_location_lng"; // 维度
        public static final String LAT = "upload_location_lat"; // 经度
    }


    public static abstract class User {
        public static final String USERPK  = "user_Pk";//用户帐号ID
        public static final String NICKNAME= "nick_Name";//用户别称
        public static final String MEMBER_ID = "user_member_id";//用户会员ID
        public static final String MEMBER_LEVEL = "user_member_level";//用户等级
        public static final String MEMBER_STATUS = "user_member_status";//用户状态
        public static final String ID_NUMBER = "user_id_number";//用户ID号
        public static final String ID_TYPE = "user_id_type";//用户ID类型
        public static final String IS_CHECKID = "user_is_checkid";//用户是否验证过
        public static final String AVATAR_URL = "user_avatar_url";//用户头像url
        public static final String MOBILE = "user_mobile";//用户手机
        public static final String IS_CHECK_MOBILE = "user_is_check_mobile";//用户手机是否验证
        public static final String EMAIL = "user_email";//用户邮箱
        public static final String CONTACT_EMAIL = "contact_email";//联系邮箱
        public static final String NAME = "user_name";//用户名
        public static final String USER_NAME = "user_name";//用户名
        public static final String GENDER = "user_gender";//用户性别
        public static final String UPDATE_TIME = "user_update_time";//用户更新时间

        public static class Card {
            public static final String ID = "user_card_id";//会员卡号
            public static final String POINTS = "user_card_ponts";//用户更新时间
            public static final String OUTDATE_POINTS = "user_card_outdate_point";//用户更新时间
            public static final String OUTDATE_TIME = "user_card_outdate_time";//用户更新时间
        }

    }

    public static abstract class Cart {
        public static final String CODE = "cart_code";//购物车编码
        public static final String GUID = "cart_guid"; //购物车GUID
        public static final String NUMBER = "cart_number";//购物车数量
        public static final String HAI_TAO_CODE = "hai_tao_cart_code";//海淘购物车编码
        public static final String DRINKMODULE_STORE_CODE = "drinkmodule_store_code";//即饮购物车编码
        public static final String DRINKMODULE_CODE = "drinkmodule_code";//即饮购物车编码
        public static final String DRINKMODULE_NUMBER = "drinkmodule_number";//即饮购物车数量
        public static final String DRINKMODULE_TAKES_TIME = "drinkmodule_takes_time";//即饮配送时间
    }

    public static abstract class Location {
        public static final String REGION_CODE = "app_region_code";//省code
        public static final String CITY_NAME = "location_city_name"; //显示的城市
        public static final String CITY_CODE = "location_city_code"; //城市编码
        public static final String DISTRICT_NAME = "location_district_name"; // 区县编码
        public static final String DISTRICT_CODE = "location_district_code"; // 区县编码
        public static final String STREET_NAME = "location_street_name"; // 街道
        public static final String LNG = "location_lng"; // 维度
        public static final String LAT = "location_lat"; // 经度
        public static final String DETAILS = "location_details"; // 全部，用来当前显示

    }
    public static abstract class DrinkLocation {
        public static final String REGION_CODE = "drink_region_code";//省code
        public static final String CITY_NAME = "drink_city_name"; //显示的城市
        public static final String CITY_CODE = "drink_city_code"; //城市编码
        public static final String DISTRICT_CODE = "drink_district_code"; // 区县编码
        public static final String STREET_NAME = "drink_street_name"; // 街道
        public static final String T_LNG = "drink_lng"; // 腾讯维度
        public static final String T_LAT = "drink_lat"; // 腾讯经度

    }

    public static abstract class Homepager {
        public static final String LINKGROUPS_HOME = "homepager_theme";//微店首页布局
        public static final String LINKGROUPS_GLOBAL_HOME = "homepager_global_theme";//微店首页布局
        public static final String HOME_TIME_OFFSET = "homepager_time_offset";//记录服务器与手机时间的偏差值
        public static final String GLOBAL_TIME_OFFSET = "globalpager_time_offset";//记录服务器与手机时间的偏差值
        public static final String PAGELOG = "page_logo";//首页的显示布局
        public static final String TIME_OFFSET = "homepager_time_offset";//记录服务器与手机时间的偏差值
    }

    //积分商城
    public static abstract class Integral {
        public static final String CITY_NAME = "integral_city_name";//城市名称
        public static final String CITY_CODE = "integral_city_code";//城市编码
        public static final String STORE_NAME = "location_store_name";//门店名称
        public static final String STORE_ADDRESS = "location_store_address";//门店地址
        public static final String TAKESTIME = "takes_time";//取货时间
        public static final String EXPIRDATE = "expir_data";//有效期
        public static final String INTEGRAL_DEFAULT_STORE_ID = "";//默认积分商城id

    }


}
