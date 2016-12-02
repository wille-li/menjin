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
import com.base.entity.SimplePage;
import com.menjin.company.model.Company;
import com.menjin.company.service.CompanyService;

@Controller
public class CompanyController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	CompanyService companyService;
	
	
	
	@RequestMapping(value="/company.do")
	@SystemControllerLog
	public String testCompanyController(){
		return "company/companymanage";
	}
	
	/*@RequestMapping(value="/companylist.do")
	@SystemControllerLog
	@ResponseBody
	public List<Company> getCompanyByPage(){
		int count = companyService.findCount(null, null);
		logger.info("Conpany Count:"+count);
		SimplePage page = new SimplePage(1, 10, count);
		Map<String, Object> params = null;
		String orderBy = null;
		List<Company> companys = companyService.findByPage(page, params, orderBy);
		return companys;
	}*/
	
	@RequestMapping(value="/companylist.do")
	@SystemControllerLog
	@ResponseBody
	public Map getCompanyByPage(@Param(value="pageSize") Integer page,@Param(value="rows") Integer rows){
		logger.info("Start to getCompanyByPage:companylist.do");
		int count = companyService.findCount(null, null);
		logger.info("Conpany Count:"+count);
		SimplePage simplepage = new SimplePage(page, rows, count);
		Map<String, Object> params = null;
		String orderBy = null;
		List<Company> companys = companyService.findByPage(simplepage, params, orderBy);
		Map maps = new HashMap();
		maps.put("rows", companys);
		maps.put("total", count);
		logger.info("End getCompanyByPage:companylist!");
		return maps;
	}
	
	@RequestMapping(value="/addCompany.do")
	@SystemControllerLog
	@ResponseBody
	/**
	 * @Param(value = "companyName") String companyName,
			@Param(value = "companyAddress")String companyAddress,
			@Param(value = "companyPhone")String companyPhone,
	 * @return
	 */
	public Integer addCompany(@ModelAttribute Company company,
			HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to insert new Company!Company Name:"+company.getCompanyName());
		company.setCreateBy("Admin");//根据现在操作用户修改
		company.setCreateTime(new Date());
		company.setModifiedDate(new Date());
		int returnCode = companyService.add(company);
		logger.info("End to Insert Company!ReturnCode:"+returnCode);
		return returnCode;
	}
	
	@RequestMapping(value="/updateCompany.do")
	@SystemControllerLog
	@ResponseBody
	public Integer updateCompany(@ModelAttribute Company company,HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to update Company!");
		company.setCreateTime(new Date());
		company.setModifiedDate(new Date());
		company.setCreateBy("Lin");
		int returnCode = companyService.modifyById(company);
		logger.info("End to update Company!ReturnCode:"+returnCode);
		return returnCode;
	}

	@RequestMapping(value="/deleteCompany.do")
	@SystemControllerLog
	@ResponseBody
	public Integer delectCompany(@ModelAttribute Company company,HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to delete Company!");
		int returnCode = companyService.deleteById(company);
		logger.info("End to delete company。RetrunCode:"+returnCode);
		return returnCode;
	}

}
