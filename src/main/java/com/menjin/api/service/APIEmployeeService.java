package com.menjin.api.service;

import java.util.List;

import com.base.service.BaseService;
import com.menjin.api.model.APIEmployee;

public interface APIEmployeeService extends BaseService<APIEmployee> {

	public List<APIEmployee> getEmployeesByDepartmentId(Integer departmentId);

	public List<APIEmployee> getEmployeesByCompanyId(Integer companyId);
}
