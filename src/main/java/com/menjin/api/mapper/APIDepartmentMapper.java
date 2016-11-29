package com.menjin.api.mapper;

import java.util.List;

import com.base.mapper.BaseCrudMapper;
import com.menjin.api.model.APIDepartment;

/**
 * @author Lin
 *
 */
public interface APIDepartmentMapper extends BaseCrudMapper<APIDepartment>{

	/**
	 * 通过公司Id找出公司的所有部门
	 * @param departmentId
	 * @return
	 */
	public List<APIDepartment> getDepartmentsByCompanyId(Integer companyId);
}
