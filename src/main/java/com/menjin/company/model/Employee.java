package com.menjin.company.model;

import java.util.Date;

/**
 * 
 * @author Lin
 *
 */
public class Employee {

	private Integer id;

	/**
	 * 部门信息
	 */
	private Department department;
	
	/**
	 * 员工编号
	 */
	private String employeeNo;

	/**
	 * 员工名字
	 */
	private String employeeName;

	/**
	 * 性别
	 */
	private String employeeSex;

	/**
	 * 邮件
	 */
	private String email;

	/**
	 * 电话
	 */
	private String mobile;

	/**
	 * 证件类型   1为身份证    2为护照号  3其他
	 */
	private String IdCardType;

	/**
	 * 证件号码
	 */
	private String IdCardNum;

	private String createBy;

	private Date modifiedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeSex() {
		return employeeSex;
	}

	public void setEmployeeSex(String employeeSex) {
		this.employeeSex = employeeSex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	@Override
	public String toString() {
		return "Employee [id=" + id + ", department=" + department
				+ ", employeeNo=" + employeeNo + ", employeeName="
				+ employeeName + ", employeeSex=" + employeeSex + ", email="
				+ email + ", mobile=" + mobile + ", IdCardType=" + IdCardType
				+ ", IdCardNum=" + IdCardNum + ", createBy=" + createBy
				+ ", modifiedDate=" + modifiedDate + "]";
	}


	
}
