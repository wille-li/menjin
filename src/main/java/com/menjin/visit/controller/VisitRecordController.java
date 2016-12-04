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
	@RequestMapping(value="/vist.do")
	@SystemControllerLog
	public String testVistorController(){
		return "visit/visitrecordmanage";
	}
	
	
	@RequestMapping(value="/visitlist.do")
	@SystemControllerLog
	@ResponseBody
	public Map getVistorByPage(@Param(value="pageSize") Integer page,
			@Param(value="rows") Integer rows,@Param(value="status") String status ){
		logger.info("Start to getVisitorByPage:vistorlist.do");
		int count = vistService.findCount(null, null);
		logger.info("Matters Count:"+count);
		SimplePage simplepage = new SimplePage(page, rows, count);
		Map<String, Object> params = null;
		if(status != null && !status.equals("")){
			params = new HashMap<String, Object>();
			params.put("status", status);
		}
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
	public Integer addVisitor(/*@ModelAttribute Visit visit,@ModelAttribute Visitor visitor,*/
			@Param(value = "visitorName") String visitorName,@Param(value = "idCardType") String idCardType,
			@Param(value = "idCardNum") String idCardNum,@Param(value = "sex") String sex,
			@Param(value = "mobile") String mobile,@Param(value = "companyId") Integer companyId,
			@Param(value = "departmentId") Integer departmentId,@Param(value = "employeeId") Integer employeeId,
			@Param(value = "matterId") Integer matterId,@Param(value = "peopleSum") String peopleSum,
			@Param(value = "visitTime") String visitTime,
			HttpServletRequest request,HttpServletResponse response){
		Visitor visitor = visitorService.selectByIDCar(idCardNum);
		Integer returnCode = 0;
		if(visitor != null){
			logger.info("IDCard No has been save in database!");
			if(visitor.getRank().equals(BLACK_LIST)){
				logger.info("Visitor Name:"+visitorName+" rank is black。");
				return 3;
			}else if(visitor.getRank().equals(WHITE_LIST)){
				logger.info("Visitor Name:"+visitorName+" rank is white。");
				return 2;
			}else{
				logger.info("Visitor Name:"+visitorName+" rank is ordinary。");
			}
		}else{
			visitor = new Visitor();
			visitor.setIdCardType(idCardType);
			visitor.setIdCardNum(idCardNum);
			visitor.setVisitorName(visitorName);
			visitor.setSex(sex);
			visitor.setMobile(mobile);
			visitor.setRank("1");//设置为一般的普通用户
			visitor.setCreateBy("Admin");
			visitor.setCreatedTime(new Date());
			visitor.setModifiedDate(new Date());
			Integer svReturnCode = visitorService.add(visitor);
			if(svReturnCode <= 0){
				return returnCode;
			}
		}
		VisitRecord visit = new VisitRecord();
		visit.setPeopleSum(Integer.parseInt(peopleSum));
		Company company = new Company();
		company.setId(companyId);
		Department department = new Department();
		department.setId(departmentId);
		Employee employee = new Employee();
		employee.setId(employeeId);
		Matter matter = new Matter();
		matter.setId(matterId);
		Visitor newvisitor = visitorService.selectByIDCar(idCardNum);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date appoitmentTime = sdf.parse(visitTime);
			visit.setAppointmentTime(appoitmentTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//此处需要修改。由DB生成流水账号
		visit.setMatterTxnNum(UUID.randomUUID().toString());
		visit.setCompany(company);
		visit.setDepartment(department);
		visit.setEmployee(employee);
		visit.setVisitor(newvisitor);
		visit.setMatter(matter);
		visit.setStatus(VISIT_STATUS_NOT.toString());
		visit.setCreateBy("Admin");//根据现在操作用户修改
		visit.setModifiedDate(new Date());
		returnCode = vistService.add(visit);
		logger.info("End to Insert Visitor!ReturnCode:");
		return returnCode;
	}
	
	@RequestMapping(value="/updateVisit.do")
	@SystemControllerLog
	@ResponseBody
	public Integer updateVisit(@ModelAttribute VisitRecord visit,HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to update Visitor!");
		visit.setModifiedDate(new Date());
		int returnCode = vistService.modifyById(visit);
		logger.info("End to update Visitor!ReturnCode:"+returnCode);
		return returnCode;
	}
	
	@RequestMapping(value="/updateVisitStatus.do")
	@SystemControllerLog
	@ResponseBody
	public Integer updateVisitStatus(@Param(value = "status") Integer status,@Param(value = "id") Integer id){
	    VisitRecord visit = new VisitRecord();
	    Integer returnCode = 0;
	    visit.setId(id);
	    visit = vistService.findById(visit);
		if(status != null && status == 2){
			//访客到来，更改状态为正在访问
			visit.setStatus(VISIT_STATUS_HAND.toString());
			visit.setActualTime(new Date());
			visit.setModifiedDate(new Date());
			returnCode = vistService.modifyById(visit);
		}else if(status != null && status == 3){
			visit.setStatus(VISIT_STATUS_LEAVE.toString());
			visit.setLeaveTime(new Date());
			visit.setModifiedDate(new Date());
			returnCode = vistService.modifyById(visit);
		}
		return returnCode;
		
	}
	

	@RequestMapping(value="/deleteVisit.do")
	@SystemControllerLog
	@ResponseBody
	public Integer delectMatter(@ModelAttribute VisitRecord visit,HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to delete Visitor!");
		int returnCode = vistService.deleteById(visit);
		logger.info("End to delete Visitor。RetrunCode:"+returnCode);
		return returnCode;
	}

}
