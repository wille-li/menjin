package com.base.entity;

import java.io.Serializable;
import java.util.Date;

public class LogInfo implements Serializable{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -1263073076153037525L;

	private String description;
	
	private String method;
	
	private String requestIP;
	
	private String serverIP;
	
	private String logType;
	
	private String exceptionCode;
	
	private String exceptionDetail;
	
	private String params;
	
	private Long runTime;

	private String createBy;
	
	private Date createDate;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getRequestIP() {
		return requestIP;
	}

	public void setRequestIP(String requestIP) {
		this.requestIP = requestIP;
	}

	public String getServerIP() {
		return serverIP;
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public String getExceptionDetail() {
		return exceptionDetail;
	}

	public void setExceptionDetail(String exceptionDetail) {
		this.exceptionDetail = exceptionDetail;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	public Long getRunTime() {
		return runTime;
	}

	public void setRunTime(Long runTime) {
		this.runTime = runTime;
	}

	@Override
	public String toString() {
		return "LogInfo [description=" + description + ", method=" + method + ", requestIP=" + requestIP + ", serverIP="
				+ serverIP + ", logType=" + logType + ", exceptionCode=" + exceptionCode + ", exceptionDetail="
				+ exceptionDetail + ", params=" + params + ", runTime=" + runTime + ", createBy=" + createBy
				+ ", createDate=" + createDate + "]";
	}

}
