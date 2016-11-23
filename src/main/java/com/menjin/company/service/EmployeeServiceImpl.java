package com.menjin.company.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.mapper.BaseCrudMapper;
import com.base.service.BaseServiceImpl;
import com.menjin.company.mapper.EmployeeMapper;
import com.menjin.company.model.Employee;

/**
 * @author Lin
 *
 */
@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements EmployeeService{

	@Resource
	private EmployeeMapper employeeMapper;

	@Override
	public BaseCrudMapper<Employee> init() {
		return employeeMapper;
	}

	@Override
	public List<Employee> getEmployeesByDepartmentId(Integer departmentId) {
		return employeeMapper.getEmployeesByDepartmentId(departmentId);
	}

	@Override
	public List<Employee> getEmployeesByCompanyId(Integer companyId) {
		return employeeMapper.getEmployeesByCompanyId(companyId);
	}


	
}
