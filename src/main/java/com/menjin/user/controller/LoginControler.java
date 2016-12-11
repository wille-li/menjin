package com.menjin.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.annotation.log.SystemControllerLog;

@Controller
public class LoginControler {
private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 对controller方法切入log内容
	 * @return
	 */
	@RequestMapping(value="/user/login.do")
	@SystemControllerLog
	public String loginCheck(String username ,String password){
		logger.info("username="+username+", password="+password);
		UserDetails u = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logger.info("username="+u.getUsername());
		return "index2";
	}
}
