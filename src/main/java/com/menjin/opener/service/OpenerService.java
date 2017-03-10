package com.menjin.opener.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.base.service.BaseService;
import com.menjin.opener.mapper.OpenerMapper;
import com.menjin.opener.model.Opener;

/**
 * 连接继电器服务类
 * @author Wille
 *
 */
public interface OpenerService extends BaseService<Opener>{
	
	/**
	 * 继电器打开信号
	 */
	public final String SIGN_ON = "on";
	
	/**
	 * 继电器关闭信号
	 */
	public final String SIGN_OFF = "off";
	
	/**
	 * 继电器获取状态信号
	 */
	public final String SIGN_READ = "read";
	
	/**
	 * 继电器名称不匹配
	 */
	public final String RETURN_NOT_SAME_NAME = "10";
	
	/**
	 * 继电器名称不匹配
	 */
	public final String RETURN_WRITE_FAIL = "11";
	
	/**
	 * 连接继电器失败
	 */
	public final String RETURN_CONNCETION_ERROR = "99";
	
	/**
	 * 获取继电器的状态
	 * @param opener
	 * @return
	 */
	public String getStatus(Opener opener) throws IOException ;
	
	/**
	 * 打开继电器
	 * @param opener
	 * @return
	 */
	public String open(Opener opener) throws IOException ;
	
	/**
	 * 关闭继电器
	 * @param opener
	 * @return
	 */
	public String close(Opener opener) throws IOException ;
}
