package com.menjin.company.mapper;

import java.util.List;

import com.base.mapper.BaseCrudMapper;
import com.menjin.company.model.Company;

/**
 * @author Lin
 *
 */
public interface CompanyMapper extends BaseCrudMapper<Company>{

	public List<Company> selectAllAndDepartment();
	
	public Company selectByCompanyName(String companyName);
}
