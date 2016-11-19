package com.menjin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.annotation.log.SystemControllerLog;
import com.menjin.service.TestService;

@Controller
public class TestController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	TestService testService;
	
	/**
	 * 对controller方法切入log内容
	 * @return
	 */
	@RequestMapping(value="/test.do")
	@SystemControllerLog
	public String testController(){
		logger.info("test:" + testService.findCount(null, null));
		return "test";
	}

}
