package com.menjin.company.controller;

import java.util.ArrayList;
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
import com.menjin.company.model.Department;
import com.menjin.company.service.CompanyService;
import com.menjin.company.service.DepartmentService;

@Controller
public class DepartmentController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	CompanyService companyService;

	@Autowired
	DepartmentService departmentService;

	@RequestMapping(value = "/department.do")
	@SystemControllerLog
	public String testCompanyController() {
		return "company/departmentmanage";
	}

	@RequestMapping(value = "/departmentlistBycompanyId.do")
	@SystemControllerLog
	@ResponseBody
	public Map getDepartmentByCompanyId(@Param(value = "companyId") Integer companyId,
			@Param(value="pageSize") Integer page,@Param(value="rows") Integer rows) {
		logger.info("Start to get Departments By Company ID !");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyId", companyId);
		int count = departmentService.findCount(null, params);
		SimplePage simplepage = new SimplePage(page, rows, count);
		String orderBy = null;
		List<Department> departments = departmentService.findByPage(simplepage,params, orderBy);
		Map maps = new HashMap();
		maps.put("rows", departments);
		maps.put("total", count);
		logger.info("End to get Departments!");
		return maps;
	}
	
	@RequestMapping(value = "/departmentlistBycompanyIdForCombox.do")
	@SystemControllerLog
	@ResponseBody
	public List<Department> getDepartmetsByCompanyIdForCombox(@Param(value = "companyId") Integer companyId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyId", companyId);
		int count = departmentService.findCount(null, params);
		SimplePage simplepage = new SimplePage(1, count, count);
		String orderBy = null;
		List<Department> departments = departmentService.findByPage(simplepage,params, orderBy);
		return departments;
	}

	@RequestMapping(value = "/departmentlist.do")
	@SystemControllerLog
	@ResponseBody
	public List<Department> getDepartmentByPage() {
		int count = departmentService.findCount(null, null);
		logger.info("Department Count:" + count);
		SimplePage page = new SimplePage(1, 10, count);
		Map<String, Object> params = null;
		String orderBy = null;
		List<Department> departments = departmentService.findByPage(page,
				params, orderBy);
		return departments;
	}

	@RequestMapping(value = "/departmentlistbytree.do")
	@SystemControllerLog
	@ResponseBody
	public Map getCompanyByTree() {
		logger.info("Start to get CompanyTree!  departmentlistbytree.do");
		Map companysTree = new HashMap();
		int count = companyService.findCount(null, null);
		logger.info("Conpany Count:" + count);
		SimplePage page = new SimplePage(1, 80, count);
		Map<String, Object> params = null;
		String orderBy = null;
		List<Company> companys = companyService.findByPage(page, params,
				orderBy);
		companysTree.put("id", 1);
		companysTree.put("text", "公司列表");

		List<Map> maps = new ArrayList<Map>();
		for (int i = 0; i < companys.size(); i++) {
			Map map = new HashMap();
			map.put("id", companys.get(i).getId());
			map.put("text", companys.get(i).getCompanyName());
			maps.add(map);
		}
		companysTree.put("children", maps);
		logger.info("End to get CompanyTree!");
		return companysTree;
	}

	@RequestMapping(value = "/adddepartment.do")
	@SystemControllerLog
	@ResponseBody
	public Integer addDepartment(@ModelAttribute Department department,
			@Param(value = "companyId") String companyId,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("Start to insert new Department!Department Name:"+department.getDepartmentName());
		if (companyId != null && !companyId.equals("")) {
			Company company = new Company();
			company.setId(Integer.parseInt(companyId));
			department.setCompany(company);
			department.setCreateTime(new Date());
			department.setModifiedDate(new Date());
			department.setCreateBy("Admin");
			int returnCode = departmentService.add(department);
			logger.info("End to Insert Company!ReturnCode:"+returnCode);
			return returnCode;
		}
		return null;
	}

	@RequestMapping(value = "/updatedepartment.do")
	@SystemControllerLog
	@ResponseBody
	public Integer updateCompany(@ModelAttribute Department department,
			@Param(value = "companyId") String companyId,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("Start to update Department!");
		if(companyId != null && !companyId.equals("")){
			Company company = new Company();
			company.setId(Integer.parseInt(companyId));
			department.setCompany(company);
			department.setModifiedDate(new Date());
			department.setCreateBy("Admin");
			int returnCode = departmentService.modifyById(department);
			logger.info("End to update Department!ReturnCode:"+returnCode);
			return returnCode;
		}
		
		return null;
	}

	@RequestMapping(value = "/deletedepartment.do")
	@SystemControllerLog
	@ResponseBody
	public Integer delectCompany(@ModelAttribute Department department,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("Start to delete Department!");
		int returnCode = departmentService.deleteById(department);
		logger.info("End to delete Department。RetrunCode:" + returnCode);
		return returnCode;
	}

}
