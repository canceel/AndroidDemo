package com.shenme.androiddemo.bean.address;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CANC on 2016/11/28.
 */

public class Address {

    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("addrPk")
    private String addrPk;
    @SerializedName("userPk")
    private String userPk;
    @SerializedName("fullName")
    private String fullName;
    @SerializedName("cellphone")
    private String cellphone;
    @SerializedName("countryPk")
    private String countryPk;
    @SerializedName("regionPk")
    private String regionPk;
    @SerializedName("cityPk")
    private String cityPk;
    @SerializedName("districtPk")
    private String districtPk;
    @SerializedName("fax")
    private String fax;
    @SerializedName("postcode")
    private String postcode;
    @SerializedName("streetname")
    private String streetname;
    @SerializedName("streetnumber")
    private String streetnumber;
    @SerializedName("lng")
    private String lng;
    @SerializedName("lat")
    private String lat;
    @SerializedName("email")
    private String email;
    @SerializedName("company")
    private String company;
    @SerializedName("department")
    private String department;
    @SerializedName("building")
    private String building;
    @SerializedName("createDate")
    private String createDate;
    @SerializedName("modifyDate")
    private String modifyDate;
    public boolean selected;

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

    public String getAddrPk() {
        return addrPk;
    }

    public void setAddrPk(String addrPk) {
        this.addrPk = addrPk;
    }

    public String getUserPk() {
        return userPk;
    }

    public void setUserPk(String userPk) {
        this.userPk = userPk;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getCountryPk() {
        return countryPk;
    }

    public void setCountryPk(String countryPk) {
        this.countryPk = countryPk;
    }

    public String getRegionPk() {
        return regionPk;
    }

    public void setRegionPk(String regionPk) {
        this.regionPk = regionPk;
    }

    public String getCityPk() {
        return cityPk;
    }

    public void setCityPk(String cityPk) {
        this.cityPk = cityPk;
    }

    public String getDistrictPk() {
        return districtPk;
    }

    public void setDistrictPk(String districtPk) {
        this.districtPk = districtPk;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    public String getStreetnumber() {
        return streetnumber;
    }

    public void setStreetnumber(String streetnumber) {
        this.streetnumber = streetnumber;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
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
}
