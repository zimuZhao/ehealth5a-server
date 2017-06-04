package com.frameDemo.model;

/**
 * 数据库表t_user_associate_device
 *
 * @author zimu
 * @date 2016-11-05 15:26:20 中国标准时间
 */
public class UserAssociateDevice {

    /**
     * 用户-设备关联关系记录ID
     */
    private String associateID;
    /**
     * 用户ID
     */
    private String userID;
    /**
     * 设备ID
     */
    private String deviceID;

    public UserAssociateDevice() {
    }

    public String getAssociateID() {
        return this.associateID;
    }

    public void setAssociateID(String associateID) {
        this.associateID = associateID;
    }

    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDeviceID() {
        return this.deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

}