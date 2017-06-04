package com.frameDemo.model;

/**
 * 数据库表t_data_blood_oxygen
 *
 * @author zimu
 * @date 2016-11-05 15:26:20 中国标准时间
 */
public class DataBloodOxygen {

    /**
     * 血氧记录ID
     */
    private String bloodOxygenID;
    /**
     * 关联userID和deviceID
     */
    private String associateID;
    /**
     * 测量时间
     */
    private java.util.Date measureTime;
    /**
     * 血氧饱和度（单位：% 有效范围：0~100 有效精度：1）
     */
    private Integer sPO2;
    /**
     * 脉搏（单位：bpm 有效范围：10~400 有效精度：1）
     */
    private Integer pR;
    /**
     * 是否上传
     */
    private Boolean upload;
    /**
     * 保留字节
     */
    private String rES1;
    /**
     * 保留字节
     */
    private String rES2;
    /**
     * 记录创建时间
     */
    private java.util.Date creatTime;
    /**
     * 保留字段
     */
    private String describtion;
    /**
     * 删除标记
     */
    private Boolean deleteFlag;

    public DataBloodOxygen() {
    }

    public String getBloodOxygenID() {
        return this.bloodOxygenID;
    }

    public void setBloodOxygenID(String bloodOxygenID) {
        this.bloodOxygenID = bloodOxygenID;
    }

    public String getAssociateID() {
        return this.associateID;
    }

    public void setAssociateID(String associateID) {
        this.associateID = associateID;
    }

    public java.util.Date getMeasureTime() {
        return this.measureTime;
    }

    public void setMeasureTime(java.util.Date measureTime) {
        this.measureTime = measureTime;
    }

    public Integer getSPO2() {
        return this.sPO2;
    }

    public void setSPO2(Integer sPO2) {
        this.sPO2 = sPO2;
    }

    public Integer getPR() {
        return this.pR;
    }

    public void setPR(Integer pR) {
        this.pR = pR;
    }

    public Boolean getUpload() {
        return this.upload;
    }

    public void setUpload(Boolean upload) {
        this.upload = upload;
    }

    public String getRES1() {
        return this.rES1;
    }

    public void setRES1(String rES1) {
        this.rES1 = rES1;
    }

    public String getRES2() {
        return this.rES2;
    }

    public void setRES2(String rES2) {
        this.rES2 = rES2;
    }

    public java.util.Date getCreatTime() {
        return this.creatTime;
    }

    public void setCreatTime(java.util.Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getDescribtion() {
        return this.describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public Boolean getDeleteFlag() {
        return this.deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

}