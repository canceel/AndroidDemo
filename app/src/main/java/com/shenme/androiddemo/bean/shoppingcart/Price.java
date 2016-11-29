package com.shenme.androiddemo.bean.shoppingcart;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Stone on 2015/8/11.
 * Project:YongHuiProject
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class Price implements Parcelable {
    //货币制
    public String currencyIso;
    //含税订单总金额
    public double value;
    //格式化的价格（¥89.00）
    public String formattedValue;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.currencyIso);
        dest.writeDouble(this.value);
        dest.writeString(this.formattedValue);
    }

    public Price() {
    }

    protected Price(Parcel in) {
        this.currencyIso = in.readString();
        this.value = in.readDouble();
        this.formattedValue = in.readString();
    }

    public static final Parcelable.Creator<Price> CREATOR = new Parcelable.Creator<Price>() {
        public Price createFromParcel(Parcel source) {
            return new Price(source);
        }

        public Price[] newArray(int size) {
            return new Price[size];
        }
    };
}
