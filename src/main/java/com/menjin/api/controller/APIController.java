package com.menjin.api.controller;

import com.base.annotation.log.SystemControllerLog;
import com.base.entity.JsonReturn;
import com.base.entity.ReturnInfo;
import com.base.entity.SimplePage;
import com.menjin.api.model.APIAuth;
import com.menjin.api.model.APIMatter;
import com.menjin.api.service.APIAuthService;
import com.menjin.api.service.APICompanyService;
import com.menjin.api.service.APIDepartmentService;
import com.menjin.api.service.APIEmployeeService;
import com.menjin.company.model.Company;
import com.menjin.photo.model.PhotoInfo;
import com.menjin.photo.service.PhotoInfoService;
import com.menjin.visit.model.Matter;
import com.menjin.visit.model.Visitor;
import com.menjin.visit.service.MatterService;
import com.menjin.visit.service.VisitRecordService;
import com.menjin.visit.service.VisitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh24:mm");
	
	
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
	
	@Autowired
	VisitRecordService visitRecordService;
	
	@Autowired
	APIAuthService aPIAuthService;
	
	@Autowired	
	MatterService matterService;


	
	private String getIdCardPhtoPath(String idCardNum) {
		PhotoInfo info = photoInfoService.selectByIDCard(idCardNum);
		if (null == info) {
			logger.error("这个身份证找不到图片信息，请检查上传图片过程");
			return null;
		}
		String filePath = info.getPath() + info.getName();
		File picFile = new File(filePath);
		if (!picFile.exists()) {
			logger.error("这个身份证找不到身份证图片， 请检查文件是否已经删除");
			return null;
		}
		return filePath;
	}


	@RequestMapping(value="/api/checkIDCard.do", method=RequestMethod.GET)
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> checkIDCard(@RequestParam("idCardNum") String idCardNum){
		Map<String, Object> returnMap = new HashMap<String, Object>();
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
	
	@RequestMapping(value="/api/getMatter.do", method=RequestMethod.GET)
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> getMatter(){
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		int count = matterService.findCount(null, null);
		ReturnInfo rInfo = new ReturnInfo();
		logger.info("Count:"+count);
		SimplePage simplepage = new SimplePage(1, count, count);
		List<Matter> matters = matterService.findByPage(simplepage, null, null);
		List<APIMatter> result = new ArrayList<>();
		for (Matter matter : matters) {
			result.add(new APIMatter(matter));
		}
		dataMap.put("matter", result);
		dataMap.put(VERSION_KEY, versionNum);
		rInfo.setMsg("获取成功");
		rInfo.setRet(SUCCESS);
		returnMap.put(HEAD_KEY, rInfo);
		returnMap.put(DATA_KEY, dataMap);
		return returnMap;
	}
	
	
	
	private void savePhotoInfo(String fileName, String filePath, String idCardNum, Long size, int picType){
		PhotoInfo photoInfo = new PhotoInfo();
		photoInfo.setName(fileName);
		photoInfo.setPath(filePath);
		photoInfo.setSize(size);
		photoInfo.setCreateBy("Wille");
		photoInfo.setIdCardNum(idCardNum);
		photoInfo.setPicType(picType);
		photoInfo.setCreateDate(Calendar.getInstance().getTime());
		photoInfoService.add(photoInfo);
	}
	
	@RequestMapping(value="/api/upload.do", method=RequestMethod.POST)
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> uploadFileForRegister(@RequestParam("file") MultipartFile[] files, String idCardNum,
			String visitorName, String birth){
		Map <String, Object> result = new HashMap<String, Object>();
		ReturnInfo returnInfo = new ReturnInfo();
		
		if (idCardNum.indexOf("\"") > -1){
			idCardNum = idCardNum.replace("\"", "");
		}
		
		if (visitorName.indexOf("\"") > -1){
			visitorName = visitorName.replace("\"", "");
		}
		if (birth != null && birth.indexOf("\"") > -1){
			birth = birth.replace("\"", "");
		}
		if (idCardNum == null){
			
			returnInfo.setRet(APIController.FAIL);
			returnInfo.setMsg("身份证有误");
			result.put(APIController.HEAD_KEY, returnInfo);
	        return result;
		}
		
		Visitor tempVisitor = visitorService.selectByIDCar(idCardNum);
		Visitor visitor = new Visitor();
		
		try {
			if (birth != null) {
			Date date = new SimpleDateFormat("yyyy年MM月dd日").parse(birth);
			visitor.setBirth(date);
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		visitor.setVisitorName(visitorName);
		visitor.setIdCardNum(idCardNum);
		
		if (null == files){
			returnInfo.setRet(APIController.FAIL);
			returnInfo.setMsg("请上传一张身份证图片.");
			result.put(APIController.HEAD_KEY, returnInfo);
	        return result;
		}
		
		// 文件大小判断
		String file1 = "";
		String file2 = "";
		for (int i = 0; i < files.length; i++){
			MultipartFile file = files[i];
			String filename = idCardNum + Calendar.getInstance().getTimeInMillis() + ".jpg";
			
			File tmpFile=new File(imagePath + filename); 
			if (filename !=null && !file.isEmpty()){  
				try {  
					FileCopyUtils.copy(file.getBytes(), tmpFile); 
					savePhotoInfo(filename, imagePath, idCardNum, file.getBytes().length + 0L, i);
					returnInfo.setRet(APIController.SUCCESS);
					returnInfo.setMsg("上传图片成功。");
					if (i == 0){
						file1 = filename;
					} else {
						file2 = filename;
					}
				} catch (IOException e) {  
					returnInfo.setRet(APIController.FAIL);
					returnInfo.setMsg("上传图片失败。");
					logger.error("上传图片失败",  e);
				}  
			} else {
				returnInfo.setRet(APIController.FAIL);
				returnInfo.setMsg("没有收到上传图片，请上传图片。");
			}
			
		}
		boolean vertifyResult = true;
		if (!vertifyResult){
			returnInfo.setRet(APIController.FAIL);
			returnInfo.setMsg("身份认证失败。");
		} else {
			returnInfo.setMsg("身份认证成功。");
		}
		visitor.setIdCardType("身份证");
		visitor.setSex("男");
		visitor.setCreateBy("Admin");
		visitor.setCreatedTime(new Date());
		visitor.setRank("1");
		if (tempVisitor == null){
			int resultNum = visitorService.add(visitor);
		}
		
        result.put(APIController.HEAD_KEY, returnInfo);
        
        return result;
	}
	
	@RequestMapping(value="/api/return.do", method=RequestMethod.GET)
	@SystemControllerLog
	@ResponseBody
	public JsonReturn<Company> returnObj(){
		Company company = new Company();
		company.setCompanyName("我艹");
		JsonReturn<Company> returnObj = new JsonReturn<Company>(0, "测试", company);
		return returnObj;
	}
	
	@RequestMapping(value="/api/auth.do", method = RequestMethod.GET)
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> auth(APIAuth aPIAuth){
		Map<String, Object> returnMap = new HashMap<String, Object>();
		ReturnInfo rInfo = new ReturnInfo();
		if (aPIAuth == null){
			rInfo.setMsg("验证失败");
			rInfo.setRet(FAIL);
			returnMap.put(HEAD_KEY, rInfo);
			return returnMap;
		}
		Integer uid = -1;
		if (null != aPIAuth.getUsername() && !"".equals(aPIAuth.getUsername())){
			uid = aPIAuthService.authByUsernameAndPassword(aPIAuth);
			if (uid > 0){
				aPIAuth.setToken(Integer.toString(uid) 
						+ Calendar.getInstance().getTimeInMillis());
				aPIAuth.setUid(uid);
				aPIAuthService.deleteAuthInfo(aPIAuth);
				aPIAuthService.createAuthInfo(aPIAuth);
			}
		}
		if (null != aPIAuth.getToken()){
			uid = aPIAuthService.authByToken(aPIAuth);
			aPIAuth.setUid(uid);
		}
		
		if (uid != null && uid > 0) {
			rInfo.setMsg("验证成功");
			rInfo.setRet(SUCCESS);
			returnMap.put(HEAD_KEY, rInfo);
			returnMap.put("uid", uid);
			returnMap.put("token", aPIAuth.getToken());
		} else {
			rInfo.setMsg("验证失败");
			rInfo.setRet(FAIL);
			returnMap.put(HEAD_KEY, rInfo);
			return returnMap;
		}
		return returnMap;
	}
	

	
	
	
	public String getMatterTxnNum(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNamePre", "YH");
		map.put("num", "8");
		map.put("newOrderNo", "");
		visitRecordService.getNewTxnNo(map);
		System.out.println("MatterTxnNum is : " + map.get("newOrderNo"));
		return (String) map.get("newOrderNo");
	}
	
	
	public String removeStr(String value){
		return value.replace("\"", "");
	}
}
