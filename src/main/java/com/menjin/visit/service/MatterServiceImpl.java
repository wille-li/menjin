package com.menjin.visit.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.mapper.BaseCrudMapper;
import com.base.service.BaseServiceImpl;
import com.menjin.visit.mapper.MatterMapper;
import com.menjin.visit.model.Matter;

/**
 * @author Lin
 *
 */
@Service
public class MatterServiceImpl extends BaseServiceImpl<Matter> implements MatterService{

	@Resource
	private MatterMapper matterMapper;

	@Override
	public BaseCrudMapper<Matter> init() {
		return matterMapper;
	}



}
