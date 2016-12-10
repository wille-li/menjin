package com.menjin.user.service.If;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.base.entity.SimplePage;
import com.base.service.BaseService;
import com.menjin.user.model.User;

/**
 * User管理的接口
 * @author Jack
 *
 */
public interface UserServiceIf extends BaseService<User> , UserDetailsService{

	/**
	 * 根据用户名查用户信息
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);
	
	/**
	 * 查询全部用户信息
	 * @return
	 */
	public List<User> findAllUser();
	
	/**
	 * 根据角色名查询用户信息(包含分页和排序的功能)
	 * @return
	 */
	public List<User> findUserByRolename(String roleName,SimplePage page,String orderBy);
	
	/**
	 * 根据角色名查询用户数量
	 * @return
	 */
	public int findCountByRolename(String roleName);
}
