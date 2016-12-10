package com.menjin.user.service.Impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.base.mapper.BaseCrudMapper;
import com.base.service.BaseServiceImpl;
import com.menjin.user.mapper.ResourceMapper;
import com.menjin.user.mapper.RoleMapper;
import com.menjin.user.model.Resource;
import com.menjin.user.model.Role;
import com.menjin.user.model.RolesResource;
import com.menjin.user.service.If.ResourceServiceIf;

/**
 * 访问资源管理的接口实现类
 * @author Jack
 *
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements ResourceServiceIf {

	private static final Logger logger = Logger.getLogger(ResourceServiceImpl.class);
	
	@Autowired
	private ResourceMapper resourceMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	private PathMatcher matcher = new AntPathMatcher();
	
	private static Map<String,Collection<ConfigAttribute>> resourceMap = null;
	
	@Override
	public List<Resource> findAllResource() {
		// TODO Auto-generated method stub
		return resourceMapper.selectAllResources();
	}

	@Override
	public BaseCrudMapper<Resource> init() {
		// TODO Auto-generated method stub
		return this.resourceMapper;
	}

	@Override
	public Resource findByResourceUrl(String resourceUrl) {
		// TODO Auto-generated method stub
		return resourceMapper.selectByResourceUrl(resourceUrl);
	}

	@Override
	public List<Resource> findResourcesByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return resourceMapper.getResourcesByUserId(userId);
	}

	@Override
	public List<Resource> findResourcesByUsername(String username) {
		// TODO Auto-generated method stub
		return resourceMapper.getResourcesByUsername(username);
	}

	@Override
	public int addRolesResource(RolesResource rolesResource) {
		// TODO Auto-generated method stub
		return resourceMapper.insertRolesResource(rolesResource);
	}
	
	@Override
	public List<Resource> findResourcesByRoleId(Integer roleId){
		return resourceMapper.getResourcesByRoleId(roleId);
	};
	
	@PostConstruct 
	public void initResource() throws Exception{
		this.resourceMap = new HashMap<String,Collection<ConfigAttribute>>();
		for(Resource item:resourceMapper.selectAllResources()){
			Set<Role> roles = roleMapper.searchRoleByResourceId(item.getId());
			item.setRoles(roles);
			resourceMap.put(item.getResourceUrl(),setToCollection(roles));
		}
	}
	
	private Collection<ConfigAttribute> setToCollection(Set<Role> roles){
		List<ConfigAttribute> list = new ArrayList<ConfigAttribute>();
		for(Role role : roles){
			list.add(new SecurityConfig(role.getName()));
		}
		return list;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String url = ((FilterInvocation)object).getRequestUrl();
		logger.info("getAttributes方法：正在访问的url为："+url);
		Iterator<String> it = resourceMap.keySet().iterator();
		while(it.hasNext()){
			String resUrl = it.next();
			if(matcher.match(resUrl, url)){
				Collection<ConfigAttribute> returnCollection = resourceMap.get(resUrl);
				return returnCollection;
			}
		}
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Set<ConfigAttribute>  allAttributes = new HashSet<ConfigAttribute>();
		for(Map.Entry<String, Collection<ConfigAttribute>> entry : resourceMap.entrySet()){
			allAttributes.addAll(entry.getValue());
		}
		return allAttributes;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}
}
