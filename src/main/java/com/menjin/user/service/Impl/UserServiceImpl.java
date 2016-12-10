package com.menjin.user.service.Impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.base.entity.SimplePage;
import com.base.mapper.BaseCrudMapper;
import com.base.service.BaseServiceImpl;
import com.menjin.user.mapper.RoleMapper;
import com.menjin.user.mapper.UserMapper;
import com.menjin.user.model.Role;
import com.menjin.user.model.User;
import com.menjin.user.service.If.UserServiceIf;

/**
 * User管理的接口实现类
 * @author Jack
 *
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserServiceIf {

	@Resource
	private UserMapper userMapper;
	
	@Resource
	private RoleMapper roleMapper;
	
	@Override
	public BaseCrudMapper<User> init() {
		// TODO Auto-generated method stub
		return this.userMapper;
	}
	
	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return userMapper.selectAllUser();
	}

	@Override
	public User findByUsername(String username){
		return userMapper.selectByUserName(username);
	}
	
	@Override
	public List<User> findUserByRolename(String roleName,SimplePage page,String orderBy){
		return userMapper.searchUserByRolename(roleName,page,orderBy);
	}

	@Override
	public int findCountByRolename(String roleName){
		return userMapper.searchCountByRolename(roleName);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userMapper.selectByUserName(username);
		if(user==null){
			throw new UsernameNotFoundException("用户名"+username+"不存在");
		}
		Set<Role> roles = roleMapper.searchRoleByUsername(username);
		user.setRoles(roles);
		return user;
	}
}
