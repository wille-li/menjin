package com.menjin.user.model;

import java.io.Serializable;

/**
 * 权限role与资源resource的关联关系对象
 * role与resource为多对多的关系
 * @author Jack
 *
 */
public class RolesResource implements Serializable{

		private static final long serialVersionUID = -6930320802471903560L;

		//关联t_resource表的主键id
		private Integer resourceId;
		
		//关联t_role表的主键id
		private Integer roleId;

		public RolesResource() {

		}

		public Integer getResourceId() {
			return resourceId;
		}

		public void setResourceId(Integer resourceId) {
			this.resourceId = resourceId;
		}

		public Integer getRoleId() {
			return roleId;
		}

		public void setRoleId(Integer roleId) {
			this.roleId = roleId;
		}

		@Override
		public String toString() {
			return "RolesResource [resourceId=" + resourceId
					+ ", roleId=" + roleId + "]";
		}
		
}
