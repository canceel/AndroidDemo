package com.shenme.androiddemo.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Stone on 2015/8/25.
 * Project:YongHuiProject
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class DateUtils {
    public static final int TYPE_SERVICE = 0;
    public static final int TYPE_NORMAL = 1;

    public static final long HOUR = 1000 * 60 * 60;

    private static final String SERVICE_FORMATER = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String NORMAL_FORMATER = "yyyy-MM-dd HH:mm:ss";
    private static final String DELIVERY_DATE_FORMATER = "MM-dd (E)";
    private static final String DELIVERY_DATE_TITLE_FORMATER = "yyyy-MM-dd";
    private static final String DELIVERY_DATE_SERVER_FORMATER = "yyyyMMdd";
    private static final String DELIVERY_TIME_FORMATER = "HH:mm:ss";

    /**
     * 将服务器的时间转换为Date
     *
     * @param dateString 服务器日期
     * @return 日期类
     */
    private static Date dateFormatService(String dateString) {
        try {
            //去掉时区
            String stringNoTimeZone = dateString.substring(0, 19);
            SimpleDateFormat formatter;
            if (dateString.contains("T")) {
                formatter = new SimpleDateFormat(SERVICE_FORMATER);
            } else {
                formatter = new SimpleDateFormat(NORMAL_FORMATER);
            }
            return formatter.parse(stringNoTimeZone);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将通用类型时间（yyyy-MM-dd HH:mm:ss）转换为Date
     *
     * @param dateString 普通类型时间
     * @return 日期类
     */
    private static Date dateFormatNormal(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat(NORMAL_FORMATER);
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将当前的Date转换为服务器返回的时间
     *
     * @return
     */
    public static String dateToServiceString() {
        SimpleDateFormat formatter = new SimpleDateFormat(SERVICE_FORMATER);
        Date date = new Date();
        return formatter.format(date);
    }

    /**
     * 通过不同的format获取相应的日期String格式
     *
     * @param serviceDate 服务器日期
     * @param format      时间格式
     * @return 相应格式的日期String
     */
    private static String getDate(String serviceDate, String format) {
        if (TextUtils.isEmpty(serviceDate)) {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date date = dateFormatService(serviceDate);
        if (date == null) {
            return serviceDate;
        }
        return formatter.format(date);
    }

    private static String getDate(long mills, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(new Date(mills));
    }

    /**
     * 获取应用通用时间
     *
     * @param serviceDate 服务器时间
     * @return
     */
    public static String getNormalDate(String serviceDate) {
        return getDate(serviceDate, NORMAL_FORMATER);
    }

    /**
     * 获取配送日期
     *
     * @param serviceDate 服务器时间
     * @return
     */
    public static String getDeliveryDate(String serviceDate) {
        StringBuilder sb = new StringBuilder("");

        long mills = getDateMills(serviceDate, TYPE_SERVICE);
        String current = getDate(System.currentTimeMillis(), DELIVERY_DATE_FORMATER);
        String today = getDate(mills, DELIVERY_DATE_FORMATER);
        String tomorrow = getDate(mills - 86400000, DELIVERY_DATE_FORMATER);

        if (current.equals(today)) {
            sb.append("今天 ");
        } else if (current.equals(tomorrow)) {
            sb.append("明天 ");
        }
        sb.append(getDate(serviceDate, DELIVERY_DATE_FORMATER));
        return sb.toString();
    }

    /**
     * 获取配送日期标题
     *
     * @param serviceDate
     * @return
     */
    public static String geyDeliveryDateTitle(String serviceDate) {
        return getDate(serviceDate, DELIVERY_DATE_TITLE_FORMATER);
    }

    /**
     * 获取时间毫秒数
     *
     * @param dateString 日期的字符串
     * @param type       传入日期字符串的格式
     *                   0    服务器时间
     *                   1    yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static long getDateMills(String dateString, int type) {
        if (TextUtils.isEmpty(dateString)) {
            return 0;
        }
        Date date = null;
        switch (type) {
            case TYPE_SERVICE:
                date = dateFormatService(dateString);
                break;
            case TYPE_NORMAL:
                date = dateFormatNormal(dateString);
                break;
        }
        if (date != null) {
            return date.getTime();
        } else {
            return 0;
        }
    }

    /**
     * 返回给服务器的配送时间格式
     *
     * @param serviceDate 服务器时间
     * @return
     */
    public static String getDeliveryDateServer(String serviceDate) {
        return getDate(serviceDate, DELIVERY_DATE_SERVER_FORMATER);
    }

    /**
     * 获取服务器与当前本地时间的偏差值
     *
     * @param serviceDate
     * @return
     */
    public static long getOffsetTime(String serviceDate) {
        Date date = dateFormatService(serviceDate);
        return date.getTime() - System.currentTimeMillis();
    }

    /**
     * 根据毫秒数换算倒计时，格式为：xx天xx时xx分xx秒
     *
     * @param time
     * @return
     */
    public static HashMap<String, String> getCountDownTime(long time) {
        HashMap<String, String> dateMap = new HashMap<>();
        if (time <= 0) {
            dateMap.put("hours", "00");
            dateMap.put("minute", "00");
            dateMap.put("second", "00");
            return dateMap;
        }
        long remainder;

        int hours = (int) (time / (1000 * 60 * 60));
        remainder = time % (1000 * 60 * 60);
        if (hours > 99) {
            dateMap.put("hours", "99+");
        } else if (hours < 10) {
            dateMap.put("hours", "0" + hours);
        } else {
            dateMap.put("hours", String.valueOf(hours));
        }

        int minute = (int) (remainder / (1000 * 60));
        remainder = remainder % (1000 * 60);
        if (minute < 10) {
            dateMap.put("minute", "0" + minute);
        } else {
            dateMap.put("minute", String.valueOf(minute));
        }

        int second = (int) (remainder / 1000);
        if (second < 10) {
            dateMap.put("second", "0" + second);
        } else {
            dateMap.put("second", String.valueOf(second));
        }

        return dateMap;
    }
    /**
     * 获取物流时间
     *
     * @param serviceDate 服务器时间
     * @return
     */
    public static String getDeliveryTime(String serviceDate) {
        return getDate(serviceDate, DELIVERY_TIME_FORMATER);
    }
    /**
     * 毫秒转时间
     *
     * @param data
     * @return
     */
    public static String getDateFromMillisecond(Long data) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(NORMAL_FORMATER);
        return simpleDateFormat.format(data);
    }
}
