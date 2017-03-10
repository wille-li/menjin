package com.menjin.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.menjin.api.mapper.APIAuthMapper;
import com.menjin.api.model.APIAuth;

@Service
public class APIAuthServiceImpl implements APIAuthService {

	@Autowired
	private APIAuthMapper aPIAuthMapper;
	
	@Override
	public Integer authByUsernameAndPassword(APIAuth aPIAuth) {
		return aPIAuthMapper.authByUsernameAndPassword(aPIAuth);
	}

	@Override
	public Integer authByToken(APIAuth aPIAuth) {
		return aPIAuthMapper.authByToken(aPIAuth);
	}

	@Override
	public Integer createAuthInfo(APIAuth aPIAuth) {
		
		return aPIAuthMapper.createAuthInfo(aPIAuth);
	}

	@Override
	public Integer deleteAuthInfo(APIAuth aPIAuth) {
		return aPIAuthMapper.deleteAuthInfo(aPIAuth);
	}

}
