package com.shenme.androiddemo.bean.user;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

public class Customer {
    //该账户的账户层级的account_uid样式为注册时使用的手机/邮箱+时间戳

    @SerializedName("userPk")
    private String userPk;
    @SerializedName("uid")
    private String uid;
    @SerializedName("userName")
    private String userName;
    @SerializedName("password")
    private String password;
    @SerializedName("profilePicture")
    private String profilePicture;
    @SerializedName("lastLoginDate")
    private String lastLoginDate;
    @SerializedName("title")
    private String title;
    @SerializedName("mobileNumber")
    private String mobileNumber;
    @SerializedName("pointcardNum")
    private String pointcardNum;
    @SerializedName("pointcardType")
    private String pointcardType;
    @SerializedName("crmId")
    private String crmId;
    @SerializedName("activatedState")
    private String activatedState;
    @SerializedName("activateDate")
    private String activateDate;
    @SerializedName("email")
    private String email;
    @SerializedName("nickName")
    private String nickName;
    @SerializedName("registChannel")
    private String registChannel;
    @SerializedName("supRegistChannel")
    private String supRegistChannel;
    @SerializedName("memberPk")
    private String memberPk;
    @SerializedName("refmobile")
    private String refmobile;
    @SerializedName("memberDistrict")
    private String memberDistrict;
    @SerializedName("memberStatus")
    private String memberStatus;
    @SerializedName("idNumber")
    private String idNumber;
    @SerializedName("idType")
    private String idType;
    @SerializedName("name")
    private String name;
    @SerializedName("sex")
    private String sex;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("profession")
    private String profession;
    @SerializedName("income")
    private String income;
    @SerializedName("homeAddress")
    private String homeAddress;
    @SerializedName("crmMobile")
    private String crmMobile;
    @SerializedName("contactEmail")
    private String contactEmail;
    @SerializedName("actionCode")
    private String actionCode;
    @SerializedName("access_token")
    private String access_token;
    @SerializedName("token_type")
    private String token_type;
    @SerializedName("expires_in")
    private int expires_in;
    @SerializedName("userType")
    private String userType;
    @SerializedName("pointcardStatus")
    private String pointcardStatus;
    @SerializedName("lastUpdateTime")
    private String lastUpdateTime;
    @SerializedName("refresh_token")
    private String refresh_token;

    public String getRefresh_token() {
        return TextUtils.isEmpty(refresh_token) ? "" : refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getUserPk() {
        return TextUtils.isEmpty(userPk) ? "" : userPk;
    }

    public void setUserPk(String userPk) {
        this.userPk = userPk;
    }

    public String getUid() {
        return TextUtils.isEmpty(uid) ? "" : uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return TextUtils.isEmpty(userName) ? "" : userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return TextUtils.isEmpty(password) ? "" : password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicture() {
        return TextUtils.isEmpty(profilePicture) ? "" : profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getLastLoginDate() {
        return TextUtils.isEmpty(lastLoginDate) ? "" : lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getTitle() {
        return TextUtils.isEmpty(title) ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMobileNumber() {
        return TextUtils.isEmpty(mobileNumber) ? "" : mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPointcardNum() {
        return TextUtils.isEmpty(pointcardNum) ? "" : pointcardNum;
    }

    public void setPointcardNum(String pointcardNum) {
        this.pointcardNum = pointcardNum;
    }

    public String getPointcardType() {
        return TextUtils.isEmpty(pointcardType) ? "" : pointcardType;
    }

    public void setPointcardType(String pointcardType) {
        this.pointcardType = pointcardType;
    }

    public String getCrmId() {
        return TextUtils.isEmpty(crmId) ? "" : crmId;
    }

    public void setCrmId(String crmId) {
        this.crmId = crmId;
    }

    public String getActivatedState() {
        return TextUtils.isEmpty(activatedState) ? "" : activatedState;
    }

    public void setActivatedState(String activatedState) {
        this.activatedState = activatedState;
    }

    public String getActivateDate() {
        return TextUtils.isEmpty(activateDate) ? "" : activateDate;
    }

    public void setActivateDate(String activateDate) {
        this.activateDate = activateDate;
    }

    public String getEmail() {
        return TextUtils.isEmpty(email) ? "" : email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return TextUtils.isEmpty(nickName) ? "" : nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRegistChannel() {
        return TextUtils.isEmpty(registChannel) ? "" : registChannel;
    }

    public void setRegistChannel(String registChannel) {
        this.registChannel = registChannel;
    }

    public String getSupRegistChannel() {
        return TextUtils.isEmpty(supRegistChannel) ? "" : supRegistChannel;
    }

    public void setSupRegistChannel(String supRegistChannel) {
        this.supRegistChannel = supRegistChannel;
    }

    public String getMemberPk() {
        return TextUtils.isEmpty(memberPk) ? "" : memberPk;
    }

    public void setMemberPk(String memberPk) {
        this.memberPk = memberPk;
    }

    public String getRefmobile() {
        return TextUtils.isEmpty(refmobile) ? "" : refmobile;
    }

    public void setRefmobile(String refmobile) {
        this.refmobile = refmobile;
    }

    public String getMemberDistrict() {
        return TextUtils.isEmpty(memberDistrict) ? "" : memberDistrict;
    }

    public void setMemberDistrict(String memberDistrict) {
        this.memberDistrict = memberDistrict;
    }

    public String getMemberStatus() {
        return TextUtils.isEmpty(memberStatus) ? "" : memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }

    public String getIdNumber() {
        return TextUtils.isEmpty(idNumber) ? "" : idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdType() {
        return TextUtils.isEmpty(idType) ? "" : idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getName() {
        return TextUtils.isEmpty(name) ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return TextUtils.isEmpty(sex) ? "" : sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return TextUtils.isEmpty(birthday) ? "" : birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getProfession() {
        return TextUtils.isEmpty(profession) ? "" : profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getIncome() {
        return TextUtils.isEmpty(income) ? "" : income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getHomeAddress() {
        return TextUtils.isEmpty(homeAddress) ? "" : homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getCrmMobile() {
        return TextUtils.isEmpty(crmMobile) ? "" : crmMobile;
    }

    public void setCrmMobile(String crmMobile) {
        this.crmMobile = crmMobile;
    }

    public String getContactEmail() {
        return TextUtils.isEmpty(contactEmail) ? "" : contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getActionCode() {
        return TextUtils.isEmpty(actionCode) ? "" : actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public String getAccess_token() {
        return TextUtils.isEmpty(access_token) ? "" : access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return TextUtils.isEmpty(token_type) ? "" : token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public int getExpires_in() {
        return TextUtils.isEmpty(String.valueOf(expires_in)) ? 99999999 : expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getUserType() {
        return TextUtils.isEmpty(userType) ? "" : userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPointcardStatus() {
        return TextUtils.isEmpty(pointcardStatus) ? "" : pointcardStatus;
    }

    public void setPointcardStatus(String pointcardStatus) {
        this.pointcardStatus = pointcardStatus;
    }

    public String getLastUpdateTime() {
        return TextUtils.isEmpty(lastUpdateTime) ? "" : lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getMember_status() {
        return TextUtils.isEmpty(member_status) ? "" : member_status;
    }

    public void setMember_status(String member_status) {
        this.member_status = member_status;
    }

    public String getIscheckid() {
        return TextUtils.isEmpty(ischeckid) ? "" : ischeckid;
    }

    public void setIscheckid(String ischeckid) {
        this.ischeckid = ischeckid;
    }

    public String getAvatarurl() {
        return TextUtils.isEmpty(avatarurl) ? "" : avatarurl;
    }

    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl;
    }

    //不再使用的字段

    @SerializedName("status")
    private String member_status;
    @SerializedName("ischeckid")
    private String ischeckid;
    //用户头像
    @SerializedName("avatarurl")
    private String avatarurl;

}
