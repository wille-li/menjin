package com.menjin.company.controller;

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
import com.menjin.company.model.Company;
import com.menjin.company.service.CompanyService;

@Controller
public class CompanyController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	CompanyService companyService;
	
    public final static int SUCCESS = 0;
	
	public final static int FAIL = 1;
	
	
	
	@RequestMapping(value="/company.do")
	@SystemControllerLog
	public String testCompanyController(){
		return "company/companymanage";
	}
	
	
	@RequestMapping(value="/companylist.do")
	@SystemControllerLog
	@ResponseBody
	public Map getCompanyByPage(@Param(value="companyName")String companyName,HttpServletRequest request){
		logger.info("Start to getCompanyByPage:companylist.do");
		Integer page = Integer.parseInt(request.getParameter("start"));
        System.out.println(page);
        Integer rows = Integer.parseInt(request.getParameter("length"));
        System.out.println(rows);
        String draw = request.getParameter("draw") == null ? "0" : request.getParameter("draw") + 1;
		
		
		Map<String, Object> params = new HashMap<String, Object>();
		if(companyName != null && !companyName.equals("")){
			params.put("companyName", companyName);
		}
		int count = companyService.findCount(null, params);
		logger.info("Conpany Count:"+count);
		SimplePage simplepage = new SimplePage(page/rows+1, rows, count);
		String orderBy = null;
		List<Company> companys = companyService.findByPage(simplepage, params, orderBy);
		Map maps = new HashMap();
		maps.put("data", companys);
		maps.put("draw", draw);
		maps.put("recordsTotal", count);
		maps.put("recordsFiltered", count);
		logger.info("End getCompanyByPage:companylist!");
		return maps;
	}
	
	@RequestMapping(value="/getcompanylistByTree.do")
	@SystemControllerLog
	@ResponseBody
	public List<Company> getCompanyListByTree(){
		Map<String, Object> params = null;
		String orderBy = null;
		int count = companyService.findCount(null, null);
		SimplePage simplepage = new SimplePage(1, count, count);
		List<Company> companys = companyService.findByPage(simplepage, params, orderBy);
		return companys;
	}
	
	@RequestMapping(value="/addCompany.do")
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> addCompany(@ModelAttribute Company company,
			HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to insert new Company!Company Name:"+company.getCompanyName());
		Map<String, Object> returnMap = new HashMap<String, Object>();
		ReturnInfo rInfo = new ReturnInfo();
		company.setCreateBy("Admin");//根据现在操作用户修改
		company.setCreateTime(new Date());
		company.setModifiedDate(new Date());
		int returnCode = companyService.add(company);
		if(returnCode > SUCCESS){
			rInfo.setMsg("公司数据添加成功!");
			rInfo.setRet(SUCCESS);
		}else{
			rInfo.setMsg("公司数据添加失败,请重试！");
			rInfo.setRet(FAIL);
		}
		returnMap.put("rInfo", rInfo);
		logger.info("End to Insert Company!ReturnCode:"+returnCode);
		return returnMap;
	}
	
	@RequestMapping(value="/updateCompany.do")
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> updateCompany(@ModelAttribute Company company,HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to update Company!");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		ReturnInfo rInfo = new ReturnInfo();
		company.setCreateTime(new Date());
		company.setModifiedDate(new Date());
		company.setCreateBy("Lin");
		int returnCode = companyService.modifyById(company);
		if(returnCode > SUCCESS){
			rInfo.setMsg("更新公司数据成功!");
			rInfo.setRet(SUCCESS);
		}else{
			rInfo.setMsg("更新公司数据失败,请重试!");
			rInfo.setRet(FAIL);
		}
		returnMap.put("rInfo", rInfo);
		logger.info("End to update Company!ReturnCode:"+returnCode);
		return returnMap;
	}

	@RequestMapping(value="/deleteCompany.do")
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> delectCompany(@ModelAttribute Company company,HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to delete Company!");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		ReturnInfo rInfo = new ReturnInfo();
		int returnCode = companyService.deleteById(company);
		if(returnCode > SUCCESS){
			rInfo.setMsg("刪除公司数据成功!");
			rInfo.setRet(SUCCESS);
		}else{
			rInfo.setMsg("刪除公司数据失败,请重试!");
			rInfo.setRet(FAIL);
		}
		returnMap.put("rInfo", rInfo);
		logger.info("End to delete company。RetrunCode:"+returnCode);
		return returnMap;
	}

}
