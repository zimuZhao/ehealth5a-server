package com.frameDemo.model;

/**
 * 数据库表t_data_blood_pressure
 *
 * @author zimu
 * @date 2016-11-05 15:26:20 中国标准时间
 */
public class DataBloodPressure {

    /**
     * 无创血压记录ID
     */
    private String nibpID;
    /**
     * 关联userID和deviceID
     */
    private String associateID;
    /**
     * 测量时间
     */
    private java.util.Date measureTime;
    /**
     * 收缩压（单位：mmHg 有效范围：0~300 有效精度：1）一般和Dia一起表示为120/75mmHg
     */
    private Integer sys;
    /**
     * 舒张压（单位：mmHg 有效范围：0~300 有效精度：1）
     */
    private Integer dia;
    /**
     * 踝肱指数（需拼接底层数据）
     */
    private Double aBI;
    /**
     * 踝肱指数标记（0：踝肱指数未开启 1：肱部血压 2：踝部血压）
     */
    private Boolean aBIFlag;
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

    public DataBloodPressure() {
    }

    public String getNibpID() {
        return this.nibpID;
    }

    public void setNibpID(String nibpID) {
        this.nibpID = nibpID;
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

    public Integer getSys() {
        return this.sys;
    }

    public void setSys(Integer sys) {
        this.sys = sys;
    }

    public Integer getDia() {
        return this.dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Double getABI() {
        return this.aBI;
    }

    public void setABI(Double aBI) {
        this.aBI = aBI;
    }

    public Boolean getABIFlag() {
        return this.aBIFlag;
    }

    public void setABIFlag(Boolean aBIFlag) {
        this.aBIFlag = aBIFlag;
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