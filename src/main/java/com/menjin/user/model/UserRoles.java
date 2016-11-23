package com.menjin.user.model;

import java.io.Serializable;

/**
 * 用户user与权限role关联关系对象
 * @author Jack
 *
 */
public class UserRoles implements Serializable{
	
	private static final long serialVersionUID = 2242764244318222826L;

	//与t_user_role关联的主键id
	private Integer id ;
	
	//关联t_user表的主键id
	private Integer userId;
	
	//关联t_role表的主键id
	private Integer roleId;

	public UserRoles() {
	
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "UserRoles [id=" + id + ", userId=" + userId + ", roleId="
				+ roleId + "]";
	}
	
}
