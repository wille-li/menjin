package com.menjin.photo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.mapper.BaseCrudMapper;
import com.base.service.BaseServiceImpl;
import com.menjin.photo.mapper.PhotoInfoMapper;
import com.menjin.photo.model.PhotoInfo;

@Service
public class PhotoInfoServiceImpl extends BaseServiceImpl<PhotoInfo> implements PhotoInfoService {

	@Autowired
	private PhotoInfoMapper photoInfoMapper;
	
	@Override
	public BaseCrudMapper<PhotoInfo> init() {
		return photoInfoMapper;
	}

}
