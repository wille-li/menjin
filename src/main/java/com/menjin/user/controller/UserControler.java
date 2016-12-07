package com.menjin.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.annotation.log.SystemControllerLog;
import com.base.entity.SimplePage;
import com.menjin.user.model.Role;
import com.menjin.user.model.User;
import com.menjin.user.service.If.RoleServiceIf;
import com.menjin.user.service.If.UserServiceIf;
import com.menjin.visit.model.Visitor;

@Controller
public class UserControler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserServiceIf userService;
	
	@Autowired
	private RoleServiceIf roleService;
	
	@RequestMapping(value="/user.do")
	@SystemControllerLog
	public String searchUser(){
		return "user/userManage";
	}
	
	@RequestMapping(value="/user/getAllUserList.do")
	@SystemControllerLog
	@ResponseBody
	public Map getUserByPage(@Param(value="pageSize") Integer page,
			@Param(value="rows") Integer rows){
		logger.info("Start to getUserByPage");
		int count = userService.findCount(null, null);
		logger.info("Users Count:"+count);
		SimplePage simplepage = new SimplePage(page, rows, count);
		Map<String, Object> params = null;
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
		logger.info("End getUserByPage");
		return maps;
	}
	
}
