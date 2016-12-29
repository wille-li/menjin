package com.menjin.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.annotation.log.SystemControllerLog;
import com.base.entity.JsonReturn;
import com.base.entity.ReturnInfo;
import com.base.entity.SimplePage;
import com.base.service.BaseService;
import com.base.util.JsonReturnUtil;
import com.menjin.api.model.APICompany;
import com.menjin.api.service.APICompanyService;

/**
 * 
 * @ClassName: APICompanyController 
 * @Description: 手机API控制器
 * @author Wille Li 
 * @date 2016年12月28日 下午10:20:29 
 *
 */
@Controller
public class APICompanyController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	public static Integer companyVersion = 0;
	
	public final static String HEAD_KEY = "head";
	
	public final static String DATA_KEY = "data";
	
	public final static int SUCCESS = 0;
	
	public final static int FAIL = 1;
	
	public final static int VISITOR_EXISTING = 2;
	
	public final static String VERSION_KEY = "version";
	
	public static Integer versionNum = 0;
	
	
	@Value("${photo.path}")
	private String imagePath;
	
	@Autowired
	APICompanyService aPICompanyService;
	
	@RequestMapping(value="/api/getCompany1.do", method = RequestMethod.GET)
	@SystemControllerLog
	@ResponseBody
	public JsonReturn<APICompany> getCompanyInfo1(Integer version){
		List<APICompany> company  = (List<APICompany>) findAllInfo(aPICompanyService);
		return JsonReturnUtil.buildReturn(SUCCESS, "获取成功", company);
	}
	
	@RequestMapping(value="/api/getCompany.do", method = RequestMethod.GET)
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> getCompanyInfo(Integer version){
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		ReturnInfo rInfo = new ReturnInfo();
		List<APICompany> company  = (List<APICompany>) findAllInfo(aPICompanyService);
		dataMap.put("company", company);
		dataMap.put(VERSION_KEY, versionNum);
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
	
	
}
