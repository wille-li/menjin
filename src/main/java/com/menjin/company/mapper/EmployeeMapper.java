package com.menjin.company.mapper;

import java.util.List;

import com.base.mapper.BaseCrudMapper;
import com.menjin.company.model.Employee;

/**
 * @author Lin
 *
 */
public interface EmployeeMapper extends BaseCrudMapper<Employee>{

	/**
	 * 通过部门Id找出部门下的所有员工
	 * @param departmentId
	 * @return
	 */
	public List<Employee> getEmployeesByDepartmentId(Integer departmentId);
	
	/**
	 * 通过公司Id找出公司所有员工
	 * @param companyId
	 * @return
	 */
	public List<Employee> getEmployeesByCompanyId(Integer companyId);
}
