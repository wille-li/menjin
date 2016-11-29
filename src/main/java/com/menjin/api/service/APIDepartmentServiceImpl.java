package com.menjin.api.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.mapper.BaseCrudMapper;
import com.base.service.BaseServiceImpl;
import com.menjin.api.mapper.APIDepartmentMapper;
import com.menjin.api.model.APIDepartment;

/**
 * @author Lin
 *
 */
@Service
public class APIDepartmentServiceImpl extends BaseServiceImpl<APIDepartment> implements APIDepartmentService{

	@Resource
	private APIDepartmentMapper aPIDepartmentMapper;

	@Override
	public BaseCrudMapper<APIDepartment> init() {
		return aPIDepartmentMapper;
	}

	@Override
	public List<APIDepartment> getDepartmentsByCompanyId(Integer companyId) {
		return aPIDepartmentMapper.getDepartmentsByCompanyId(companyId);
	}


	
}
