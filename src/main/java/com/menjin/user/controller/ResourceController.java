package com.menjin.user.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.annotation.log.SystemControllerLog;
import com.base.entity.SimplePage;
import com.menjin.user.model.Resource;
import com.menjin.user.service.If.ResourceServiceIf;
import com.menjin.user.service.If.RoleServiceIf;

@Controller
public class ResourceController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ResourceServiceIf resourceService;

	@Autowired
	private RoleServiceIf roleService;

	@RequestMapping(value = "/resource.do")
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
		String url = resource.getResourceUrl()+"*";
		resource.setResourceUrl(url);
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
	public List<Map<String,String>> getParentResources(HttpServletResponse resp){
		logger.info("Start to getParentResources");
		List<String> list = resourceService.findParent();
		String str = "";
		List<Map<String,String>> listStr = new ArrayList<Map<String,String>>();
		if(list!=null&&list.size()>0){
			for(String s :list){
				Map<String,String> map = new HashMap<String,String>();
				map.put("parentDesc", s);
				listStr.add(map);
			}
			logger.info("json tranfer="+str);
		}
		logger.info("End getParentResources");

		return listStr;
	}
	
	@RequestMapping(value="/resource/getChildrenResources.do")
	@SystemControllerLog
	@ResponseBody
	public List<Resource> getChildrenResources(String parentDesc ,HttpServletResponse resp){
		logger.info("Start to getChildrenResources");
		List<Resource> list = resourceService.findChildren(parentDesc);
		return list;
	}
	
	/**
	   * 将权限转换成tree格式、
	   * @param privileges
	   * @return
	   */
	  private Map<String, List<Map<String, String>>> trimPrivileges(List<Resource> privileges){
	    	Map<String, List<Map<String, String>>> result = new HashMap<String, List<Map<String, String>>>();
	    	for (Resource privilege : privileges) {
				 String catalog = privilege.getParentDesc();
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
	  
	    @RequestMapping(value="/resource/getResourceByUrl.do")
		@SystemControllerLog
		@ResponseBody
		public Map getResourceByUrl(@Param(value="pageSize") Integer page,@Param(value="rows") Integer rows,
				@Param(value="resourceUrl") String resourceUrl){
			logger.info("Start to getResourceByUrl"+",resourceUrl="+resourceUrl);
			Map<String, Object> params = new HashMap<String,Object>();
			params.put("resource_url",resourceUrl);
			int count = resourceService.findCount(null, params);
			logger.info("Resource Count:"+count);
			SimplePage simplepage = new SimplePage(page, rows, count);
			String orderBy = null;
			logger.info("page="+page+", rows="+rows);
			List<Resource> res = resourceService.findByPage(simplepage, params, orderBy);
			Map maps = new HashMap();
			maps.put("rows", res);
			maps.put("total", count);
			logger.info("End getResourceByUrl");
			return maps;
		}
}