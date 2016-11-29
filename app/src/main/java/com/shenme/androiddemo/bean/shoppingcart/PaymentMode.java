package com.shenme.androiddemo.bean.shoppingcart;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Stone on 2015/8/11.
 * Project:YongHuiProject
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class PaymentMode implements Parcelable {

//    public final static String CODE_ALIPAY = "alipay";
//    public final static String CODE_WECHAT = "wechat";
//    public final static String CODE_UNIONPAY = "unionpay";
//    public final static String CODE_YHCARD = "yhcard";

    private String status;
    private String message;
    private String paymentPk;
    private String paymentCode;
    private String paymentName;
    private String description;
    private String isActive;
    //支付是否选中
    public boolean isSelect;

    public static final Creator<PaymentMode> CREATOR = new Creator<PaymentMode>() {
        @Override
        public PaymentMode createFromParcel(Parcel in) {
            return new PaymentMode(in);
        }

        @Override
        public PaymentMode[] newArray(int size) {
            return new PaymentMode[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPaymentPk() {
        return paymentPk;
    }

    public void setPaymentPk(String paymentPk) {
        this.paymentPk = paymentPk;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeString(this.message);
        dest.writeString(this.paymentPk);
        dest.writeString(this.paymentCode);
        dest.writeString(this.paymentName);
        dest.writeString(this.description);
        dest.writeString(this.isActive);
        dest.writeByte(isSelect ? (byte) 1 : (byte) 0);
    }

    protected PaymentMode(Parcel in) {
        status = in.readString();
        message = in.readString();
        paymentPk = in.readString();
        paymentCode = in.readString();
        paymentName = in.readString();
        description = in.readString();
        isActive = in.readString();
        isSelect = in.readByte() != 0;
    }


    //    //支付方式编码
//    public String code;
//    //支付方式名称
//    public String name;
//    //支付是否选中
//    public boolean isSelect;

//
//    public PaymentMode() {
//    }
//
//    protected PaymentMode(Parcel in) {
//        this.code = in.readString();
//        this.name = in.readString();
//        this.isSelect = in.readByte() != 0;
//    }
//
//    public static final Parcelable.Creator<PaymentMode> CREATOR = new Parcelable
//            .Creator<PaymentMode>() {
//        public PaymentMode createFromParcel(Parcel source) {
//            return new PaymentMode(source);
//        }
//
//        public PaymentMode[] newArray(int size) {
//            return new PaymentMode[size];
//        }
//    };
}
