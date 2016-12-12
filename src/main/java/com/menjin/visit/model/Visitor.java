package com.menjin.visit.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author Lin
 *
 */
public class Visitor {

	private Integer id;
	
	/**
	 * 访客名称
	 */
	private String visitorName;
	
	/**
	 * 证件类型
	 */
	private String IdCardType;
	
	/**
	 * 证件号
	 */
	private String IdCardNum;
	
	/**
	 * 性别
	 */
	private String sex;
	
	/**
	 * 民族
	 */
	private String nation;
	
	/**
	 * 出身日月
	 */
	private Date birth;
	
	/**
	 * 访客照片地址路径
	 */
	private String pictureUrl;
	
	/**
	 * 访客公司地址等。。
	 */
	private String address;
	
	private String mobile;
	
	private String email;
	
	/**
	 * 1.普通等级  2.白名单  3.黑名单
	 */
	private String rank;
	
	private String createBy;
	
	private Date createdTime;
	
	private Date modifiedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

	public String getIdCardType() {
		return IdCardType;
	}

	public void setIdCardType(String idCardType) {
		IdCardType = idCardType;
	}

	public String getIdCardNum() {
		return IdCardNum;
	}

	public void setIdCardNum(String idCardNum) {
		IdCardNum = idCardNum;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd")
	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Override
	public String toString() {
		return "Visitor [id=" + id + ", visitorName=" + visitorName
				+ ", IdCardType=" + IdCardType + ", IdCardNum=" + IdCardNum
				+ ", sex=" + sex + ", nation=" + nation + ", birth=" + birth
				+ ", pictureUrl=" + pictureUrl + ", address=" + address
				+ ", mobile=" + mobile + ", email=" + email + ", rank=" + rank
				+ ", createBy=" + createBy + ", createdTime=" + createdTime
				+ ", modifiedDate=" + modifiedDate + "]";
	}

	
}
