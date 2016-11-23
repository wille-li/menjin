package com.menjin.company.service;

import java.util.List;

import com.base.service.BaseService;
import com.menjin.company.model.Department;

public interface DepartmentService extends BaseService<Department>{

	public List<Department> getDepartmentsByCompanyId(Integer companyId);
}
