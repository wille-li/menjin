package com.menjin.user.service.If;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

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
}
