package com.menjin.api.service;

import java.util.List;

import com.base.service.BaseService;
import com.menjin.api.model.APICompany;

public interface APICompanyService extends BaseService<APICompany>{

	public List<APICompany> selectAllAndDepartment();
}
