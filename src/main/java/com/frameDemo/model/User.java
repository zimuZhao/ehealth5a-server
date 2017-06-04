package com.frameDemo.model;

/**
 * 数据库表t_user
 *
 * @author zimu
 * @date 2016-11-05 15:26:20 中国标准时间
 */
public class User {

    /**
     * 用户ID
     */
    private String userID;
    /**
     * 卡类型（1：社保卡 2：医保卡 3：自费卡 4：其他卡 5.自制IC卡 6.身份证 7.NFC卡）
     */
    private Integer cardType;
    /**
     * 用户名（登录）
     */
    private String userName;
    /**
     * 用户密码（登录）
     */
    private String password;
    /**
     * 用户记录创建时间
     */
    private java.util.Date creatTime;
    /**
     * 性别（1:男性，2：女性，0：未知）
     */
    private Integer sex;
    /**
     * 姓名
     */
    private String name;
    /**
     * 身份证号
     */
    private String iDNumber;
    /**
     * 医保卡号
     */
    private String medicareNumber;
    /**
     * 联系地址
     */
    private String address;
    /**
     * 联系电话
     */
    private String mobile;
    /**
     * 保留字节
     */
    private String rES;
    /**
     * 保留字段
     */
    private String description;
    /**
     * 删除标记
     */
    private Boolean deleteFlag;

    public User() {
    }

    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Integer getCardType() {
        return this.cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public java.util.Date getCreatTime() {
        return this.creatTime;
    }

    public void setCreatTime(java.util.Date creatTime) {
        this.creatTime = creatTime;
    }

    public Integer getSex() {
        return this.sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIDNumber() {
        return this.iDNumber;
    }

    public void setIDNumber(String iDNumber) {
        this.iDNumber = iDNumber;
    }

    public String getMedicareNumber() {
        return this.medicareNumber;
    }

    public void setMedicareNumber(String medicareNumber) {
        this.medicareNumber = medicareNumber;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRES() {
        return this.rES;
    }

    public void setRES(String rES) {
        this.rES = rES;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDeleteFlag() {
        return this.deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

}