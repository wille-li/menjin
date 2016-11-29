package com.menjin.api.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author Lin
 *
 */
public class APIDepartment {

	private Integer id;

    /**
     * 公司信息
     */
	private Integer companyID;

	/**
	 * 部门名称
	 */
	private String departmentName;

	/**
	 * 部门经理
	 */
	private String departmentHeader;

	/**
	 * 部门电话
	 */
	private String departmentPhone;

	private Date createTime;

	private String createBy;

	private Date modifiedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCompanyID() {
		return companyID;
	}

	public void setCompanyID(Integer companyID) {
		this.companyID = companyID;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentHeader() {
		return departmentHeader;
	}

	public void setDepartmentHeader(String departmentHeader) {
		this.departmentHeader = departmentHeader;
	}

	public String getDepartmentPhone() {
		return departmentPhone;
	}

	public void setDepartmentPhone(String departmentPhone) {
		this.departmentPhone = departmentPhone;
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
		return "Department [id=" + id + ", companyID=" + companyID
				+ ", departmentName=" + departmentName + ", departmentHeader="
				+ departmentHeader + ", departmentPhone=" + departmentPhone
				+ ", createTime=" + createTime + ", createBy=" + createBy
				+ ", modifiedDate=" + modifiedDate + "]";
	}

}
