package com.shenme.androiddemo.bean.product;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CANC on 2016/11/10.
 */

public class Product {
    @SerializedName("productName")
    private String productName;
    @SerializedName("productCode")
    private String productCode;
    @SerializedName("thumbnail")
    private String thumbnail;
    @SerializedName("onlineDate")
    private Long onlineDate;
    @SerializedName("offlineDate")
    private Long offlineDate;
    @SerializedName("productEan")
    private String productEan;
    @SerializedName("description")
    private String description;
    @SerializedName("salesVolume")
    private String salesVolume;
    @SerializedName("numberOfGoodreviews")
    private String numberOfGoodreviews;
    @SerializedName("produceArea")
    private String produceArea;
    @SerializedName("weight")
    private String weight;
    @SerializedName("specification")
    private String specification;
    @SerializedName("price")
    private String price;
    @SerializedName("isStockLevel")
    private String isStockLevel;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Long getOnlineDate() {
        return onlineDate;
    }

    public void setOnlineDate(Long onlineDate) {
        this.onlineDate = onlineDate;
    }

    public Long getOfflineDate() {
        return offlineDate;
    }

    public void setOfflineDate(Long offlineDate) {
        this.offlineDate = offlineDate;
    }

    public String getProductEan() {
        return productEan;
    }

    public void setProductEan(String productEan) {
        this.productEan = productEan;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(String salesVolume) {
        this.salesVolume = salesVolume;
    }

    public String getNumberOfGoodreviews() {
        return numberOfGoodreviews;
    }

    public void setNumberOfGoodreviews(String numberOfGoodreviews) {
        this.numberOfGoodreviews = numberOfGoodreviews;
    }

    public String getProduceArea() {
        return produceArea;
    }

    public void setProduceArea(String produceArea) {
        this.produceArea = produceArea;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIsStockLevel() {
        return isStockLevel;
    }

    public void setIsStockLevel(String isStockLevel) {
        this.isStockLevel = isStockLevel;
    }
}
