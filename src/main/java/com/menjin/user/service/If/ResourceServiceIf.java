package com.menjin.user.service.If;

import java.util.List;

import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.base.service.BaseService;
import com.menjin.user.model.Resource;
import com.menjin.user.model.RolesResource;

/**
 * 访问资源管理的接口
 * @author Jack
 *
 */
public interface ResourceServiceIf extends BaseService<Resource> , FilterInvocationSecurityMetadataSource{

	/**
	 * 获取全部的访问资源
	 * @return
	 */
	public List<Resource> findAllResource();
	
	/**
	 * 根据访问资源的路径查找
	 * @param resourceUrl
	 * @return
	 */
	public Resource findByResourceUrl(String resourceUrl);
	
	/**
	 * 根据用户Id获取该用户可访问的资源（权限）
	 * @param userId
	 * @return
	 */
	public List<Resource> findResourcesByUserId(Integer userId);
	
	/**
	 * 根据登录名获取该用户可访问的资源（权限）
	 * @param username
	 * @return
	 */
	public List<Resource> findResourcesByUsername(String username);
	
	/**
	 * 插入记录到t_role_resource表，建立role与resource的关联关系
	 * @param rolesResource
	 * @return
	 */
	public int addRolesResource(RolesResource rolesResource);
}
