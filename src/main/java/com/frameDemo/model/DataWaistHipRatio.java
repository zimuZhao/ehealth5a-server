package com.frameDemo.model;

/**
 * 数据库表t_data_waist_hip_ratio
 *
 * @author zimu
 * @date 2016-11-05 15:26:20 中国标准时间
 */
public class DataWaistHipRatio {

    /**
     * 腰臀比记录ID
     */
    private String whrID;
    /**
     * 关联userID和deviceID
     */
    private String associateID;
    /**
     * 测量时间
     */
    private java.util.Date measureTime;
    /**
     * 腰围（单位：cm 有效范围：0~300 有效精度：1）
     */
    private Integer waist;
    /**
     * 体重（单位： 有效范围：0~300 有效精度：1）
     */
    private Integer hip;
    /**
     * 是否上传
     */
    private Boolean upload;
    /**
     * 保留字节
     */
    private String rES;
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

    public DataWaistHipRatio() {
    }

    public String getWhrID() {
        return this.whrID;
    }

    public void setWhrID(String whrID) {
        this.whrID = whrID;
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

    public Integer getWaist() {
        return this.waist;
    }

    public void setWaist(Integer waist) {
        this.waist = waist;
    }

    public Integer getHip() {
        return this.hip;
    }

    public void setHip(Integer hip) {
        this.hip = hip;
    }

    public Boolean getUpload() {
        return this.upload;
    }

    public void setUpload(Boolean upload) {
        this.upload = upload;
    }

    public String getRES() {
        return this.rES;
    }

    public void setRES(String rES) {
        this.rES = rES;
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