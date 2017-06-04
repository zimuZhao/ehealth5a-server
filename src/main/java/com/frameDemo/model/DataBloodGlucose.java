package com.frameDemo.model;

/**
 * 数据库表t_data_blood_glucose
 * @author zimu
 * @date 2016-11-05 15:26:20 中国标准时间
 */
public class DataBloodGlucose {

	/**
	 * 血糖记录ID
	 */
	private String bloodGlucoseID;
	/**
	 * 关联userID和deviceID
	 */
	private String associateID;
	/**
	 * 测量时间
	 */
	private java.util.Date measureTime;
	/**
	 * 血糖（单位：mmol/L 有效范围：1.1~33.3 有效精度：0.1）
	 */
	private Double gLU;
	/**
	 * 状态（0：餐后测量 1：餐前测量 2：随机测量）
	 */
	private Integer flag;
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
	 * 预留字段
	 */
	private String describtion;
	/**
	 * 删除标记
	 */
	private Boolean deleteFlag;

	public DataBloodGlucose() {
	}
	
	public String getBloodGlucoseID(){
		return this.bloodGlucoseID;
	}

	public void setBloodGlucoseID(String bloodGlucoseID) {
		this.bloodGlucoseID = bloodGlucoseID;
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
	
	public Double getGLU(){
		return this.gLU;
	}

	public void setGLU(Double gLU) {
		this.gLU = gLU;
	}
	
	public Integer getFlag(){
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
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