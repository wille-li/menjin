package com.menjin.company.service;

import java.util.List;

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

	@Override
	public List<Company> selectAllAndDepartment() {
		return companyMapper.selectAllAndDepartment();
	}

}
