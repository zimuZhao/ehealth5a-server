package com.frameDemo.model;

/**
 * 数据库表t_data_chol
 * @author zimu
 * @date 2016-11-05 15:26:20 中国标准时间
 */
public class DataChol {

	/**
	 * 总胆固醇记录ID
	 */
	private String cholID;
	/**
	 * 关联userID和deviceID
	 */
	private String associateID;
	/**
	 * 测量时间
	 */
	private java.util.Date measureTime;
	/**
	 * 总胆固醇（单位：mmol/L 有效范围：2.59~10.35 有效精度：0.01）
	 */
	private Double cHOL;
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

	public DataChol() {
	}
	
	public String getCholID(){
		return this.cholID;
	}

	public void setCholID(String cholID) {
		this.cholID = cholID;
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
	
	public Double getCHOL(){
		return this.cHOL;
	}

	public void setCHOL(Double cHOL) {
		this.cHOL = cHOL;
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