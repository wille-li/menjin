package com.menjin.api.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.mapper.BaseCrudMapper;
import com.base.service.BaseServiceImpl;
import com.menjin.api.mapper.APIEmployeeMapper;
import com.menjin.api.model.APIEmployee;

/**
 * @author Lin
 *
 */
@Service
public class APIEmployeeServiceImpl extends BaseServiceImpl<APIEmployee> implements APIEmployeeService{

	@Resource
	private APIEmployeeMapper aPIEmployeeMapper;

	@Override
	public BaseCrudMapper<APIEmployee> init() {
		return aPIEmployeeMapper;
	}

	@Override
	public List<APIEmployee> getEmployeesByDepartmentId(Integer departmentId) {
		return aPIEmployeeMapper.getEmployeesByDepartmentId(departmentId);
	}

	@Override
	public List<APIEmployee> getEmployeesByCompanyId(Integer companyId) {
		return aPIEmployeeMapper.getEmployeesByCompanyId(companyId);
	}


	
}
