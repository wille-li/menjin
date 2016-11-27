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
	 * 图片属于哪个用户
	 */
	private Integer uid;
	
	/**
	 * 创建者
	 */
	private String createBy;
	
	/**
	 * 创建日期
	 */
	private Date createDate;

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

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
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

	@Override
	public String toString() {
		return "PhotoInfo [id=" + id + ", name=" + name + ", path=" + path + ", size=" + size + ", uid=" + uid
				+ ", createBy=" + createBy + ", createDate=" + createDate + "]";
	}
	
}
