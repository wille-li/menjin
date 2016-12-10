package com.menjin.visit.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.annotation.log.SystemControllerLog;
import com.base.entity.ReturnInfo;
import com.base.entity.SimplePage;
import com.menjin.company.model.Company;
import com.menjin.company.model.Department;
import com.menjin.company.model.Employee;
import com.menjin.visit.model.Matter;
import com.menjin.visit.model.VisitRecord;
import com.menjin.visit.model.Visitor;
import com.menjin.visit.service.VisitRecordService;
import com.menjin.visit.service.VisitorService;

@Controller
public class VisitRecordController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	VisitRecordService vistService;
	
	@Autowired
	VisitorService visitorService;
	
	private static String  BLACK_LIST = "3";
	private static String  WHITE_LIST = "2";
	private static Integer  VISIT_STATUS_NOT = 1;
	private static Integer  VISIT_STATUS_HAND = 2;
	private static Integer  VISIT_STATUS_LEAVE = 3;
	
    public final static int SUCCESS = 0;
	
	public final static int FAIL = 1;
	@RequestMapping(value="/vist.do")
	@SystemControllerLog
	public String testVistorController(){
		return "visit/visitrecordmanage";
	}
	
	
	@RequestMapping(value="/visitlist.do")
	@SystemControllerLog
	@ResponseBody
	public Map getVistorByPage(@Param(value="pageSize") Integer page,
			@Param(value="rows") Integer rows,@Param(value="status") String status,
			@Param(value="matterTxnNum") String matterTxnNum,@Param(value="startDate") String startDate,
			@Param(value="endDate") String endDate,@Param(value="IdCardNum") String IdCardNum,
			@Param(value="visitorName") String visitorName,@Param(value="employeeName") String employeeName,
			@Param(value="companyName") String companyName,@Param(value="validateMode") String validateMode){
		logger.info("Start to getVisitorByPage:vistorlist.do");
		
		Map<String, Object> params = toMap(status, matterTxnNum, startDate, endDate, 
				IdCardNum, visitorName, employeeName, companyName, validateMode);
	
		int count = vistService.findCount(null, params);
		logger.info("check visitRecord by params Count:"+count);
		SimplePage simplepage = new SimplePage(page, rows, count);
		String orderBy = "modified_date";
		List<VisitRecord> visits = vistService.findByPage(simplepage, params, orderBy);
		Map maps = new HashMap();
		maps.put("rows", visits);
		maps.put("total", count);
		logger.info("End getVisitorByPage:matterlist.do!");
		return maps;
	}
	
	@RequestMapping(value="/addVisit.do")
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> addVisitRecord(
			@Param(value = "visitorId") Integer visitorId,@Param(value = "companyId") Integer companyId,
			@Param(value = "employeePhone") String employeePhone,@Param(value = "employeeName") String employeeName,
			@Param(value = "matterId") Integer matterId,@Param(value = "peopleSum") Integer peopleSum,
			@Param(value = "visitTime") String visitTime,@Param(value = "validateMode")String validateMode,
			@Param(value = "status")String status,@Param(value = "leaveTime")String leaveTime,
			HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> returnMap = new HashMap<>();
		ReturnInfo rInfo = new ReturnInfo();
		logger.info("get Matter Txn Num Start!");
		String txnNum = getMatterTxnNum();
		if(txnNum == null){
			logger.info("get Matter Txn Num failed,MatterTxnNum:"+txnNum);
			rInfo.setMsg("获取拜访单号失败，请重试！");
			rInfo.setRet(FAIL);
			returnMap.put("rInfo", rInfo);
			return returnMap;
		}
		logger.info("get Matter Txn Num End!");
		Integer returnCode = 0;
		Visitor visitor = new Visitor();
		visitor.setId(visitorId);
		VisitRecord visit = new VisitRecord();
		visit.setMatterTxnNum(txnNum);
		Company company = new Company();
		company.setId(companyId);
		Matter matter = new Matter();
		matter.setId(matterId);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		try {
			visit.setActualTime(sdf.parse(visitTime));
			visit.setLeaveTime(sdf.parse(leaveTime));
			
		} catch (ParseException e) {
			rInfo.setMsg("时间输入有误，请检查后重试！");
			rInfo.setRet(FAIL);
			returnMap.put("rInfo", rInfo);
			e.printStackTrace();
			return returnMap;
		}
		visit.setCompany(company);
		/*visit.setDepartment(department);
		visit.setEmployee(employee);*/
		visit.setVisitor(visitor);
		visit.setMatter(matter);
		visit.setStatus(status);
		visit.setEmployeeName(employeeName);
		visit.setEmployeePhone(employeePhone);
		visit.setValidateMode(validateMode);
		visit.setPeopleSum(peopleSum);
		visit.setCreateBy("Admin");//根据现在操作用户修改
		visit.setModifiedDate(new Date());
		returnCode = vistService.add(visit);
		logger.info("End to Insert Visitor!ReturnCode:");
		if(returnCode > SUCCESS){
			rInfo.setMsg("拜访单数据添加成功!");
			rInfo.setRet(SUCCESS);
		}else{
			rInfo.setMsg("拜访单数据添加失败,请重试！");
			rInfo.setRet(FAIL);
		}
		returnMap.put("rInfo", rInfo);
		return returnMap;
	}
	
	@RequestMapping(value="/updateVisit.do")
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> updateVisitRecord(@Param(value = "id") Integer id,@Param(value = "companyId") Integer companyId,
			@Param(value = "employeePhone") String employeePhone,@Param(value = "employeeName") String employeeName,
			@Param(value = "matterId") Integer matterId,@Param(value = "peopleSum") Integer peopleSum,
			@Param(value = "visitTime") String visitTime,@Param(value = "validateMode")String validateMode,
			@Param(value = "status")String status,@Param(value = "leaveTime")String leaveTime,
			HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> returnMap = new HashMap<>();
		ReturnInfo rInfo = new ReturnInfo();
		logger.info("Start to update VisitRecord!");
		VisitRecord visitRecord = new VisitRecord();
		visitRecord.setId(id);
		visitRecord = vistService.findById(visitRecord);
		Company company = new Company();
		company.setId(companyId);
		visitRecord.setCompany(company);
		Matter matter = new Matter();
		matter.setId(matterId);
		visitRecord.setMatter(matter);
		visitRecord.setEmployeeName(employeeName);
		visitRecord.setEmployeePhone(employeePhone);
		visitRecord.setPeopleSum(peopleSum);
		visitRecord.setValidateMode(validateMode);
		visitRecord.setStatus(status);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		try {
			visitRecord.setActualTime(sdf.parse(visitTime));
			visitRecord.setLeaveTime(sdf.parse(leaveTime));
		} catch (ParseException e) {
			rInfo.setMsg("时间输入有误，请检查后重试！");
			rInfo.setRet(FAIL);
			returnMap.put("rInfo", rInfo);
			e.printStackTrace();
			return returnMap;
		}
		visitRecord.setModifiedDate(new Date());
		Integer returnCode = vistService.modifyById(visitRecord);
		
		if(returnCode > SUCCESS){
			rInfo.setMsg("拜访单数据修改成功!");
			rInfo.setRet(SUCCESS);
		}else{
			rInfo.setMsg("拜访单数据修改失败,请重试！");
			rInfo.setRet(FAIL);
		}
		returnMap.put("rInfo", rInfo);
		logger.info("End to update Visitor!ReturnCode:"+returnCode);
		return returnMap;
	}
	

	@RequestMapping(value="/deleteVisit.do")
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> delectMatter(@ModelAttribute VisitRecord visit,HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to delete Visitor!");
		Map<String, Object> returnMap = new HashMap<>();
		ReturnInfo rInfo = new ReturnInfo();
		int returnCode = vistService.deleteById(visit);
		if(returnCode > SUCCESS){
			rInfo.setMsg("拜访单数据删除成功!");
			rInfo.setRet(SUCCESS);
		}else{
			rInfo.setMsg("拜访单数据删除失败,请重试！");
			rInfo.setRet(FAIL);
		}
		returnMap.put("rInfo", rInfo);
		logger.info("End to delete Visitor。RetrunCode:"+returnCode);
		return returnMap;
	}
	
	public String getMatterTxnNum(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNamePre", "YH");
		map.put("num", "8");
		map.put("newOrderNo", "");
		vistService.getNewTxnNo(map);
		System.out.println("MatterTxnNum is : " + map.get("newOrderNo"));
		return (String) map.get("newOrderNo");
	}
	
	public Map<String, Object> toMap(String status,String matterTxnNum,String startDate,
			String endDate,String IdCardNum,String visitorName,String employeeName,
			String companyName,String validateMode){
		Map<String, Object> params = new HashMap<String, Object>();
		if(status != null && !status.equals("")){
			logger.info("status:"+status);
			params.put("status", status);
		}
		if(matterTxnNum != null && !matterTxnNum.equals("")){
			logger.info("matterTxnNum:"+matterTxnNum);
			params.put("matterTxnNum", matterTxnNum);
		}
		if(startDate!=null && !startDate.equals("")){
			logger.info("startDate:"+matterTxnNum);
			params.put("startDate", startDate);
		}
		if(endDate!=null && !endDate.equals("")){
			logger.info("endDate:"+endDate);
			params.put("endDate", endDate);
		}
		if(IdCardNum != null && ! IdCardNum.equals("")){
			logger.info("IdCardNum:"+IdCardNum);
			params.put("IdCardNum", IdCardNum);
		}
		if(employeeName != null && ! employeeName.equals("")){
			logger.info("employeeName:"+employeeName);
			params.put("employeeName", employeeName);
		}
		if(visitorName != null && !visitorName.equals("")){
			logger.info("visitorName:"+visitorName);
			params.put("visitorName", visitorName);
		}
		if(companyName != null && !companyName.equals("")){
			logger.info("companyName:"+companyName);
			params.put("companyName", companyName);
		}
		if(validateMode != null && ! validateMode.equals("")){
			logger.info("validateMode:"+validateMode);
			params.put("validateMode", validateMode);
		}
		return params;
	}

}
