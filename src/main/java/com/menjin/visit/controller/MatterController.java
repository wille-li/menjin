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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.annotation.log.SystemControllerLog;
import com.base.entity.SimplePage;
import com.menjin.visit.model.Matter;
import com.menjin.visit.service.MatterService;

@Controller
public class MatterController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	MatterService matterService;
	
	
	
	@RequestMapping(value="/matter.do")
	@SystemControllerLog
	public String testMatterController(){
		return "visit/mattermanage";
	}
	
	
	@RequestMapping(value="/matterlist.do")
	@SystemControllerLog
	@ResponseBody
	public Map getMatterByPage(@Param(value="pageSize") Integer page,@Param(value="rows") Integer rows){
		logger.info("Start to getMatterByPage:matterlist.do");
		int count = matterService.findCount(null, null);
		logger.info("Matters Count:"+count);
		SimplePage simplepage = new SimplePage(page, rows, count);
		Map<String, Object> params = null;
		String orderBy = null;
		List<Matter> matters = matterService.findByPage(simplepage, params, orderBy);
		Map maps = new HashMap();
		maps.put("rows", matters);
		maps.put("total", count);
		logger.info("End getMatterByPage:matterlist.do!");
		return maps;
	}
	
	@RequestMapping(value="/matterlistForCombox.do")
	@SystemControllerLog
	@ResponseBody
	public List<Matter> getMatterByPageForCombox(){
		logger.info("Start to getMatterByPage:matterlist.do");
		int count = matterService.findCount(null, null);
		logger.info("Matters Count:"+count);
		SimplePage simplepage = new SimplePage(1, count, count);
		Map<String, Object> params = null;
		String orderBy = null;
		List<Matter> matters = matterService.findByPage(simplepage, params, orderBy);
		return matters;
	}
	
	@RequestMapping(value="/addMatter.do")
	@SystemControllerLog
	@ResponseBody
	
	public Integer addMatter(@ModelAttribute Matter matter,
			HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to insert new Matter!Matter Name:"+matter.getMatterDecs());
		matter.setCreateBy("Admin");//根据现在操作用户修改
		matter.setCreateTime(new Date());
		matter.setModifiedDate(new Date());
		int returnCode = matterService.add(matter);
		logger.info("End to Insert Company!ReturnCode:"+returnCode);
		return returnCode;
	}
	
	@RequestMapping(value="/updateMatter.do")
	@SystemControllerLog
	@ResponseBody
	public Integer updateMatter(@ModelAttribute Matter matter,HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to update Matter!");
		matter.setModifiedDate(new Date());
		int returnCode = matterService.modifyById(matter);
		logger.info("End to update Matter!ReturnCode:"+returnCode);
		return returnCode;
	}

	@RequestMapping(value="/deleteMatter.do")
	@SystemControllerLog
	@ResponseBody
	public Integer delectMatter(@ModelAttribute Matter matter,HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to delete Matter!");
		int returnCode = matterService.deleteById(matter);
		logger.info("End to delete Matter。RetrunCode:"+returnCode);
		return returnCode;
	}

}
