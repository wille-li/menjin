package com.menjin.visit.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.menjin.visit.model.Visitor;
import com.menjin.visit.service.VisitorService;

@Controller
public class VisitorController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public final static int SUCCESS = 0;
	
	public final static int FAIL = 1;
	
	@Autowired
	VisitorService vistorService;
	
	
	
	@RequestMapping(value="/vistor.do")
	@SystemControllerLog
	public String testVistorController(){
		return "visit/visitormanage";
	}
	
	
	@RequestMapping(value="/visitorlist.do")
	@SystemControllerLog
	@ResponseBody
	public Map getVistorByPage(@Param(value="pageSize") Integer page,
			@Param(value="rows") Integer rows,@Param(value="status") String status,@Param(value="visitorName") String visitorName  ){
		logger.info("Start to getVisitorByPage:vistorlist.do");
		Map<String, Object> params = new HashMap<String, Object>();;
		if(status != null && !status.equals("")){
			params.put("status", status);
		}
		if(visitorName != null && !visitorName.equals("")){
			params.put("visitorName", visitorName);
		}
		int count = vistorService.findCount(null, params);
		logger.info("Matters Count:"+count);
		SimplePage simplepage = new SimplePage(page, rows, count);
		
		String orderBy = "modified_date";
		List<Visitor> visitors = vistorService.findByPage(simplepage, params, orderBy);
		Map maps = new HashMap();
		maps.put("rows", visitors);
		maps.put("total", count);
		logger.info("End getVisitorByPage:matterlist.do!");
		return maps;
	}
	
	@RequestMapping(value="/visitorlistforcombox.do")
	@SystemControllerLog
	@ResponseBody
	public List<Visitor> getVistorForCombox(@Param(value="pageSize") Integer page,
			@Param(value="rows") Integer rows,@Param(value="status") String status ){
		logger.info("Start to getVisitorByPage:vistorlist.do");
		int count = vistorService.findCount(null, null);
		logger.info("Matters Count:"+count);
		SimplePage simplepage = new SimplePage(0, count, count);
		Map<String, Object> params = null;
		if(status != null && !status.equals("")){
			params = new HashMap<String, Object>();
			params.put("status", status);
		}
		String orderBy = null;
		List<Visitor> visitors = vistorService.findByPage(simplepage, params, orderBy);
		logger.info("End getVisitorByPage:matterlist.do!");
		return visitors;
	}
	
	@RequestMapping(value="/addVisitor.do")
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> addVisitor(/*@ModelAttribute Visitor visitor,*/
			@Param(value = "visitorName") String visitorName,@Param(value = "idCardType") String idCardType,
			@Param(value = "idCardNum") String idCardNum,@Param(value = "sex") String sex,
			@Param(value = "nation") String nation,@Param(value = "birth") String birth,
			@Param(value = "address") String address,@Param(value = "mobile") String mobile,
			@Param(value = "rank") String rank,@Param(value = "email") String email,
			HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to insert new Visitor!Visitor Name:"+visitorName);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		ReturnInfo rInfo = new ReturnInfo();
		Visitor visitor = new Visitor();
		visitor.setVisitorName(visitorName);
		visitor.setIdCardType(idCardType);
		visitor.setIdCardNum(idCardNum);
		visitor.setSex(sex);
		visitor.setNation(nation);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		try {
			visitor.setBirth(sdf.parse(birth));
			
		} catch (ParseException e) {
			rInfo.setMsg("时间输入有误，请检查后重试！");
			rInfo.setRet(FAIL);
			returnMap.put("rInfo", rInfo);
			e.printStackTrace();
			return returnMap;
		}
		visitor.setMobile(mobile);
		visitor.setRank(rank);
		visitor.setAddress(address);
		visitor.setEmail(email);
		
		visitor.setCreateBy("Admin");//根据现在操作用户修改
		visitor.setModifiedDate(new Date());
		visitor.setCreatedTime(new Date());
		int returnCode = vistorService.add(visitor);
		if(returnCode > SUCCESS){
			rInfo.setMsg("拜访者数据添加成功!");
			rInfo.setRet(SUCCESS);
		}else{
			rInfo.setMsg("拜访者数据添加失败,请重试！");
			rInfo.setRet(FAIL);
		}
		returnMap.put("rInfo", rInfo);
		logger.info("End to Insert Visitor!ReturnCode:"+returnCode);
		return returnMap;
	}
	
	@RequestMapping(value="/updateVisitor.do")
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> updateVisitor(@Param(value = "id") Integer id,
			@Param(value = "visitorName") String visitorName,@Param(value = "idCardType") String idCardType,
			@Param(value = "idCardNum") String idCardNum,@Param(value = "sex") String sex,
			@Param(value = "nation") String nation,@Param(value = "birth") String birth,
			@Param(value = "address") String address,@Param(value = "mobile") String mobile,
			@Param(value = "rank") String rank,@Param(value = "email") String email,
			HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to update Visitor!");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		ReturnInfo rInfo = new ReturnInfo();
		Visitor visitor = new Visitor();
		visitor.setId(id);
		visitor.setVisitorName(visitorName);
		visitor.setIdCardType(idCardType);
		visitor.setIdCardNum(idCardNum);
		visitor.setSex(sex);
		visitor.setNation(nation);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			visitor.setBirth(sdf.parse(birth));
			
		} catch (ParseException e) {
			rInfo.setMsg("时间输入有误，请检查后重试！");
			rInfo.setRet(FAIL);
			returnMap.put("rInfo", rInfo);
			e.printStackTrace();
			return returnMap;
		}
		visitor.setMobile(mobile);
		visitor.setRank(rank);
		visitor.setAddress(address);
		visitor.setEmail(email);
		visitor.setModifiedDate(new Date());
		int returnCode = vistorService.modifyById(visitor);
		if(returnCode > SUCCESS){
			rInfo.setMsg("拜访者数据修改成功!");
			rInfo.setRet(SUCCESS);
		}else{
			rInfo.setMsg("拜访者数据修改失败,请重试！");
			rInfo.setRet(FAIL);
		}
		returnMap.put("rInfo", rInfo);
		logger.info("End to update Visitor!ReturnCode:"+returnCode);
		return returnMap;
	}
	
	@RequestMapping(value="/updateVisitorRank.do")
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> updateVisitorRank(@Param(value = "id") Integer id,@Param(value = "rank") String rank,HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to update Visitor Rank!");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		ReturnInfo rInfo = new ReturnInfo();
		if(id == null || rank == null || rank.equals("")){
			logger.info("update visitor rank and id is null");
			rInfo.setMsg("系统错误！请找管理员确认！");
			rInfo.setRet(FAIL);
			returnMap.put("rInfo", rInfo);
			return returnMap;
		}
		Visitor visitor = new Visitor();
		visitor.setId(id);
		visitor = vistorService.findById(visitor);
		visitor.setRank(rank);
		visitor.setModifiedDate(new Date());
		int returnCode = vistorService.modifyById(visitor);
		if(returnCode > SUCCESS){
			rInfo.setMsg("拜访者等级设置成功!");
			rInfo.setRet(SUCCESS);
		}else{
			rInfo.setMsg("拜访者等级设置失败,请重试！");
			rInfo.setRet(FAIL);
		}
		returnMap.put("rInfo", rInfo);
		logger.info("End to update Visitor Rank!ReturnCode:"+returnCode);
		return returnMap;
	}

	@RequestMapping(value="/deleteVisitor.do")
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> delectMatter(@Param(value = "id") Integer id,HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to delete Visitor!");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		ReturnInfo rInfo = new ReturnInfo();
		Visitor visitor = new Visitor();
		visitor.setId(id);
		int returnCode = vistorService.deleteById(visitor);
		logger.info("End to delete Visitor。RetrunCode:"+returnCode);
		if(returnCode > SUCCESS){
			rInfo.setMsg("拜访者数据删除成功!");
			rInfo.setRet(SUCCESS);
		}else{
			rInfo.setMsg("拜访者数据删除失败，请重试!");
			rInfo.setRet(FAIL);
		}
		returnMap.put("rInfo", rInfo);
		return returnMap;
	}

}
