package com.menjin.api.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class APIVisit {
	
	private String idCardNum;
	
	private String companyName;
	
	private String interviewee;
	
	private String phoneNum;
	
	@JsonFormat(pattern="yyyy-MM-dd hh24:mm")
	private Date appointmentTime;
	
	private Integer status;
	
	private Date expireDate;
	
	private String createBy;
	
	private Date modifyDate;

	public String getIdCardNum() {
		return idCardNum;
	}

	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getInterviewee() {
		return interviewee;
	}

	public void setInterviewee(String interviewee) {
		this.interviewee = interviewee;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
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
		return "APIVisit [idCardNum=" + idCardNum + ", companyName=" + companyName + ", interviewee=" + interviewee
				+ ", phoneNum=" + phoneNum + ", appointmentTime=" + appointmentTime + ", status=" + status
				+ ", expireDate=" + expireDate + ", createBy=" + createBy + ", modifyDate=" + modifyDate + "]";
	}

}
