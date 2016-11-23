package com.menjin.company.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.mapper.BaseCrudMapper;
import com.base.service.BaseServiceImpl;
import com.menjin.company.mapper.CompanyMapper;
import com.menjin.company.model.Company;

/**
 * @author Lin
 *
 */
@Service
public class CompanyServiceImpl extends BaseServiceImpl<Company> implements CompanyService{

	@Resource
	private CompanyMapper companyMapper;

	@Override
	public BaseCrudMapper<Company> init() {
		return companyMapper;
	}
	
}
