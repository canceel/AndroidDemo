package com.shenme.androiddemo.bean.shoppingcart;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by CANC on 2016/11/28.
 */

public class ResponseShoppingCart {

    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("cartId")
    private String cartId;
    @SerializedName("cartCode")
    private String cartCode;
    @SerializedName("totalPrice")
    private String totalPrice;
    @SerializedName("subTotalPrice")
    private String subTotalPrice;
    @SerializedName("deliverycost")
    private String deliverycost;
    @SerializedName("paymentMode")
    private String paymentMode;
    @SerializedName("paymentName")
    private String paymentName;
    @SerializedName("deliveryMode")
    private String deliveryMode;
    @SerializedName("userId")
    private String userId;
    @SerializedName("calculatedFlag")
    private String calculatedFlag;
    @SerializedName("isInvoice")
    private String isInvoice;
    @SerializedName("invoice")
    private String invoice;
    @SerializedName("notes")
    private String notes;
    @SerializedName("totalWeight")
    private String totalWeight;
    @SerializedName("createDate")
    private String createDate;
    @SerializedName("modifyDate")
    private String modifyDate;
    @SerializedName("addrPk")
    private String addrPk;
    @SerializedName("deliveryAddress")
    private String deliveryAddress;
    public List<Entry> entries;

    public String getAddrPk() {
        return addrPk;
    }

    public void setAddrPk(String addrPk) {
        this.addrPk = addrPk;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
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

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getCartCode() {
        return cartCode;
    }

    public void setCartCode(String cartCode) {
        this.cartCode = cartCode;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSubTotalPrice() {
        return subTotalPrice;
    }

    public void setSubTotalPrice(String subTotalPrice) {
        this.subTotalPrice = subTotalPrice;
    }

    public String getDeliverycost() {
        return deliverycost;
    }

    public void setDeliverycost(String deliverycost) {
        this.deliverycost = deliverycost;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(String deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCalculatedFlag() {
        return calculatedFlag;
    }

    public void setCalculatedFlag(String calculatedFlag) {
        this.calculatedFlag = calculatedFlag;
    }

    public String getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(String isInvoice) {
        this.isInvoice = isInvoice;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
