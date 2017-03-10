package com.menjin.opener.model;

import java.util.Date;

/**
 * 继电器开关对象
 * @author Wille
 *
 */
public class Opener {
	
	/**
	 * 开关唯一ID
	 */
	private Integer id;
	
	/**
	 * 开关对应继电器名字
	 */
	private String name;
	
	/**
	 * 继电器所在IP
	 */
	private String ip;
	
	/**
	 * 继电器所在端口
	 */
	private Integer port;
	
	/**
	 * 第几号开关（一般继电器有两个开关）
	 */
	private String num;
	
	/**
	 * 继电器创建人
	 */
	private String createBy;
	
	/**
	 * 继电器创建时间
	 */
	private Date createTime;

	
	@Override
	public String toString() {
		return "Opener [id=" + id + ", name=" + name + ", ip=" + ip + ", port=" + port + ", num=" + num + ", createBy="
				+ createBy + ", createDate=" + createTime + "]";
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createDate) {
		this.createTime = createDate;
	}
	
}
