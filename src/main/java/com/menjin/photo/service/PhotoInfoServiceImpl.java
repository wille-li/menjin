package com.menjin.photo.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.base.mapper.BaseCrudMapper;
import com.base.service.BaseServiceImpl;
import com.menjin.photo.mapper.PhotoInfoMapper;
import com.menjin.photo.model.PhotoInfo;

@Service
@Scope("prototype")
public class PhotoInfoServiceImpl extends BaseServiceImpl<PhotoInfo> implements PhotoInfoService {

	@Resource
	private PhotoInfoMapper photoInfoMapper;
	
	@Override
	public BaseCrudMapper<PhotoInfo> init() {
		return photoInfoMapper;
	}

	@Override
	public PhotoInfo selectByIDCard(String idCard) {
		return photoInfoMapper.selectByIDCard(idCard);
	}

}
