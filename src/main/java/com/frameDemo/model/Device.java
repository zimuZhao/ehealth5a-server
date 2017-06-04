package com.frameDemo.model;

/**
 * 数据库表t_device
 * @author zimu
 * @date 2016-11-05 15:26:20 中国标准时间
 */
public class Device {

	/**
	 * 设备ID
	 */
	private String deviceID;
	/**
	 * 设备名
	 */
	private String deviceName;
	/**
	 * 设备类型（针对多源数据）
	 */
	private String deviceType;
	/**
	 * 协议版本号
	 */
	private String protocolVersion;
	/**
	 * 创建时间
	 */
	private java.util.Date creatTime;
	/**
	 * 预留字段
	 */
	private String description;
	/**
	 * 删除标记
	 */
	private Boolean deleteFlag;

	public Device() {
	}
	
	public String getDeviceID(){
		return this.deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	
	public String getDeviceName(){
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	public String getDeviceType(){
		return this.deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
	public String getProtocolVersion(){
		return this.protocolVersion;
	}

	public void setProtocolVersion(String protocolVersion) {
		this.protocolVersion = protocolVersion;
	}
	
	public java.util.Date getCreatTime(){
		return this.creatTime;
	}

	public void setCreatTime(java.util.Date creatTime) {
		this.creatTime = creatTime;
	}
	
	public String getDescription(){
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getDeleteFlag(){
		return this.deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
}