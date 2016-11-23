package com.menjin.company.mapper;

import java.util.List;

import com.base.mapper.BaseCrudMapper;
import com.menjin.company.model.Department;

/**
 * @author Lin
 *
 */
public interface DepartmentMapper extends BaseCrudMapper<Department>{

	/**
	 * 通过公司Id找出公司的所有部门
	 * @param departmentId
	 * @return
	 */
	public List<Department> getDepartmentsByCompanyId(Integer companyId);
}
