package com.frameDemo.model;

/**
 * 数据库表t_data_uric_cid
 * @author zimu
 * @date 2016-11-05 15:26:20 中国标准时间
 */
public class DataUricCid{

	/**
	 * 尿酸记录ID
	 */
	private String uaID;
	/**
	 * 关联userID和deviceID
	 */
	private String associateID;
	/**
	 * 测量时间
	 */
	private java.util.Date measureTime;
	/**
	 * 尿酸（单位：mmol/L 有效范围：0.18~1.19）
	 */
	private Double uA;
	/**
	 * 是否上传
	 */
	private Boolean upload;
	/**
	 * 保留字节
	 */
	private String rES;
	/**
	 * 创建记录时间
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

	public DataUricCid() {
	}
	
	public String getUaID(){
		return this.uaID;
	}

	public void setUaID(String uaID) {
		this.uaID = uaID;
	}
	
	public String getAssociateID(){
		return this.associateID;
	}

	public void setAssociateID(String associateID) {
		this.associateID = associateID;
	}
	
	public java.util.Date getMeasureTime(){
		return this.measureTime;
	}

	public void setMeasureTime(java.util.Date measureTime) {
		this.measureTime = measureTime;
	}
	
	public Double getUA(){
		return this.uA;
	}

	public void setUA(Double uA) {
		this.uA = uA;
	}
	
	public Boolean getUpload(){
		return this.upload;
	}

	public void setUpload(Boolean upload) {
		this.upload = upload;
	}
	
	public String getRES(){
		return this.rES;
	}

	public void setRES(String rES) {
		this.rES = rES;
	}
	
	public java.util.Date getCreatTime(){
		return this.creatTime;
	}

	public void setCreatTime(java.util.Date creatTime) {
		this.creatTime = creatTime;
	}
	
	public String getDescribtion(){
		return this.describtion;
	}

	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}
	
	public Boolean getDeleteFlag(){
		return this.deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
}