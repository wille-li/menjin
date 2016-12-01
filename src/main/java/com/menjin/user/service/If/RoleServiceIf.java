package com.menjin.user.service.If;

import java.util.Set;
import com.base.service.BaseService;
import com.menjin.user.model.Role;
import com.menjin.user.model.UserRoles;
/**
 * 用户角色管理接口
 * @author Jack
 *
 */
public interface RoleServiceIf extends BaseService<Role> {

	/**
	 * 根据用户角色名查找
	 * @param roleName
	 * @return
	 */
	public Role findByRoleName(String roleName);
	
	/**
	 * 插入数据到t_user_role做关联关系
	 * @param userRoles
	 * @return
	 */
	public int addUserRoles(UserRoles userRoles);
	
	/**
	 * 根据用户的id删除t_user_role表中的记录
	 * @param userId
	 * @return
	 */
	public int deleteByUserId(Integer userId);
	
	/**
	 * 根据userId查找用户的角色信息
	 * @param userId
	 * @return
	 */
	public Set<Role> findRoleByUserId(Integer userId);
	
	/**
	 * 根据登录名查找用户的角色信息
	 * @param username
	 * @return
	 */
	public Set<Role> findRoleByUsername(String username);
	
	/**
	 * 根据资源resourceId查找对应可访问的用户角色Role
	 * @param username
	 * @return
	 */
	public Set<Role> findRoleByResourceId(Integer resourceId);
}
