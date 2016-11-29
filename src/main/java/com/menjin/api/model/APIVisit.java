package com.menjin.api.model;

import java.util.Date;

public class APIVisit {
	
	private Integer id;
	
	private Integer visitorID;
	
	private Integer employeeID;
	
	private Date appointmentTime;
	
	private Integer status;
	
	private Date expireDate;
	
	private String createBy;
	
	private Date modifyDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVisitorID() {
		return visitorID;
	}

	public void setVisitorID(Integer visitorID) {
		this.visitorID = visitorID;
	}

	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public Date getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "APIVisit [id=" + id + ", visitorID=" + visitorID + ", employeeID=" + employeeID + ", appointmentTime="
				+ appointmentTime + ", status=" + status + ", expireDate=" + expireDate + ", createBy=" + createBy
				+ ", modifyDate=" + modifyDate + "]";
	}
	
}
