package com.frameDemo.model;

/**
 * 数据库表t_for_help
 *
 * @author zimu
 * @date 2016-11-05 15:26:20 中国标准时间
 */
public class ForHelp {

    /**
     * 求助记录ID
     */
    private String helpID;
    /**
     * 关联userID和deviceID
     */
    private String associateID;
    /**
     * 求助时间
     */
    private java.util.Date helpTime;
    /**
     * 是否上传（0：上传，1：不上传）
     */
    private Boolean upload;
    /**
     * 解决时间
     */
    private java.util.Date solveTime;
    /**
     * 保留字段
     */
    private String rES;
    /**
     * 描述（预留）
     */
    private String describtion;
    /**
     * 删除标记
     */
    private Boolean deleteFlag;

    public ForHelp() {
    }

    public String getHelpID() {
        return this.helpID;
    }

    public void setHelpID(String helpID) {
        this.helpID = helpID;
    }

    public String getAssociateID() {
        return this.associateID;
    }

    public void setAssociateID(String associateID) {
        this.associateID = associateID;
    }

    public java.util.Date getHelpTime() {
        return this.helpTime;
    }

    public void setHelpTime(java.util.Date helpTime) {
        this.helpTime = helpTime;
    }

    public Boolean getUpload() {
        return this.upload;
    }

    public void setUpload(Boolean upload) {
        this.upload = upload;
    }

    public java.util.Date getSolveTime() {
        return this.solveTime;
    }

    public void setSolveTime(java.util.Date solveTime) {
        this.solveTime = solveTime;
    }

    public String getRES() {
        return this.rES;
    }

    public void setRES(String rES) {
        this.rES = rES;
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