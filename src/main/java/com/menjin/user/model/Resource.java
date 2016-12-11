package com.menjin.user.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

/**
 * 资源Resource实体
 * @author Jack
 *
 */
public class Resource implements Serializable , GrantedAuthority {

	private static final long serialVersionUID = -5618086043877626022L;

	//t_resource表对应的主键id
	private Integer id;
	
	//访问资源的url
	private String resourceUrl;
	
	//访问的资源名
	private String name;
	
	//上一级目录资源名
	private String parentDesc;
	
	//访问资源的关键字
	private String resKey;
	
	//描述
	private String description;
	
	private Set<Role> roles = new HashSet<Role>(); 

	public Resource() {

	}

	public String getParentDesc() {
		return parentDesc;
	}

	public void setParentDesc(String parentDesc) {
		this.parentDesc = parentDesc;
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
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	
	public String getAuthority() {  
        return getName();  
    }

	@Override
	public String toString() {
		return "Resource [id=" + id + ", resourceUrl=" + resourceUrl
				+ ", name=" + name + ", parentDesc="
				+ parentDesc + ", resKey=" + resKey + ", description="
				+ description + ", roles=" + roles + "]";
	}  
	
}
	
	