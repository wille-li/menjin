package com.menjin.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.annotation.log.SystemControllerLog;
import com.base.entity.ReturnInfo;
import com.base.entity.SimplePage;
import com.base.service.BaseService;
import com.menjin.api.model.APICompany;
import com.menjin.api.model.APIDepartment;
import com.menjin.api.model.APIEmployee;
import com.menjin.api.model.APIVisit;
import com.menjin.api.service.APICompanyService;
import com.menjin.api.service.APIDepartmentService;
import com.menjin.api.service.APIEmployeeService;

@Controller
public class APIController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	public static Integer companyVersion = 0;
	
	public final static String HEAD_KEY = "head";
	
	public final static String DATA_KEY = "data";
	
	public final static int SUCCESS = 0;
	
	public final static int FAIL = 1;
	
	@Autowired
	APICompanyService aPICompanyService;
	
	@Autowired
	APIDepartmentService aPIDepartmentService;
	
	@Autowired
	APIEmployeeService aPIEmployeeService;
	
	@RequestMapping(value="/api/getCompany.do", method=RequestMethod.GET)
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> getCompanyInfo(Integer version){
		Map<String, Object> returnMap = new HashMap<>();
		Map<String, List> dataMap = new HashMap<>();
		ReturnInfo rInfo = new ReturnInfo();
		List<APICompany> company  = (List<APICompany>) findAllInfo(aPICompanyService);
		List<APIDepartment> departmentList = (List<APIDepartment>) findAllInfo(aPIDepartmentService);
		List<APIEmployee> employeeList = (List<APIEmployee>) findAllInfo(aPIEmployeeService);
		dataMap.put("company", company);
		dataMap.put("department", departmentList);
		dataMap.put("employee", employeeList);
		rInfo.setMsg("获取成功");
		rInfo.setRet(SUCCESS);
		returnMap.put(HEAD_KEY, rInfo);
		returnMap.put(DATA_KEY, dataMap);
		return returnMap;
	}
	
	private List<?> findAllInfo(BaseService<?> baseService){
		int count = baseService.findCount(null, null);
		logger.info("Count:"+count);
		SimplePage simplepage = new SimplePage(1, count, count);
		return baseService.findByPage(simplepage, null, null);
	}
	
	@RequestMapping(value="/api/visit.do", method=RequestMethod.POST)
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> visit(@ModelAttribute APIVisit visit){
		logger.info(visit.getEmployeeID() + "");
		Map<String, Object> returnMap = new HashMap<>();
		ReturnInfo rInfo = new ReturnInfo();
		rInfo.setMsg("获取成功");
		rInfo.setRet(SUCCESS);
		returnMap.put(HEAD_KEY, rInfo);
		returnMap.put("validateCode", 666333);
		return returnMap;
	}
}
