package com.shenme.androiddemo.bean.product;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by king on 2015/8/12.
 */
public class Images implements Parcelable {
    public final static String FORMAT_PRODUCT = "product";
    public final static String FORMAT_THUMBNAIL = "thumbnail";
    public final static String FORMAT_ZOOM = "zoom";
    public final static String FORMAT_CART_ICON = "cartIcon";
    public final static String TYPE_GALLERY = "GALLERY";
    public final static String TYPE_PRIMARY = "PRIMARY";

    //图片文字
    public String altText;
    public String format;
    //图片类型
    public String imageType;
    //图片地址url
    public String url;

    public String galleryIndex;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.altText);
        dest.writeString(this.format);
        dest.writeString(this.imageType);
        dest.writeString(this.url);
        dest.writeString(this.galleryIndex);
    }

    public Images() {
    }

    protected Images(Parcel in) {
        this.altText = in.readString();
        this.format = in.readString();
        this.imageType = in.readString();
        this.url = in.readString();
        this.galleryIndex = in.readString();
    }

    public static final Parcelable.Creator<Images> CREATOR = new Parcelable.Creator<Images>() {
        public Images createFromParcel(Parcel source) {
            return new Images(source);
        }

        public Images[] newArray(int size) {
            return new Images[size];
        }
    };
}
