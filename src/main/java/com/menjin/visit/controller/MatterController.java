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
import com.base.entity.ReturnInfo;
import com.base.entity.SimplePage;
import com.menjin.visit.model.Matter;
import com.menjin.visit.service.MatterService;

@Controller
public class MatterController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public final static int SUCCESS = 0;
		
	public final static int FAIL = 1;
	
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
	public Map getMatterByPage(@Param(value="pageSize") Integer page,@Param(value="rows") Integer rows,
			@Param(value="matterDecs") String matterDecs){
		logger.info("Start to getMatterByPage:matterlist.do");
		Map<String, Object> params = new HashMap<String, Object>();
		if(matterDecs != null && !matterDecs.equals("")){
			params.put("matterDecs", matterDecs);
		}
		int count = matterService.findCount(null, params);
		logger.info("Matters Count:"+count);
		SimplePage simplepage = new SimplePage(page, rows, count);
		String orderBy = "modified_date";
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
	public Map<String, Object> addMatter(@ModelAttribute Matter matter,
			HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to insert new Matter!Matter Name:"+matter.getMatterDecs());
		Map<String, Object> returnMap = new HashMap<String, Object>();
		ReturnInfo rInfo = new ReturnInfo();
		matter.setCreateBy("Admin");//根据现在操作用户修改
		matter.setCreateTime(new Date());
		matter.setModifiedDate(new Date());
		int returnCode = matterService.add(matter);
		if(returnCode > SUCCESS){
			rInfo.setMsg("拜访缘由添加成功!");
			rInfo.setRet(SUCCESS);
		}else{
			rInfo.setMsg("拜访缘由添加失败,请重试！");
			rInfo.setRet(FAIL);
		}
		returnMap.put("rInfo", rInfo);
		logger.info("End to Insert Company!ReturnCode:"+returnCode);
		return returnMap;
	}
	
	@RequestMapping(value="/updateMatter.do")
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> updateMatter(@ModelAttribute Matter matter,HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to update Matter!");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		ReturnInfo rInfo = new ReturnInfo();
		matter.setModifiedDate(new Date());
		int returnCode = matterService.modifyById(matter);
		if(returnCode > SUCCESS){
			rInfo.setMsg("拜访缘由修改成功!");
			rInfo.setRet(SUCCESS);
		}else{
			rInfo.setMsg("拜访缘由修改失败,请重试！");
			rInfo.setRet(FAIL);
		}
		returnMap.put("rInfo", rInfo);
		logger.info("End to update Matter!ReturnCode:"+returnCode);
		return returnMap;
	}

	@RequestMapping(value="/deleteMatter.do")
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> delectMatter(@ModelAttribute Matter matter,HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to delete Matter!");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		ReturnInfo rInfo = new ReturnInfo();
		int returnCode = matterService.deleteById(matter);
		if(returnCode > SUCCESS){
			rInfo.setMsg("拜访缘由删除成功!");
			rInfo.setRet(SUCCESS);
		}else{
			rInfo.setMsg("拜访缘由删除失败,请重试！");
			rInfo.setRet(FAIL);
		}
		returnMap.put("rInfo", rInfo);
		logger.info("End to delete Matter。RetrunCode:"+returnCode);
		return returnMap;
	}

}
