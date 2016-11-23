package com.menjin.company.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.mapper.BaseCrudMapper;
import com.base.service.BaseServiceImpl;
import com.menjin.company.mapper.DepartmentMapper;
import com.menjin.company.model.Department;

/**
 * @author Lin
 *
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService{

	@Resource
	private DepartmentMapper departmentMapper;

	@Override
	public BaseCrudMapper<Department> init() {
		return departmentMapper;
	}

	@Override
	public List<Department> getDepartmentsByCompanyId(Integer companyId) {
		return departmentMapper.getDepartmentsByCompanyId(companyId);
	}


	
}
