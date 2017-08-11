package com.menjin.log.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.annotation.log.SystemControllerLog;
import com.base.entity.LogInfo;
import com.base.entity.SimplePage;
import com.base.service.LogInfoService;

@Controller
public class LogController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	LogInfoService logInfoService;
	
	@RequestMapping(value="/log.do")
	@SystemControllerLog
	public String openerManage(){
		return "log/logManage";
	}
	
	@RequestMapping(value="/logList.do")
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> getLogList(@Param(value="searchName")String searchName, HttpServletRequest request){
		logger.info("Start to getLogList");
		Integer page = Integer.parseInt(request.getParameter("start"));
        System.out.println(page);
        Integer rows = Integer.parseInt(request.getParameter("length"));
        System.out.println(rows);
        String draw = request.getParameter("draw") == null ? "0" : request.getParameter("draw") + 1;
		int count = logInfoService.findCount(null, null);
		logger.info("Log Count:"+count);
		SimplePage simplepage = new SimplePage(page/rows+1, rows, count);
		Map<String, Object> params = new HashMap<String, Object>();
		if(searchName != null && !searchName.equals("")){
			params.put("searchName", searchName);
		}
		String orderBy = "create_date desc";
		logger.info("page="+page+", rows="+rows);
		List<LogInfo> logInfo = logInfoService.findByPage(simplepage, params, orderBy);
		Map<String, Object> maps = new HashMap<>();
		maps.put("data", logInfo);
		maps.put("draw", draw);
		maps.put("recordsTotal", count);
		maps.put("recordsFiltered", count);
		logger.info("End getLogList");
		return maps;
	}
	
}
