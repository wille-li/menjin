package com.menjin.company.service;

import java.util.List;

import com.base.service.BaseService;
import com.menjin.company.model.Company;

public interface CompanyService extends BaseService<Company>{

	public List<Company> selectAllAndDepartment();
	
	public Company selectByCompanyName(String companyName);
}
