package com.menjin.api.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author Wille
 *
 */
public class APICompany implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3747944037894841049L;

	private Integer id;

	/**
	 * 公司名称
	 */
	private String companyName;

	/**
	 * 公司地址，楼层
	 */
	private String companyAddress;

	/**
	 * 公司电话
	 */
	private String companyPhone;

	private Date createTime;

	private String createBy;

	private Date modifiedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	@JsonIgnore
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@JsonIgnore
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	@JsonIgnore
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", companyName=" + companyName + ", companyAddress=" + companyAddress
				+ ", companyPhone=" + companyPhone + ", createTime=" + createTime + ", createBy=" + createBy
				+ ", modifiedDate=" + modifiedDate + "]";
	}

}
