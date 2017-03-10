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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.base.annotation.log.SystemControllerLog;
import com.base.entity.ReturnInfo;
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
	
    public final static int SUCCESS = 0;
	
	public final static int FAIL = 1;

	@RequestMapping(value = "/employee.do")
	@SystemControllerLog
	public String testCompanyController() {
		return "company/employeemanage";
	}

	@RequestMapping(value = "/employeelistBydepartmentId.do")
	@SystemControllerLog
	@ResponseBody
	public Map getEmployeeByDepartemtnId(@Param(value = "companyId") Integer companyId,@Param(value = "employeeName") String employeeName,
			HttpServletRequest request) {
		Integer page = Integer.parseInt(request.getParameter("start"));
        System.out.println(page);
        Integer rows = Integer.parseInt(request.getParameter("length"));
        System.out.println(rows);
        String draw = request.getParameter("draw") == null ? "0" : request.getParameter("draw") + 1;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyId", companyId);
		params.put("employeeName", employeeName);
		int count = employeeService.findCount(null, params);
		logger.info("Conpany Count:"+count);
		SimplePage simplepage = new SimplePage(page/rows+1, rows, count);
		
		String orderBy = null;
		List<Employee> employees = employeeService.findByPage(simplepage,params, orderBy);
		Map maps = new HashMap();
		maps.put("data", employees);
		maps.put("draw", draw);
		maps.put("recordsTotal", count);
		maps.put("recordsFiltered", count);
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
	public Map<String, Object> addEmployeement(@ModelAttribute Employee employee,
			@Param(value = "companyId") String companyId,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("Start to insert employee message!");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		ReturnInfo rInfo = new ReturnInfo();
		if (companyId != null && !companyId.equals("")) {
			Company company = new Company();
			company.setId(Integer.parseInt(companyId));
			company = companyService.findById(company);
			if(company.getCompanyName().equals("") || company.getCompanyName() == null  ){
				rInfo.setMsg("请选择所属公司！");
				rInfo.setRet(FAIL);
				returnMap.put("rInfo", rInfo);
				return returnMap;
			}
			employee.setCompany(company);
			employee.setModifiedDate(new Date());
			employee.setCreateBy("Admin");
			String index = company.getCompanyName()+","+company.getCompanyAddress()+","+company.getDoorPlate()
					+","+company.getCompanyPhone()+","+employee.getEmployeeName()+","+employee.getMobile();
			employee.setIndex(index);
			int returnCode = employeeService.add(employee);
			if(returnCode > SUCCESS){
				rInfo.setMsg("员工数据添加成功!");
				rInfo.setRet(SUCCESS);
			}else{
				rInfo.setMsg("员工数据添加失败,请重试！");
				rInfo.setRet(FAIL);
			}
			logger.info("End to insert employee. ReturnCode:"+returnCode);
		}else{
			rInfo.setMsg("请选择所属公司！");
			rInfo.setRet(FAIL);
		}
		returnMap.put("rInfo", rInfo);
		return returnMap;
	}

	@RequestMapping(value = "/updateemployee.do")
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> updateEmployeement(
			@ModelAttribute Employee employee,
			@Param(value = "companyId") String companyId,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		ReturnInfo rInfo = new ReturnInfo();
		employee.setModifiedDate(new Date());
		Company company = new Company();
		company.setId(Integer.parseInt(companyId));
		employee.setCompany(company);
		company = companyService.findById(company);
		String index = company.getCompanyName()+","+company.getCompanyAddress()+","+company.getDoorPlate()
				+","+company.getCompanyPhone()+","+employee.getEmployeeName()+","+employee.getMobile();
		employee.setIndex(index);
		int returnCode = employeeService.modifyById(employee);
		if(returnCode > SUCCESS){
			rInfo.setMsg("员工数据添加成功!");
			rInfo.setRet(SUCCESS);
		}else{
			rInfo.setMsg("员工数据添加失败,请重试！");
			rInfo.setRet(FAIL);
		}
		returnMap.put("rInfo", rInfo);
		return returnMap;
	}

	@RequestMapping(value = "/deleteemployee.do")
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> delectCompany(@ModelAttribute Employee employee,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("Start to delete Employee!");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		ReturnInfo rInfo = new ReturnInfo();
		int returnCode = employeeService.deleteById(employee);
		if(returnCode > SUCCESS){
			rInfo.setMsg("刪除员工数据成功!");
			rInfo.setRet(SUCCESS);
		}else{
			rInfo.setMsg("刪除员工数据失败,请重试!");
			rInfo.setRet(FAIL);
		}
		returnMap.put("rInfo", rInfo);
		logger.info("End to delete Employee。RetrunCode:" + returnCode);
		return returnMap;
	}
	
	@RequestMapping(value="/uploadBatchEmployees.do")
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> BatchUploadEmployees(@RequestParam("uploadfile") MultipartFile excelFile, String idCardNum,
			String visitorName, String birth){
		logger.info("Start to Upload Batch Company!");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		ReturnInfo rInfo = new ReturnInfo();
		System.out.println(excelFile.getName());
		System.out.println(excelFile.getOriginalFilename());
		System.out.println(excelFile.getSize());
		/*int returnCode = companyService.add(company);
		if(returnCode > SUCCESS){
			rInfo.setMsg("公司数据添加成功!");
			rInfo.setRet(SUCCESS);
		}else{
			rInfo.setMsg("公司数据添加失败,请重试！");
			rInfo.setRet(FAIL);
		}*/
		returnMap.put("rInfo", rInfo);
		return returnMap;
	}

}
