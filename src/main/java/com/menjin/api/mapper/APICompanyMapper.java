package com.menjin.api.mapper;

import java.util.List;

import com.base.mapper.BaseCrudMapper;
import com.menjin.api.model.APICompany;

/**
 * @author Wille
 *
 */
public interface APICompanyMapper extends BaseCrudMapper<APICompany>{

	public List<APICompany> selectAllAndDepartment();
}
