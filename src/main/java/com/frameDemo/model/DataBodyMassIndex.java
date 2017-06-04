package com.frameDemo.model;

/**
 * 数据库表t_data_body_mass_index
 * @author zimu
 * @date 2016-11-05 15:26:20 中国标准时间
 */
public class DataBodyMassIndex {

	/**
	 * BMI记录ID
	 */
	private String bmiID;
	/**
	 * 数据库列associateID
	 */
	private String associateID;
	/**
	 * 测量时间
	 */
	private java.util.Date measureTime;
	/**
	 * 身高（单位：cm 有效范围：0~250 有效精度：1）
	 */
	private Integer height;
	/**
	 * 体重（单位：kg 有效范围：0~200 有效精度：1）
	 */
	private Integer weight;
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

	public DataBodyMassIndex() {
	}
	
	public String getBmiID(){
		return this.bmiID;
	}

	public void setBmiID(String bmiID) {
		this.bmiID = bmiID;
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
	
	public Integer getHeight(){
		return this.height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}
	
	public Integer getWeight(){
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
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