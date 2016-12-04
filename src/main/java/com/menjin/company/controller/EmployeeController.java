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
import com.menjin.company.model.Employee;
import com.menjin.company.model.TreeJson;
import com.menjin.company.service.CompanyService;
import com.menjin.company.service.DepartmentService;
import com.menjin.company.service.EmployeeService;

@Controller
public class EmployeeController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	CompanyService companyService;

	@Autowired
	DepartmentService departmentService;
	
	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/employee.do")
	@SystemControllerLog
	public String testCompanyController() {
		return "company/employeemanage";
	}

	@RequestMapping(value = "/employeelistBydepartmentId.do")
	@SystemControllerLog
	@ResponseBody
	public Map getEmployeeByDepartemtnId(@Param(value = "departmentId") Integer departmentId,
			@Param(value="pageSize") Integer page,@Param(value="rows") Integer rows) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("departmentId", departmentId);
		int count = employeeService.findCount(null, params);
		logger.info("Conpany Count:"+count);
		SimplePage simplepage = new SimplePage(page, rows, count);
		
		String orderBy = null;
		List<Employee> employees = employeeService.findByPage(simplepage,params, orderBy);
		Map maps = new HashMap();
		maps.put("rows", employees);
		maps.put("total", count);
		return maps;
	}
	
	@RequestMapping(value = "/employeelistBydepartmentIdForCombox.do")
	@SystemControllerLog
	@ResponseBody
	public List<Employee> getEmployeeByDepartemtnIdForCombox(@Param(value = "departmentId") Integer departmentId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("departmentId", departmentId);
		int count = employeeService.findCount(null, params);
		logger.info("Conpany Count:"+count);
		SimplePage simplepage = new SimplePage(1, count, count);
		String orderBy = null;
		List<Employee> employees = employeeService.findByPage(simplepage,params, orderBy);
		return employees;
	}

	@RequestMapping(value = "/employeelist.do")
	@SystemControllerLog
	@ResponseBody
	public List<Employee> getDepartmentByPage() {
		int count = departmentService.findCount(null, null);
		logger.info("Department Count:" + count);
		SimplePage page = new SimplePage(1, 10, count);
		Map<String, Object> params = null;
		String orderBy = null;
		List<Employee> employees = employeeService.findByPage(page,
				params, orderBy);
		return employees;
	}

	@RequestMapping(value = "/companydepartmentlistbytree.do")
	@SystemControllerLog
	@ResponseBody
	public List<TreeJson> getCompanyAndDepartmentByTree() {
		List<TreeJson> treejson = new ArrayList<TreeJson>();
		List<Company> companys = companyService.selectAllAndDepartment();
		for (int i = 0; i < companys.size(); i++) {
			TreeJson tree = new TreeJson();
			tree.setId(companys.get(i).getId());
			tree.setText(companys.get(i).getCompanyName());
			List<TreeJson> child =  new ArrayList<TreeJson>();
			for (int j = 0; j < companys.get(i).getDepartments().size(); j++) {
				TreeJson t = new TreeJson();
				t.setId(companys.get(i).getDepartments().get(j).getId());
				t.setText(companys.get(i).getDepartments().get(j).getDepartmentName());
				child.add(t);
			}
			tree.setChildren(child);
			treejson.add(tree);
		}
		return treejson;
	}

	@RequestMapping(value = "/addemployeement.do")
	@SystemControllerLog
	@ResponseBody
	public Integer addEmployeement(@ModelAttribute Employee employee,
			@Param(value = "departmentId") String departmentId,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("Start to insert employee message!");
		if (departmentId != null && !departmentId.equals("")) {
			Department department = new Department();
			department.setId(Integer.parseInt(departmentId));
			employee.setDepartment(department);
			employee.setModifiedDate(new Date());
			employee.setCreateBy("Admin");
			int returnCode = employeeService.add(employee);
			logger.info("End to insert employee. ReturnCode:"+returnCode);
			return returnCode;
		}

		return null;
	}

	@RequestMapping(value = "/updateemployee.do")
	@SystemControllerLog
	@ResponseBody
	public Integer updateEmployeement(@ModelAttribute Employee employee,
			/*@Param(value = "departmentId") String departmentId,*/
			HttpServletRequest request, HttpServletResponse response) {
		    employee.setModifiedDate(new Date());
			int returnCode = employeeService.modifyById(employee);
			return returnCode;
	}

	@RequestMapping(value = "/deleteemployee.do")
	@SystemControllerLog
	@ResponseBody
	public Integer delectCompany(@ModelAttribute Employee employee,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("Start to delete Employee!");
		int returnCode = employeeService.deleteById(employee);
		logger.info("End to delete Employee。RetrunCode:" + returnCode);
		return returnCode;
	}

}
