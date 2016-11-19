package com.base.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.entity.LogInfo;
import com.base.mapper.BaseCrudMapper;
import com.base.mapper.LogInfoMapper;

/**
 * Log Service 实现
 * @author Wille
 *
 */
@Service
public class LogInfoServiceImpl extends BaseServiceImpl<LogInfo> implements LogInfoService{

	@Resource
	private LogInfoMapper logInfoMapper;
	
	@Override
	public BaseCrudMapper<LogInfo> init() {
		// TODO Auto-generated method stub
		return this.logInfoMapper;
	}

}
