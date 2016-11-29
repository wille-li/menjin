package com.menjin.api.service;

import java.util.List;

import com.base.service.BaseService;
import com.menjin.api.model.APIDepartment;

public interface APIDepartmentService extends BaseService<APIDepartment>{

	public List<APIDepartment> getDepartmentsByCompanyId(Integer companyId);
}
