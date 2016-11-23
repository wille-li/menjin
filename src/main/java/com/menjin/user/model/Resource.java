package com.menjin.user.model;

import java.io.Serializable;

/**
 * 资源Resource实体
 * @author Jack
 *
 */
public class Resource implements Serializable{

	private static final long serialVersionUID = -5618086043877626022L;

	//t_resource表对应的主键id
	private Integer id;
	
	//访问资源的url
	private String resourceUrl;
	
	//访问的资源名
	private String name;
	
	//上一级id
	private Integer parentId;
	
	//访问资源的关键字
	private String resKey;
	
	//描述
	private String description;

	public Resource() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getResKey() {
		return resKey;
	}

	public void setResKey(String resKey) {
		this.resKey = resKey;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Resource [id=" + id + ", resourceUrl=" + resourceUrl
				+ ", name=" + name + ", parentId=" + parentId + ", resKey="
				+ resKey + ", description=" + description + "]";
	}
	
	
}
	
	