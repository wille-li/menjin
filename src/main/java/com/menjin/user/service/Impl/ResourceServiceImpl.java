package com.menjin.user.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.base.mapper.BaseCrudMapper;
import com.base.service.BaseServiceImpl;
import com.menjin.user.mapper.ResourceMapper;
import com.menjin.user.model.Resource;
import com.menjin.user.service.If.ResourceServiceIf;

/**
 * 访问资源管理的接口实现类
 * @author Jack
 *
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements ResourceServiceIf {

	@Autowired
	private ResourceMapper resourceMapper;
	
	@Override
	public BaseCrudMapper<Resource> init() {
		// TODO Auto-generated method stub
		return this.resourceMapper;
	}
}
