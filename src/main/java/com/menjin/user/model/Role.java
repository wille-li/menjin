package com.menjin.user.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

/**
 * Role权限对象
 * @author Jack
 *
 */
public class Role implements Serializable{

	private static final long serialVersionUID = 601683194325682005L;

	//与t_role表关联的主键id
	private Integer id;
	
	//权限名
	private String name;
	
	//描述
	private String description;
	
	private Set<Resource> resources = new HashSet<Resource>();  
	 
	private Set<User> users = new HashSet<User>();  

	public Role() {

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", description="
				+ description + "]";
	}
	
	 public GrantedAuthority generateGrantedAuthority() {  
	        return new GrantedAuthority() {  
	            public String getAuthority() {  
	                return getName();  
	            }  
	        };  
	    }  
}
