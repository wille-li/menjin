package com.base.entity;

import java.io.Serializable;

/**
 * 
 * @ClassName: JsonReturnHeader 
 * @Description: JSON 返回头 
 * @author Wille Li 
 * @date 2016年12月27日 下午10:04:17 
 *
 */
public class JsonReturnHeader implements Serializable{

	/** 
	 * @Fields serialVersionUID : 序列化 
	 */ 
	private static final long serialVersionUID = -8083416092154304198L;
	
	private int ret;
	
	private String msg;
	
	public JsonReturnHeader(int ret, String msg) {
		super();
		this.ret = ret;
		this.msg = msg;
	}

	public int getRet() {
		return ret;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "JsonReturnHeader [ret=" + ret + ", msg=" + msg + "]";
	}
	

}
