package com.menjin.api.mapper;

import com.base.mapper.BaseCrudMapper;
import com.menjin.api.model.APIAuth;

public interface APIAuthMapper extends BaseCrudMapper<APIAuth>{
	
	public Integer authByUsernameAndPassword(APIAuth aPIAuth);
	
	public Integer authByToken(APIAuth aPIAuth);
	
	public Integer createAuthInfo(APIAuth aPIAuth);
	
	public int deleteAuthInfo(APIAuth aPIAuth);

}
