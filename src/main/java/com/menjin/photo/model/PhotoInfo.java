package com.menjin.photo.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 图片对象
 * @author wille
 *
 */
public class PhotoInfo implements Serializable {

	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 3335587507278282347L;

	/**
	 * 图片ID
	 */
	private Integer id;
	
	/**
	 * 图片的名字
	 */
	private String name;
	
	/**
	 * 图片所在路径
	 */
	private String path;
	
	/**
	 * 图片大小
	 */
	private Long size;
	
	/**
	 * 身份证
	 */
	private String idCardNum;
	
	/**
	 * 1. 身份证
	 * 2. 验证图片
	 */
	private Integer picType;
	/**
	 * 创建者
	 */
	private String createBy;
	
	/**
	 * 创建日期
	 */
	private Date createDate;

	
	
	public String getIdCardNum() {
		return idCardNum;
	}

	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}

	public Integer getPicType() {
		return picType;
	}

	public void setPicType(Integer picType) {
		this.picType = picType;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}


	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
