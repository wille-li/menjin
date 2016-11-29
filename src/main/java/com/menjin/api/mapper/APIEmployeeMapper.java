package com.menjin.api.mapper;

import java.util.List;

import com.base.mapper.BaseCrudMapper;
import com.menjin.api.model.APIEmployee;

/**
 * @author Lin
 *
 */
public interface APIEmployeeMapper extends BaseCrudMapper<APIEmployee>{

	/**
	 * 通过部门Id找出部门下的所有员工
	 * @param departmentId
	 * @return
	 */
	public List<APIEmployee> getEmployeesByDepartmentId(Integer departmentId);
	
	/**
	 * 通过公司Id找出公司所有员工
	 * @param companyId
	 * @return
	 */
	public List<APIEmployee> getEmployeesByCompanyId(Integer companyId);
}
