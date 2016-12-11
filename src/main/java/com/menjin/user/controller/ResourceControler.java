package com.menjin.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.base.annotation.log.SystemControllerLog;
import com.base.entity.SimplePage;
import com.menjin.user.model.Resource;
import com.menjin.user.service.If.ResourceServiceIf;

@Controller
public class ResourceControler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ResourceServiceIf resourceService;
	
	@RequestMapping(value="/resource.do")
	@SystemControllerLog
	public String searchResources(){
		return "user/resourceManage";
	}
	
	@RequestMapping(value="/resource/getAllResourceList.do")
	@SystemControllerLog
	@ResponseBody
	public Map getResourcesByPage(@Param(value="pageSize") Integer page,
			@Param(value="rows") Integer rows){
		logger.info("Start to getResourcesByPage");
		int count = resourceService.findCount(null, null);
		logger.info("Resources Count:"+count);
		SimplePage simplepage = new SimplePage(page, rows, count);
		Map<String, Object> params = null;
		String orderBy = null;
		logger.info("page="+page+", rows="+rows);
		List<Resource> resources = resourceService.findByPage(simplepage, params, orderBy);
		Map maps = new HashMap();
		maps.put("rows", resources);
		maps.put("total", count);
		logger.info("End getResourcesByPage");
		return maps;
	}
	
	@RequestMapping(value="/resource/deleteResource.do")
	@SystemControllerLog
	@ResponseBody
	public Integer deleteResource(@ModelAttribute Resource resource){
		logger.info("Start to delete resource,resourceUrl="+resource.getResourceUrl());
		int returnCode = resourceService.deleteById(resource);
		logger.info("End to delete resource,returnCode:"+returnCode);
		return returnCode;
	}
	
	@RequestMapping(value="/resource/addResource.do")
	@SystemControllerLog
	@ResponseBody
	public Integer addResource(@ModelAttribute Resource resource){
		logger.info("Start to add new resource,resourceUrl="+resource.getResourceUrl());
		int returnCode = resourceService.add(resource);
		logger.info("End to add resource,ReturnCode="+returnCode);
		return returnCode;
	}
	
	@RequestMapping(value="/resource/updateResource.do")
	@SystemControllerLog
	@ResponseBody
	public Integer updateResource(@ModelAttribute Resource resource){
		logger.info("Start to update resource!");
		int returnCode = resourceService.modifyById(resource);
		logger.info("End to update resource,ReturnCode:"+returnCode);
		return returnCode;
	}
	
	@RequestMapping(value="/resource/getParentResources.do")
	@SystemControllerLog
	@ResponseBody
	public String getParentResources(){
		logger.info("Start to getParentResources");
		List<Resource> list = resourceService.findParent();
		String str = "";
		if(list!=null&&list.size()>0){
			str = JSON.toJSONString(list);
			logger.info("json tranfer="+str);
		}
		logger.info("End getParentResources");
		return str;
	}
	
	/**
	   * 将权限转换成tree格式、
	   * @param privileges
	   * @return
	   */
	  private Map<String, List<Map<String, String>>> trimPrivileges(List<Resource> privileges){
	    	Map<String, List<Map<String, String>>> result = new HashMap<String, List<Map<String, String>>>();
	    	for (Resource privilege : privileges) {
				 String catalog = String.valueOf(privilege.getParentId());
				 Map<String, String> node = new HashMap<String, String>();
				 node.put("id",privilege.getId().toString());
				 node.put("text", privilege.getName());
				 if(result.get(catalog)!=null){
					 List<Map<String, String>> childrens = result.get(catalog);
					 childrens.add(node);
					 result.put(catalog, childrens);
				 }else{
					 List<Map<String, String>> childrens = new ArrayList<Map<String, String>>();
					 childrens.add(node);
					 result.put(catalog, childrens);
					 
				 }
			}
	    	return result;
	    }
	  
	  @ResponseBody
	    @RequestMapping(value="/resource/getResourceNodes.do")
		/**
		 * 获取权限列表
		 * @return
		 */
	    public List<Map<String, Object>> getPrivilegeNodes(){
	        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
	        int count = resourceService.findCount(null, null);
			logger.info("Resources Count:"+count);
			SimplePage simplepage = new SimplePage(1, count, count);
			Map<String, Object> params = null;
			String orderBy = "parentId";
			List<Resource> resources = resourceService.findByPage(simplepage, params, orderBy);
	        Map<String, List<Map<String, String>>> privileges = trimPrivileges(resources);
	        
	        for (Map.Entry<String, List<Map<String, String>>> privilege : privileges.entrySet()) {
	            Map<String, Object> node = new HashMap<String, Object>();
	            node.put("id", "");
	            node.put("text", privilege.getKey());
	            node.put("children", privilege.getValue());
	            results.add(node);
	        }
	        return results;
	    }
	
	/*
	@RequestMapping(value="/user/getUserByUsername.do")
	@SystemControllerLog
	@ResponseBody
	public Map getUserByUsername(@Param(value="pageSize") Integer page,@Param(value="rows") Integer rows,
			@Param(value="username") String username){
		logger.info("Start to getUserByUsername"+",username="+username);
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("username",username);
		int count = userService.findCount(null, params);
		logger.info("Users Count:"+count);
		SimplePage simplepage = new SimplePage(page, rows, count);
		String orderBy = null;
		logger.info("page="+page+", rows="+rows);
		List<User> users = userService.findByPage(simplepage, params, orderBy);
		for(User u:users){
			Set<Role> roles = roleService.findRoleByUserId(u.getId());
			u.setRoles(roles);
		}
		Map maps = new HashMap();
		maps.put("rows", users);
		maps.put("total", count);
		logger.info("End getUserByUsername");
		return maps;
	}
	
	@RequestMapping(value="/user/getUserByRolename.do")
	@SystemControllerLog
	@ResponseBody
	public Map getUserByRolename(@Param(value="pageSize") Integer page,@Param(value="rows") Integer rows,
			@Param(value="roleName") String roleName){
		logger.info("Start to getUserByRolename"+",roleName="+roleName);
		int count = userService.findCountByRolename(roleName);
		logger.info("Users Count:"+count);
		SimplePage simplepage = new SimplePage(page, rows, count);
		String orderBy = null;
		logger.info("page="+page+", rows="+rows);
		List<User> users = userService.findUserByRolename(roleName, simplepage, orderBy);
		for(User u:users){
			Set<Role> roles = roleService.findRoleByUserId(u.getId());
			u.setRoles(roles);
		}
		Map maps = new HashMap();
		maps.put("rows", users);
		maps.put("total", count);
		logger.info("End getUserByRolename");
		return maps;
	}
	*/
}
