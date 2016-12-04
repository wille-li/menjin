package com.menjin.visit.service;

import java.util.Map;

import com.base.service.BaseService;
import com.menjin.visit.model.VisitRecord;

public interface VisitRecordService extends BaseService<VisitRecord>{

	public String getNewTxnNo(Map<String, String> params);
}
