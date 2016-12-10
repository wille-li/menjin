package com.menjin.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.base.entity.SimplePage;
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
	
	public List<User> searchUserByRolename(@Param("roleName") String roleName,
			@Param("page") SimplePage page,
			@Param("orderByField") String orderBy);

	public int searchCountByRolename(String roleName);
}

