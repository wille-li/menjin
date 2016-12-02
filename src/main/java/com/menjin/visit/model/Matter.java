package com.menjin.visit.model;

import java.util.Date;

/**
 * 
 * @author Lin
 *
 */
public class Matter {

	private Integer id;
	
	private String matterDecs;
	
	private String createBy;
	
	private Date modifiedDate;
	
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMatterDecs() {
		return matterDecs;
	}

	public void setMatterDecs(String matterDecs) {
		this.matterDecs = matterDecs;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Matter [id=" + id + ", matterDecs=" + matterDecs
				+ ", createBy=" + createBy + ", modifiedDate=" + modifiedDate
				+ ", createTime=" + createTime + "]";
	}

}
