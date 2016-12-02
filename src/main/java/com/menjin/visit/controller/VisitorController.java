package com.menjin.visit.controller;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.annotation.log.SystemControllerLog;
import com.base.entity.SimplePage;
import com.menjin.visit.model.Visitor;
import com.menjin.visit.service.VisitorService;

@Controller
public class VisitorController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
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
			@Param(value="rows") Integer rows,@Param(value="status") String status ){
		logger.info("Start to getVisitorByPage:vistorlist.do");
		int count = vistorService.findCount(null, null);
		logger.info("Matters Count:"+count);
		SimplePage simplepage = new SimplePage(page, rows, count);
		Map<String, Object> params = null;
		if(status != null && !status.equals("")){
			params = new HashMap<String, Object>();
			params.put("status", status);
		}
		String orderBy = null;
		List<Visitor> visitors = vistorService.findByPage(simplepage, params, orderBy);
		Map maps = new HashMap();
		maps.put("rows", visitors);
		maps.put("total", count);
		logger.info("End getVisitorByPage:matterlist.do!");
		return maps;
	}
	
	@RequestMapping(value="/addVisitor.do")
	@SystemControllerLog
	@ResponseBody
	public Integer addVisitor(@ModelAttribute Visitor visitor,
			HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to insert new Visitor!Visitor Name:"+visitor.getVisitorName());
		visitor.setCreateBy("Admin");//根据现在操作用户修改
		visitor.setModifiedDate(new Date());
		visitor.setCreatedTime(new Date());
		int returnCode = vistorService.add(visitor);
		logger.info("End to Insert Visitor!ReturnCode:"+returnCode);
		return returnCode;
	}
	
	@RequestMapping(value="/updateVisitor.do")
	@SystemControllerLog
	@ResponseBody
	public Integer updateVisitor(@ModelAttribute Visitor visitor,HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to update Visitor!");
		visitor.setModifiedDate(new Date());
		int returnCode = vistorService.modifyById(visitor);
		logger.info("End to update Visitor!ReturnCode:"+returnCode);
		return returnCode;
	}
	
	@RequestMapping(value="/updateVisitorRank.do")
	@SystemControllerLog
	@ResponseBody
	public Integer updateVisitorRank(@Param(value = "id") Integer id,@Param(value = "rank") String rank,Model model,HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to update Visitor Rank!");
		if(id == null || rank == null || rank.equals("")){
			logger.info("update visitor rank and id is null");
			return 0;
		}
		Visitor visitor = new Visitor();
		visitor.setId(id);
		visitor = vistorService.findById(visitor);
		visitor.setRank(rank);
		visitor.setModifiedDate(new Date());
		int returnCode = vistorService.modifyById(visitor);
		logger.info("End to update Visitor Rank!ReturnCode:"+returnCode);
		return returnCode;
	}

	@RequestMapping(value="/deleteVisitor.do")
	@SystemControllerLog
	@ResponseBody
	public Integer delectMatter(@ModelAttribute Visitor visitor,HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to delete Visitor!");
		int returnCode = vistorService.deleteById(visitor);
		logger.info("End to delete Visitor。RetrunCode:"+returnCode);
		return returnCode;
	}

}
