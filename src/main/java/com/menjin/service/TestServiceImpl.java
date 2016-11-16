package com.menjin.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.mapper.BaseCrudMapper;
import com.base.service.BaseServiceImpl;
import com.menjin.mapper.TestMapper;
import com.menjin.model.Test;

/**
 * 测试使用
 * @author wille
 *
 */
@Service
public class TestServiceImpl extends BaseServiceImpl<Test> implements TestService{

	@Resource
	private TestMapper testMapper;
	
	@Override
	public BaseCrudMapper<Test> init() {
		return testMapper;
	}

}
