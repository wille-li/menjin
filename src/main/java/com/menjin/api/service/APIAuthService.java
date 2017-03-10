package com.menjin.api.service;

import com.menjin.api.model.APIAuth;

public interface APIAuthService {

	public Integer authByUsernameAndPassword(APIAuth aPIAuth);
	
	public Integer authByToken(APIAuth aPIAuth);
	
	public Integer createAuthInfo(APIAuth aPIAuth);
	
	public Integer deleteAuthInfo(APIAuth aPIAuth);
}
