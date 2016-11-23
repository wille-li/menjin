package com.menjin.user.service.Impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.base.mapper.BaseCrudMapper;
import com.base.service.BaseServiceImpl;
import com.menjin.user.mapper.UserMapper;
import com.menjin.user.model.User;
import com.menjin.user.service.If.UserServiceIf;

/**
 * User管理的接口实现类
 * @author Jack
 *
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserServiceIf {

	@Resource
	private UserMapper userMapper;
	
	@Override
	public BaseCrudMapper<User> init() {
		// TODO Auto-generated method stub
		return this.userMapper;
	}

	

}
