package com.menjin.camera.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.mapper.BaseCrudMapper;
import com.base.service.BaseServiceImpl;
import com.menjin.camera.mapper.CameraMapper;
import com.menjin.camera.model.Camera;

@Service
public class CameraServiceImpl extends BaseServiceImpl<Camera> implements CameraService {

	@Autowired
	private CameraMapper cameraMapper;
	
	@Override
	public BaseCrudMapper<Camera> init() {
		return cameraMapper;
	}

}
