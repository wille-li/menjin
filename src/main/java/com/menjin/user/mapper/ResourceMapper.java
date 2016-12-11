package com.menjin.user.mapper;

import java.util.List;

import com.base.mapper.BaseCrudMapper;
import com.menjin.user.model.Resource;
import com.menjin.user.model.RolesResource;

/**
 * 访问资源管理
 * @author Jack
 *
 */
public interface ResourceMapper extends BaseCrudMapper<Resource> {

	/**
	 * 根据访问资源的路径查找
	 * @param resourceUrl
	 * @return
	 */
	public Resource selectByResourceUrl(String resourceUrl);
	
	/**
	 * 根据用户Id获取该用户可访问的资源（权限）
	 * @param userId
	 * @return
	 */
	public List<Resource> getResourcesByUserId(Integer userId);
	
	/**
	 * 根据登录名获取该用户可访问的资源（权限）
	 * @param username
	 * @return
	 */
	public List<Resource> getResourcesByUsername(String username);
	
	/**
	 * 获取全部的访问资源
	 * @return
	 */
	public List<Resource> selectAllResources();
	
	/**
	 * 插入记录到t_role_resource表，建立role与resource的关联关系
	 * @param rolesResource
	 * @return
	 */
	public int insertRolesResource(RolesResource rolesResource);
	
	/**
	 * 根据角色Id获取可访问的资源（权限）
	 * @param roleId
	 * @return
	 */
	public List<Resource> getResourcesByRoleId(Integer roleId);
	
	/**
	 * 获取父目录资源
	 * @return
	 */
	public List<Resource>  selectParent();
	
}
