package com.menjin.visit.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.mapper.BaseCrudMapper;
import com.base.service.BaseServiceImpl;
import com.menjin.visit.mapper.VisitorMapper;
import com.menjin.visit.model.Visitor;

/**
 * @author Lin
 *
 */
@Service
public class VisitorServiceImpl extends BaseServiceImpl<Visitor> implements VisitorService{

	@Resource
	private VisitorMapper visitorMapper;

	@Override
	public BaseCrudMapper<Visitor> init() {
		return visitorMapper;
	}

	@Override
	public Visitor selectByIDCar(String idCardNum) {
		return visitorMapper.selectByIDCar(idCardNum);
	}



}
