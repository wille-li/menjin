package com.menjin.user.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.annotation.log.SystemControllerLog;
import com.base.entity.SimplePage;
import com.menjin.user.model.Role;
import com.menjin.user.model.User;
import com.menjin.user.service.If.RoleServiceIf;
import com.menjin.user.service.If.UserServiceIf;

@Controller
public class UserControler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserServiceIf userService;
	
	@Autowired
	private RoleServiceIf roleService;
	
	private String getCurrentUsername(HttpServletRequest request){
		SecurityContext securityContextImpl = ((SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT"));
		if(securityContextImpl!=null){
			String name = securityContextImpl.getAuthentication().getName();
			logger.info("current username="+name);
			return name;
		}else{
			logger.info("securityContextImpl is null");
			return null;
		}
	}
	
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
	
	@RequestMapping(value="/user/addUser.do")
	@SystemControllerLog
	@ResponseBody
	public Integer addUser(@ModelAttribute User user,
			HttpServletRequest request){
		logger.info("Start to add new user,username="+user.getUsername());
		String currentLoginUser = getCurrentUsername(request);
		logger.info("当前登录用户为："+currentLoginUser+", 操作：新增用户");
		Date date = Calendar.getInstance().getTime();
		user.setCreatedBy(currentLoginUser);
		user.setModifiedBy(currentLoginUser);
		user.setModifiedDate(date);
		user.setCreatedDate(date);
		int returnCode = userService.add(user);
		logger.info("End to add user,ReturnCode="+returnCode);
		return returnCode;
	}
	
	@RequestMapping(value="/user/updateUser.do")
	@SystemControllerLog
	@ResponseBody
	public Integer updateUser(@ModelAttribute User user,HttpServletRequest request){
		logger.info("Start to update user!");
		String currentLoginUser = getCurrentUsername(request);
		logger.info("当前登录用户为："+currentLoginUser+", 操作：修改用户");
		user.setModifiedDate(Calendar.getInstance().getTime());
		user.setModifiedBy(currentLoginUser);
		int returnCode = userService.modifyById(user);
		logger.info("End to update user!ReturnCode:"+returnCode);
		return returnCode;
	}
	
	@RequestMapping(value="/user/deleteUser.do")
	@SystemControllerLog
	@ResponseBody
	public Integer deleteUser(@ModelAttribute User user,HttpServletRequest request){
		logger.info("Start to delete user,username="+user.getUsername());
		int returnCode = userService.deleteById(user);
		logger.info("End to delete user,retrunCode:"+returnCode);
		return returnCode;
	}
	
	
}
