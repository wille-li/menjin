package com.menjin.user.service.Impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.mapper.BaseCrudMapper;
import com.base.service.BaseServiceImpl;
import com.menjin.user.mapper.RoleMapper;
import com.menjin.user.model.Role;
import com.menjin.user.model.UserRoles;
import com.menjin.user.service.If.RoleServiceIf;

/**
 * 权限管理的接口实现类
 * @author Jack
 *
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleServiceIf {

	@Resource
	private RoleMapper roleMapper;
	
	@Override
	public BaseCrudMapper<Role> init() {
		// TODO Auto-generated method stub
		return this.roleMapper;
	}

	@Override
	public Role findByRoleName(String roleName) {
		// TODO Auto-generated method stub
		return roleMapper.selectByRoleName(roleName);
	}

	@Override
	public int addUserRoles(UserRoles userRoles) {
		// TODO Auto-generated method stub
		return roleMapper.insertUserRoles(userRoles);
	}

	@Override
	public int deleteByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return roleMapper.deleteRoleIdByUserId(userId);
	}

	@Override
	public Set<Role> findRoleByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return roleMapper.searchRoleByUserId(userId);
	}

	@Override
	public Set<Role> findRoleByUsername(String username) {
		// TODO Auto-generated method stub
		return roleMapper.searchRoleByUsername(username);
	}

	@Override
	public Set<Role> findRoleByResourceId(Integer resourceId) {
		// TODO Auto-generated method stub
		return roleMapper.searchRoleByResourceId(resourceId);
	}
	
}
