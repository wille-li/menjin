package com.menjin.visit.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.mapper.BaseCrudMapper;
import com.base.service.BaseServiceImpl;
import com.menjin.visit.mapper.VisitRecordMapper;
import com.menjin.visit.model.VisitRecord;

/**
 * @author Lin
 *
 */
@Service
public class VisitRecordServiceImpl extends BaseServiceImpl<VisitRecord> implements VisitRecordService{

	@Resource
	private VisitRecordMapper visitMapper;

	@Override
	public BaseCrudMapper<VisitRecord> init() {
		return visitMapper;
	}

	@Override
	public String getNewTxnNo(Map<String, Object> params) {
		return visitMapper.getNewTxnNo(params);
	}



}
