package com.menjin.company.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Lin
 *
 */
public class Company implements Serializable {

	/** 
	 * @Fields serialVersionUID : 序列化 
	 */ 
	private static final long serialVersionUID = 5729248190241639460L;

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

	/**
	 * 公司门牌号
	 */
	private String doorPlate;

	private Date createTime;

	private String createBy;

	private Date modifiedDate;

	private List<Department> departments;

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public String getDoorPlate() {
		return doorPlate;
	}

	public void setDoorPlate(String doorPlate) {
		this.doorPlate = doorPlate;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", companyName=" + companyName
				+ ", companyAddress=" + companyAddress + ", companyPhone="
				+ companyPhone + ", createTime=" + createTime + ", createBy="
				+ createBy + ", modifiedDate=" + modifiedDate
				+ ", departments=" + departments + "]";
	}

}
