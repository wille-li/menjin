package com.menjin.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.annotation.log.SystemControllerLog;
import com.base.entity.ReturnInfo;
import com.base.entity.SimplePage;
import com.menjin.user.model.Role;
import com.menjin.user.service.If.RoleServiceIf;

@Controller
public class RoleControler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public final static int SUCCESS = 0;
	
	public final static int FAIL = 1;
	
	@Autowired
	private RoleServiceIf roleService;
	
	@RequestMapping(value="/role.do")
	@SystemControllerLog
	public String searchUser(){
		return "user/roleManage";
	}
	
	@RequestMapping(value="/role/getAllRoleList.do")
	@SystemControllerLog
	@ResponseBody
	public Map getUserByPage(@Param(value="pageSize") Integer page,
			@Param(value="rows") Integer rows,@Param(value="checkmessage")String checkmessage){
		logger.info("Start to getRolesByPage");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("checkmessage", checkmessage);
		int count = roleService.findCount(null, params);
		logger.info("Users Count:"+count);
		SimplePage simplepage = new SimplePage(page, rows, count);
		String orderBy = null;
		logger.info("page="+page+", rows="+rows);
		List<Role> roles = roleService.findByPage(simplepage, params, orderBy);
		Map maps = new HashMap();
		maps.put("rows", roles);
		maps.put("total", count);
		logger.info("End getUserByPage");
		return maps;
	}
	
	
	@RequestMapping(value="/role/addRole.do")
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> addRole(@ModelAttribute Role role,
			HttpServletRequest request){
		logger.info("Start to add new role,username="+ role.getDescription());
		Map<String, Object> returnMap = new HashMap<>();
		ReturnInfo rInfo = new ReturnInfo();
		int returnCode = roleService.add(role);
		if(returnCode > SUCCESS){
			rInfo.setMsg("角色添加成功!");
			rInfo.setRet(SUCCESS);
		}else{
			rInfo.setMsg("角色添加失败,请重试！");
			rInfo.setRet(FAIL);
		}
		returnMap.put("rInfo", rInfo);
		logger.info("End to add user,ReturnCode="+returnCode);
		return returnMap;
	}
	
	@RequestMapping(value="/role/updateRole.do")
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> updateUser(@ModelAttribute Role role,HttpServletRequest request){
		logger.info("Start to update role!");
		Map<String, Object> returnMap = new HashMap<>();
		ReturnInfo rInfo = new ReturnInfo();
		int returnCode = roleService.modifyById(role);
		if(returnCode > SUCCESS){
			rInfo.setMsg("角色修改成功!");
			rInfo.setRet(SUCCESS);
		}else{
			rInfo.setMsg("角色修改失败,请重试！");
			rInfo.setRet(FAIL);
		}
		returnMap.put("rInfo", rInfo);
		logger.info("End to update user!ReturnCode:"+returnCode);
		return returnMap;
	}
	
	@RequestMapping(value="/role/deleteRole.do")
	@SystemControllerLog
	@ResponseBody
	public  Map<String, Object> deleteUser(@ModelAttribute Role role,HttpServletRequest request){
		logger.info("Start to delete Role");
		Map<String, Object> returnMap = new HashMap<>();
		ReturnInfo rInfo = new ReturnInfo();
		int returnCode = roleService.deleteById(role);
		if(returnCode > SUCCESS){
			rInfo.setMsg("角色删除成功!");
			rInfo.setRet(SUCCESS);
		}else{
			rInfo.setMsg("角色删除失败,请重试！");
			rInfo.setRet(FAIL);
		}
		returnMap.put("rInfo", rInfo);
		logger.info("End to delete user,retrunCode:"+returnCode);
		return returnMap;
	}
}
