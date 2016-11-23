package com.menjin.user.service.Impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.base.mapper.BaseCrudMapper;
import com.base.service.BaseServiceImpl;
import com.menjin.user.mapper.RoleMapper;
import com.menjin.user.model.Role;
import com.menjin.user.service.If.RoleServiceIf;

/**
 * 权限管理的接口实现类
 * @author Jack
 *
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleServiceIf {

	@Resource
	private RoleMapper roleMapper;
	
	@Override
	public BaseCrudMapper<Role> init() {
		// TODO Auto-generated method stub
		return this.roleMapper;
	}

	
	
}
