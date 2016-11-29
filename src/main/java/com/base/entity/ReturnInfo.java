package com.base.entity;

import java.io.Serializable;

public class ReturnInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3064701663428488485L;

	private Integer ret;
	
	private String msg;

	public Integer getRet() {
		return ret;
	}

	public void setRet(Integer ret) {
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
		return "ReturnInfo [ret=" + ret + ", msg=" + msg + "]";
	}
	
}
