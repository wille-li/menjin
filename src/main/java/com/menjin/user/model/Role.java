package com.menjin.user.model;

import java.io.Serializable;

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

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", description="
				+ description + "]";
	}
	
}
