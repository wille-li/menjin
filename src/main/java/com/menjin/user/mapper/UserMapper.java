package com.menjin.user.mapper;

import java.util.List;

import com.base.mapper.BaseCrudMapper;
import com.menjin.user.model.User;

/**
 * 用户管理接口
 * @author Jack
 *
 */
public interface UserMapper extends BaseCrudMapper<User> {

	/**
	 * 根据用户名查用户信息
	 * @param username
	 * @return
	 */
	public User selectByUserName(String username);
	
	public List<User> selectAllUser();
}
