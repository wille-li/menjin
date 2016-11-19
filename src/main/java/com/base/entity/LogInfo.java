package com.base.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Log 对象类
 * @author Wille
 *
 */
public class LogInfo implements Serializable{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -1263073076153037525L;
	
	/**
	 * Log id
	 */
	private Integer id;

	/**
	 * log描述
	 */
	private String description;
	
	/**
	 * 方法名
	 */
	private String method;
	
	/**
	 * 客户端IP
	 */
	private String requestIP;
	
	/**
	 * 服务器IP
	 */
	private String serverIP;
	
	/**
	 * Log类型
	 * 1:控制器log
	 */
	private String logType;
	
	/**
	 * 错误代号
	 */
	private String exceptionCode;
	
	/**
	 * 错误描述
	 */
	private String exceptionDetail;
	
	/**
	 * 参数字符串
	 */
	private String params;
	
	/**
	 * 运行时间
	 */
	private Long runTime;

	/**
	 * 创建者
	 */
	private String createBy;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
		return "LogInfo [id=" + id + ", description=" + description + ", method=" + method + ", requestIP=" + requestIP
				+ ", serverIP=" + serverIP + ", logType=" + logType + ", exceptionCode=" + exceptionCode
				+ ", exceptionDetail=" + exceptionDetail + ", params=" + params + ", runTime=" + runTime + ", createBy="
				+ createBy + ", createDate=" + createDate + "]";
	}

}
