package com.menjin.afr.service;

/**
 * 脸部识别服务
 * @author Wille
 *
 */
public interface AfrService {
	
	/**
	 * 身份认证
	 * @param userID
	 * @param fileName1
	 * @param fileName2
	 * @return
	 */
	public boolean vertifyUser(String userID, String fileName1, String fileName2);

}
