package com.menjin.company.model;

public class BatchUploadEmployees {

	public BatchUploadEmployees() {
		super();
	}

	public BatchUploadEmployees(String companyName, String companyAddress,
			String companyPhone, String doorPlate, String employeeNo,
			String employeeName, String employeeSex, String email,
			String mobile, String idCardType, String idCardNum) {
		super();
		this.companyName = companyName;
		this.companyAddress = companyAddress;
		this.companyPhone = companyPhone;
		this.doorPlate = doorPlate;
		this.employeeNo = employeeNo;
		this.employeeName = employeeName;
		this.employeeSex = employeeSex;
		this.email = email;
		this.mobile = mobile;
		IdCardType = idCardType;
		IdCardNum = idCardNum;
	}

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

	/**
	 * 公司门牌号
	 */
	private String doorPlate;

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
	 * 证件类型 1为身份证 2为护照号 3其他
	 */
	private String IdCardType;

	/**
	 * 证件号码
	 */
	private String IdCardNum;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public String getDoorPlate() {
		return doorPlate;
	}

	public void setDoorPlate(String doorPlate) {
		this.doorPlate = doorPlate;
	}

}
