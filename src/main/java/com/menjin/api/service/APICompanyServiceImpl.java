package com.menjin.api.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.mapper.BaseCrudMapper;
import com.base.service.BaseServiceImpl;
import com.menjin.api.mapper.APICompanyMapper;
import com.menjin.api.model.APICompany;

/**
 * @author Lin
 *
 */
@Service
public class APICompanyServiceImpl extends BaseServiceImpl<APICompany> implements APICompanyService{

	@Resource
	private APICompanyMapper aPICompanyMapper;

	@Override
	public BaseCrudMapper<APICompany> init() {
		return aPICompanyMapper;
	}

	@Override
	public List<APICompany> selectAllAndDepartment() {
		return aPICompanyMapper.selectAllAndDepartment();
	}

}
