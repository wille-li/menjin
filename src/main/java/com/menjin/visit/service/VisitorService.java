package com.menjin.visit.service;

import com.base.service.BaseService;
import com.menjin.visit.model.Visitor;

public interface VisitorService extends BaseService<Visitor>{

	public Visitor selectByIDCar(String idCardNum);
}
