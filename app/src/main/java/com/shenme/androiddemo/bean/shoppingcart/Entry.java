package com.shenme.androiddemo.bean.shoppingcart;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CANC on 2016/11/7.
 */
public class Entry {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("entryPk")
    private String entryPk;
    @SerializedName("cartId")
    private String cartId;
    @SerializedName("productCode")
    private String productCode;
    @SerializedName("basePrice")
    private String basePrice;
    @SerializedName("actPrice")
    private String actPrice;
    @SerializedName("quantity")
    private String quantity;
    @SerializedName("totalPrice")
    private String totalPrice;
    @SerializedName("calculatedFlag")
    private String calculatedFlag;
    @SerializedName("isSelected")
    private String isSelected;
    @SerializedName("stockStatus")
    private String createDate;
    @SerializedName("createDate")
    private String stockStatus;
    @SerializedName("modifyDate")
    private String modifyDate;


    @SerializedName("thumbnail")
    private String imageUrl;
    @SerializedName("productName")
    private String productName;

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

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

    public String getEntryPk() {
        return entryPk;
    }

    public void setEntryPk(String entryPk) {
        this.entryPk = entryPk;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(String basePrice) {
        this.basePrice = basePrice;
    }

    public String getActPrice() {
        return actPrice;
    }

    public void setActPrice(String actPrice) {
        this.actPrice = actPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCalculatedFlag() {
        return calculatedFlag;
    }

    public void setCalculatedFlag(String calculatedFlag) {
        this.calculatedFlag = calculatedFlag;
    }

    public String getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(String isSelected) {
        this.isSelected = isSelected;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
