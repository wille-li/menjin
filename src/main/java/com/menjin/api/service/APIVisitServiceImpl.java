package com.menjin.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.mapper.BaseCrudMapper;
import com.base.service.BaseServiceImpl;
import com.menjin.api.mapper.APIVisitMapper;
import com.menjin.api.model.APIVisit;

@Service
public class APIVisitServiceImpl extends BaseServiceImpl<APIVisit> implements APIVisitService {

	@Autowired
	private APIVisitMapper aPIVisitMapper;
	
	@Override
	public BaseCrudMapper<APIVisit> init() {
		// TODO Auto-generated method stub
		return aPIVisitMapper;
	}

	

}
