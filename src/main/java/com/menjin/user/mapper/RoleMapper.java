package com.menjin.user.mapper;

import java.util.Set;
import com.base.mapper.BaseCrudMapper;
import com.menjin.user.model.Role;
import com.menjin.user.model.UserRoles;

/**
 * 权限管理接口
 * @author Jack
 *
 */
public interface RoleMapper extends BaseCrudMapper<Role> {

	/**
	 * 根据用户角色名查找
	 * @param roleName
	 * @return
	 */
	public Role selectByRoleName(String roleName);
	
	/**
	 * 插入数据到t_user_role做关联关系
	 * @param userRoles
	 * @return
	 */
	public int insertUserRoles(UserRoles userRoles);
	
	/**
	 * 根据用户的id删除t_user_role表中的记录
	 * @param userId
	 * @return
	 */
	public int deleteRoleIdByUserId(Integer userId);
	
	/**
	 * 根据userId查找用户的角色信息
	 * @param userId
	 * @return
	 */
	public Set<Role> searchRoleByUserId(Integer userId);
	
	/**
	 * 根据登录名查找用户拥有的角色信息
	 * @param username
	 * @return
	 */
	public Set<Role> searchRoleByUsername(String username);
	
	/**
	 * 根据登录名查找用户没有的角色信息
	 * @param username
	 * @return
	 */
	public Set<Role> searchNoRoleByUsername(String username);
	
	/**
	 * 根据资源resourceId查找对应可访问的用户角色Role
	 * @param resourceId
	 * @return
	 */
	public Set<Role> searchRoleByResourceId(Integer resourceId);
	
	
}
