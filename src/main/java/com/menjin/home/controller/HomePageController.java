package com.menjin.home.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.annotation.log.SystemControllerLog;

@Controller
public class HomePageController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value={"/index.do"})
	@SystemControllerLog
	public String testController(){
		logger.info("进入首页");
		return "index";
	}
}
