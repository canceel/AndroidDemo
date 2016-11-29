package com.shenme.androiddemo.bean.product;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CANC on 2016/7/5.
 */
public class ElasticsearchProduct {
    public String id;
    @SerializedName("productCode")
    public String code;
    @SerializedName("productName")
    public String name;
    public String commodityType;
    public String sapNo;
    public String classification;
    @SerializedName("thumbnail")
    public String thumbnail;
    public String description;
    public String keywords;
    public String summary;
    public String productFixedUnitPrice;
    public String isPromotion;
    public String numberOfReviews;
    public String numberOfGoodReviews;
    public String stockLevels;
    public String salesVolume;
    public String onlineDate;
    public String offlineDate;
    public String eDistrict;
    public String store;
    public String salesApplication;
    public String categoryCode;
    public String categoryName;
    public String brand;
    public String brandName;
    public String globalType;
    public String price;
    public String priceRange;
    public String promotionTag;
    public String color;
    public String size;
    public String grade;
    public String produceArea;
    public String features;
    public String modifiedTime;
    public String expirationDate;
    public String productUnit;
    public String isStockSharing;

    //在购物车中数量
    public int quantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
