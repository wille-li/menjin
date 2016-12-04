package com.menjin.visit.mapper;

import java.util.Map;

import com.base.mapper.BaseCrudMapper;
import com.menjin.visit.model.VisitRecord;

/**
 * @author Lin
 *
 */
public interface VisitRecordMapper extends BaseCrudMapper<VisitRecord>{

	public String getNewTxnNo(Map<String, String> params);
}
