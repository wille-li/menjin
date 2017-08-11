package com.base.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;

public class UserSessionUtil {

	private static Logger logger = LoggerFactory.getLogger(UserSessionUtil.class);
	
	public static String getCurrentUsername(HttpServletRequest request){
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
}
