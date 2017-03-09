package com.menjin.user.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.annotation.log.SystemControllerLog;
import com.base.entity.SimplePage;
import com.menjin.user.model.Role;
import com.menjin.user.model.User;
import com.menjin.user.model.UserRoles;
import com.menjin.user.service.If.RoleServiceIf;
import com.menjin.user.service.If.UserServiceIf;

@Controller
public class UserController {

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
	public Map getUserByPage(HttpServletRequest request){
		logger.info("Start to getUserByPage");
		Integer page = Integer.parseInt(request.getParameter("start"));
        System.out.println(page);
        Integer rows = Integer.parseInt(request.getParameter("length"));
        System.out.println(rows);
        String draw = request.getParameter("draw") == null ? "0" : request.getParameter("draw") + 1;
		int count = userService.findCount(null, null);
		logger.info("Users Count:"+count);
		SimplePage simplepage = new SimplePage(page/rows+1, rows, count);
		Map<String, Object> params = null;
		String orderBy = null;
		logger.info("page="+page+", rows="+rows);
		List<User> users = userService.findByPage(simplepage, params, orderBy);
		for(User u:users){
			Set<Role> roles = roleService.findRoleByUserId(u.getId());
			u.setRoles(roles);
		}
		Map maps = new HashMap();
		maps.put("data", users);
		maps.put("draw", draw);
		maps.put("recordsTotal", count);
		maps.put("recordsFiltered", count);
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
			@Param(value="description") String description){
		logger.info("Start to getUserByRolename"+",description="+description);
		int count = userService.findCountByRolename(description);
		logger.info("Users Count:"+count);
		SimplePage simplepage = new SimplePage(page, rows, count);
		String orderBy = null;
		logger.info("page="+page+", rows="+rows);
		List<User> users = userService.findUserByRolename(description, simplepage, orderBy);
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
	
	@RequestMapping(value="/user/resetPassword.do", method = RequestMethod.POST)
	@SystemControllerLog
	@ResponseBody
	public Integer resetPassword(String oldPassword, String newPassword,
			HttpServletRequest request){
		String currentLoginUser = getCurrentUsername(request);
		User temUser = userService.findByUsername(currentLoginUser);
		int returnCode = - 1;
		if (null != temUser && temUser.getPassword().equals(oldPassword)){
			temUser.setPassword(newPassword);
			returnCode = userService.resetPassword(temUser);
		}
		logger.info("retrunCode:"+returnCode);
		return returnCode;
	}
	
	@RequestMapping(value="/user/searchRole.do", method = RequestMethod.POST)
	@SystemControllerLog
	@ResponseBody
	public Map<String,Object> searchRole(String username ,HttpServletResponse resp){
		logger.info("Start to searchRole");
		Map<String,Object> map = new HashMap<String,Object>();
		Set<Role> existSet = roleService.findRoleByUsername(username);
		Set<Role> notExistSet = roleService.findNoRoleByUsername(username);
		map.put("existRoles", existSet);
		map.put("notExistRoles", notExistSet);
		return map;
	}
	
	@RequestMapping(value="/user/addUserRoles.do", method = RequestMethod.POST)
	@SystemControllerLog
	@ResponseBody
	public void addUserRoles(String userId,String roleIds,HttpServletResponse resp){
		logger.info("Start to addUserRoles,userId="+userId+" ,roleIds="+roleIds);
		int uid = Integer.parseInt(userId);
		int deleteNum = 0;
		deleteNum = roleService.deleteByUserId(uid);
		logger.info("deleteNum="+deleteNum);
		int count = 0;
		if(!"".equals(roleIds) && roleIds!=null){
			String[] strArr = roleIds.trim().split(",");
			int rid = 0;
			int addNum = -1;
			UserRoles userRoles = new UserRoles();
			for(String roleId : strArr){
				rid = Integer.parseInt(roleId);
				userRoles.setUserId(uid);
				userRoles.setRoleId(rid);
				addNum = roleService.addUserRoles(userRoles);
				if(addNum==1){
					count++;
				}
			}
		}
		String status = "failed";
		logger.info("insert to t_user_role count="+count);
		if(deleteNum==0 && count==0){
			
		}else{
			status = "success";
		}
		String jsonStr = "{\"result\":\""+status+"\"}";
		logger.info("return jsonStr="+jsonStr);
		resp.setContentType("application/json; charset=utf-8");
		try {
			resp.getWriter().write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("end addUserRoles");
	}
}
