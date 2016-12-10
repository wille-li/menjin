package com.menjin.api.controller;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.base.annotation.log.SystemControllerLog;
import com.base.entity.ReturnInfo;
import com.base.entity.SimplePage;
import com.base.service.BaseService;
import com.menjin.api.model.APICompany;
import com.menjin.api.model.APIVisit;
import com.menjin.api.service.APICompanyService;
import com.menjin.api.service.APIDepartmentService;
import com.menjin.api.service.APIEmployeeService;
import com.menjin.photo.model.PhotoInfo;
import com.menjin.photo.service.PhotoInfoService;
import com.menjin.visit.model.Visitor;
import com.menjin.visit.service.VisitorService;

@Controller
@Scope("prototype")
public class APIController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	public static Integer companyVersion = 0;
	
	public final static String HEAD_KEY = "head";
	
	public final static String DATA_KEY = "data";
	
	public final static String VISITOR_KEY = "visitor";
	
	public final static int SUCCESS = 0;
	
	public final static int FAIL = 1;
	
	public final static int VISITOR_EXISTING = 2;
	
	public final static String VERSION_KEY = "version";
	
	public static Integer versionNum = 0;
	
	@Value("${photo.path}")
	private String imagePath;
	
	@Resource
	private PhotoInfoService photoInfoService;
	
	@Autowired
	APICompanyService aPICompanyService;
	
	@Autowired
	APIDepartmentService aPIDepartmentService;
	
	@Autowired
	APIEmployeeService aPIEmployeeService;
	
	@Resource
	VisitorService visitorService;
	
	@RequestMapping(value="/api/getCompany.do", method=RequestMethod.GET)
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> getCompanyInfo(Integer version){
		Map<String, Object> returnMap = new HashMap<>();
		Map<String, Object> dataMap = new HashMap<>();
		ReturnInfo rInfo = new ReturnInfo();
		List<APICompany> company  = (List<APICompany>) findAllInfo(aPICompanyService);
		dataMap.put("company", company);
		dataMap.put(VERSION_KEY, "1");
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
		Map<String, Object> returnMap = new HashMap<>();
		ReturnInfo rInfo = new ReturnInfo();
		String idCardNum = visit.getIdCardNum();
		Visitor visitor = visitorService.selectByIDCar(idCardNum);
		if (null == visitor){
			rInfo.setRet(FAIL);
			rInfo.setMsg("身份证未做识别验证");
			returnMap.put(HEAD_KEY, rInfo);
			return returnMap;
		} 
		String phone = visitor.getMobile();
		if (null == phone){
			visitor.setMobile(visit.getPhoneNum());
		}
		rInfo.setMsg("预约成功");
		rInfo.setRet(SUCCESS);
		returnMap.put(HEAD_KEY, rInfo);
		returnMap.put("validateCode", 666333);
		return returnMap;
	}
	
	@RequestMapping(value="/api/checkIDCard.do", method=RequestMethod.GET)
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> checkIDCard(@RequestParam("idCardNum") String idCardNum){
		Map<String, Object> returnMap = new HashMap<>();
		Visitor visitor = visitorService.selectByIDCar(idCardNum);
		ReturnInfo rInfo = new ReturnInfo();
		if (null == visitor){
			rInfo.setRet(FAIL);
			rInfo.setMsg("身份证未注册");
		} else {
			rInfo.setRet(VISITOR_EXISTING);
			rInfo.setMsg("身份证已注册");
			returnMap.put(VISITOR_KEY, visitor);
		}
		
		returnMap.put(HEAD_KEY, rInfo);
		return returnMap;
	}
	
	
	
	private void savePhotoInfo(String fileName, String filePath, Long size){
		PhotoInfo photoInfo = new PhotoInfo();
		photoInfo.setName(fileName);
		photoInfo.setPath(filePath);
		photoInfo.setSize(size);
		photoInfo.setCreateBy("Wille");
		photoInfo.setCreateDate(Calendar.getInstance().getTime());
		photoInfoService.add(photoInfo);
	}
	
	

}
