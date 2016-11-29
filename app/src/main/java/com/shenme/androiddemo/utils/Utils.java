package com.shenme.androiddemo.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Html;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.shenme.androiddemo.R;
import com.shenme.androiddemo.activity.login.LoginActivity;
import com.shenme.androiddemo.base.ApplicationInit;
import com.shenme.androiddemo.bean.shoppingcart.Price;
import com.shenme.androiddemo.bean.user.Customer;
import com.shenme.androiddemo.widget.CircleBoard;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by CANC on 2016/8/29.
 */
public class Utils {
    /**
     * 手机网络检测
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return true;
            }
        }
        try {
            ToastUtil.show("请检查您的网络连接！");
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    public static int dipDimensionInteger(Context context, float value) {
        return (int) (dipDimensionFloat(context, value) + 0.5f);
    }

    public static float dipDimensionFloat(Context context, float value) {
        return context == null ? value : TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, value, context.getResources()
                        .getDisplayMetrics());
    }

    public static Spanned getHtmlRedFormat(String string0, String string) {
        return getHtmlRedFormat(string0, "<font size = \"20px\" color=\"#FFB925\">" + string + "</font>", "");
    }

    public static Spanned getHtmlRedFormat(String string0, String string, String string1) {
        return Html.fromHtml(string0 + "<font size = \"20px\" color=\"#FFB925\">" + string + "</font>" + string1);
    }

    public static Spanned getHtmlRedFormatRed(String string, String string1) {
        return Html.fromHtml(string + "<font size = \"20px\" color=\"#E80000\">" + string1 + "</font>");
    }

    /**
     * 不能输入表情
     */

    public static void unSupportExpression(final EditText editText) {
        InputFilter emojiFilter = new InputFilter() {
            Pattern emoji = Pattern.compile(
                    "[\\ud83c\\udc00-\\ud83c\\udfff]|[\\ud83d\\udc00-\\ud83d\\udfff]|[\\u2600-\\u27ff]",
                    Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart,
                                       int dend) {

                Matcher emojiMatcher = emoji.matcher(source);
                if (emojiMatcher.find()) {
                    ToastUtil.show("不能包含表情");
                    return "";
                }
                return null;
            }
        };
        editText.setFilters(new InputFilter[]{emojiFilter});
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 价格格式化工具
     *
     * @param price 价格类
     * @return
     */
    public static String PriceFormat(Price price) {
        if (price == null || price.currencyIso == null) {
            return PriceFormat(0);
        }
        if ("CNY".equals(price.currencyIso)) {
            return PriceFormat(price.value);
        } else {
            return PriceFormat(price.value).replace("¥ ", price.currencyIso);
        }
    }

    public static String PriceFormat(double price) {
        DecimalFormat df = new DecimalFormat("#0.00");
        return "¥ " + df.format(price);
    }

    public static String PriceFormat(String price) {
        if (TextUtils.isEmpty(price)) {
            return "¥0.00";
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        try {
            return "¥" + df.format(Float.valueOf(price));
        } catch (Exception e) {
            return "¥0.00";
        }
    }


    /**
     * 根据CircleBoard中的值设置颜色
     *
     * @param context
     * @param circleBoard
     * @param time        时间
     */
    public static void setCircle(Context context, CircleBoard circleBoard, int time) {
        int ringColor;
        circleBoard.setTextString(time + "");
        if (TextUtils.isEmpty(circleBoard.getmTextString())) {
            circleBoard.setTextString("XX");
            ringColor = context.getResources().getColor(R.color.gray_d7d7d7);
        } else {
            try {
                int text = Integer.parseInt(circleBoard.getmTextString());
                if (text == 0) {
                    ringColor = context.getResources().getColor(R.color.gray_d7d7d7);
                } else if (text > 0 && text <= 19) {
                    ringColor = context.getResources().getColor(R.color.red_fe3a39);
                } else if (text >= 20 && text <= 29) {
                    ringColor = context.getResources().getColor(R.color.yellow_ffc12d);
                } else if (text >= 30 && text <= 59) {
                    ringColor = context.getResources().getColor(R.color.green_2bc497);
                } else {
                    circleBoard.setTextString("完成");
                    ringColor = context.getResources().getColor(R.color.gray_d7d7d7);
                }
            } catch (Exception e) {
                circleBoard.setTextString("XX");
                ringColor = context.getResources().getColor(R.color.gray_d7d7d7);
            }

        }
        setColor(circleBoard, ringColor, Color.WHITE, ringColor, ringColor);

    }

    /**
     * 使用不同颜色
     *
     * @param circleBoard
     * @param ringColor          圆环颜色
     * @param circularPlateColor 圆板颜色
     * @param scaleColor         刻度颜色
     * @param textColor          文字颜色
     */
    public static void setColor(CircleBoard circleBoard, int ringColor, int circularPlateColor, int scaleColor, int textColor) {
        //设置圆圈背景
        circleBoard.setBackGround(ringColor);
        //设置内圆背景
        circleBoard.setmIBackGround(circularPlateColor);
        //设置刻度颜色
        circleBoard.setmScaleColor(scaleColor);
        //设置文字颜色
        circleBoard.setmTextColor(textColor);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dpToPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int pxToDp(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) ((dpValue / scale) - 0.5f);
    }

    //设置Margint值

    //空
    public static LinearLayout.LayoutParams setMargint(Context context) {
        return setMargint(context, 0, 0, 0, 0);
    }

    //左边距
    public static LinearLayout.LayoutParams setMargint(Context context, int left) {
        return setMargint(context, left, 0, 0, 0);
    }

    //左上边距
    public static LinearLayout.LayoutParams setMargint(Context context, int left, int up) {
        return setMargint(context, left, up, 0, 0);
    }

    //左上右边距
    public static LinearLayout.LayoutParams setMargint(Context context, int left, int up, int right) {
        return setMargint(context, left, up, right, 0);
    }

    /**
     * @param left  左
     * @param up    上
     * @param right 右
     * @param down  下
     * @return
     */
    public static LinearLayout.LayoutParams setMargint(Context context, int left, int up, int right, int down) {
        LinearLayout.LayoutParams layoutParams = new
                LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(dpToPx(context, left), dpToPx(context, up), dpToPx(context, right), dpToPx(context, down));
        return layoutParams;
    }


    /**
     * 保存用户的唯一标识
     *
     * @param context
     * @param accessToken
     */
    public static void saveAccessToken(Context context, String accessToken, String refreshToken, long expiresIn) {
        SharedPreUtils.putString(context, SharedPre.App.ACCESS_TOKEN, accessToken);
        SharedPreUtils.putString(context, SharedPre.App.REFRESH_TOKEN, refreshToken);
        SharedPreUtils.putLong(context, SharedPre.App.EXPIRES_IN, (expiresIn * 1000));
        SharedPreUtils.putLong(context, SharedPre.App.UPDATE_DATE, System.currentTimeMillis());
    }

    /**
     * 保存用户信息
     *
     * @param context
     * @param customer
     */
    public static void saveUserInfo(Context context, Customer customer) {
        if (context == null) {
            context = ApplicationInit.context;
        }
//        SharedPreUtils.putString(context, SharedPre.User.AVATAR_URL, customer.avatarurl);
        SharedPreUtils.putString(context, SharedPre.User.NICKNAME, customer.getNickName());
        SharedPreUtils.putString(context, SharedPre.User.USERPK, customer.getUserPk());
        SharedPreUtils.putString(context, SharedPre.User.EMAIL, customer.getEmail());
        SharedPreUtils.putString(context, SharedPre.User.GENDER, customer.getSex());
        SharedPreUtils.putString(context, SharedPre.User.ID_NUMBER, customer.getIdNumber());
        SharedPreUtils.putString(context, SharedPre.User.ID_TYPE, customer.getIdType());
        SharedPreUtils.putString(context, SharedPre.User.CONTACT_EMAIL, customer.getContactEmail());
        SharedPreUtils.putString(context, SharedPre.User.MEMBER_STATUS, customer.getMember_status());
        SharedPreUtils.putString(context, SharedPre.User.UPDATE_TIME, customer.getLastUpdateTime());
        SharedPreUtils.putString(context, SharedPre.User.MOBILE, customer.getMobileNumber());
        SharedPreUtils.putString(context, SharedPre.User.NAME, customer.getName());
        SharedPreUtils.putString(context, SharedPre.User.USER_NAME, customer.getUserName());
    }

    /**
     * 是否为登录用户
     *
     * @param context
     * @return
     */
    public static boolean isLoginUser(Context context) {
        return (!TextUtils.isEmpty(SharedPreUtils.getString(context, SharedPre.App.ACCESS_TOKEN)));
    }

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
     * 获取userID
     *
     * @param context
     * @return
     */
    public static String getUserID(Context context) {
        return SharedPreUtils.getString(context, SharedPre.User.USERPK);
    }

    /**
     * 获得access_token
     *
     * @param context
     * @return
     */
    public static String getToken(Context context) {
        return getToken(context, true);
    }


    public static String getToken(Context context, boolean isGotoLogin) {
        String accessToken = SharedPreUtils.getString(context, SharedPre.App.ACCESS_TOKEN);
        if (TextUtils.isEmpty(accessToken)) {
            return null;
        }
        return accessToken;
    }

    /**
     * 获取用户信息
     *
     * @param context
     */
    public static Customer getUserInfo(Context context) {
        if (context == null) {
            context = ApplicationInit.context;
        }
        if (!isLoginUser(context)) {
            return null;
        }
        Customer customer = new Customer();
//        customer.avatarurl = SharedPreUtils.getString(context, SharedPre.User.AVATAR_URL);
        customer.setNickName(SharedPreUtils.getString(context, SharedPre.User.NICKNAME));
        customer.setUserPk(SharedPreUtils.getString(context, SharedPre.User.USERPK));
        customer.setEmail(SharedPreUtils.getString(context, SharedPre.User.EMAIL));
        customer.setSex(SharedPreUtils.getString(context, SharedPre.User.GENDER));
        customer.setIdNumber(SharedPreUtils.getString(context, SharedPre.User.ID_NUMBER));
        customer.setIdType(SharedPreUtils.getString(context, SharedPre.User.ID_TYPE));
        customer.setContactEmail(SharedPreUtils.getString(context, SharedPre.User.CONTACT_EMAIL));
        customer.setMember_status(SharedPreUtils.getString(context, SharedPre.User.MEMBER_STATUS));
        customer.setLastUpdateTime(SharedPreUtils.getString(context, SharedPre.User.UPDATE_TIME));
        customer.setMobileNumber(SharedPreUtils.getString(context, SharedPre.User.MOBILE));
        customer.setName(SharedPreUtils.getString(context, SharedPre.User.NAME));
        customer.setUserName(SharedPreUtils.getString(context, SharedPre.User.USER_NAME));
        return customer;
    }


    //清除SP中用户的信息
    public static void cleanUserInfo(Context context) {
        if (context == null) {
            context = ApplicationInit.context;
        }
        SharedPreUtils.removeSharedKey(context, SharedPre.User.AVATAR_URL);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.NICKNAME);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.USERPK);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.EMAIL);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.GENDER);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.ID_TYPE);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.CONTACT_EMAIL);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.MEMBER_STATUS);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.UPDATE_TIME);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.NAME);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.USER_NAME);

        SharedPreUtils.removeSharedKey(context, SharedPre.App.ACCESS_TOKEN);
        SharedPreUtils.removeSharedKey(context, SharedPre.App.EXPIRES_IN);
        SharedPreUtils.removeSharedKey(context, SharedPre.App.ADDRESS_ID);

        SharedPreUtils.removeSharedKey(context, SharedPre.Cart.CODE);
        SharedPreUtils.removeSharedKey(context, SharedPre.Cart.GUID);
        SharedPreUtils.removeSharedKey(context, SharedPre.Cart.NUMBER);

        SharedPreUtils.removeSharedKey(context, SharedPre.Cart.DRINKMODULE_STORE_CODE);
        SharedPreUtils.removeSharedKey(context, SharedPre.Cart.DRINKMODULE_CODE);
        SharedPreUtils.removeSharedKey(context, SharedPre.Cart.DRINKMODULE_NUMBER);
        SharedPreUtils.removeSharedKey(context, SharedPre.Cart.DRINKMODULE_TAKES_TIME);

        SharedPreUtils.removeSharedKey(context, SharedPre.Homepager.LINKGROUPS_HOME);
        SharedPreUtils.removeSharedKey(context, SharedPre.Homepager.LINKGROUPS_GLOBAL_HOME);
        SharedPreUtils.removeSharedKey(context, SharedPre.Homepager.HOME_TIME_OFFSET);
        SharedPreUtils.removeSharedKey(context, SharedPre.Homepager.GLOBAL_TIME_OFFSET);
        SharedPreUtils.removeSharedKey(context, SharedPre.Homepager.TIME_OFFSET);
        SharedPreUtils.removeSharedKey(context, SharedPre.Homepager.PAGELOG);

        SharedPreUtils.removeSharedKey(context, SharedPre.User.Card.ID);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.Card.POINTS);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.Card.OUTDATE_POINTS);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.Card.OUTDATE_TIME);
    }

    /**
     * 获取购物车商品数量
     *
     * @param context
     * @return
     */
    public static int getCartNo(Context context) {
        return SharedPreUtils.getInt(context, SharedPre.Cart.NUMBER, 0);
    }

}
