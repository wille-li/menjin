package com.menjin.company.service;

import java.util.List;

import com.base.service.BaseService;
import com.menjin.company.model.Employee;

public interface EmployeeService extends BaseService<Employee> {

	public List<Employee> getEmployeesByDepartmentId(Integer departmentId);

	public List<Employee> getEmployeesByCompanyId(Integer companyId);
}
