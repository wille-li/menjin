package com.base.entity;

import java.io.Serializable;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @ClassName: JsonReturn 
 * @Description: JSON接口统一返回类
 * @author Wille Li 
 * @date 2016年12月27日 下午9:51:58 
 * 
 * @param <T> 返回数据泛型对象，必须序列化
 */
@JsonInclude(Include.NON_NULL) /* 空值不显示设置 */
public class JsonReturn<T extends Serializable> implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -1587424306016383442L;
	

	/**
	 * JSON 返回头信息，包括返回码和返回消息
	 */
	JsonReturnHeader head;
	
	/**
	 * 返回单个数据对象
	 */
	private T data;
	
	/**
	 * 返回数据集合
	 */
	private Collection<T> datas;
	
	
	
	/**
	 * 只有返回头信息
	 * @param ret 返回码
	 * @param msg 返回信息
	 */
	public JsonReturn(int ret, String msg) {
		super();
		this.head = new JsonReturnHeader(ret, msg);
	}

	/**
	 * 只有一个对象返回构造方法
	 * @param ret 返回码
	 * @param msg 返回信息
	 * @param data 返回对象
	 */
	public JsonReturn(int ret, String msg, T data) {
		super();
		this.head = new JsonReturnHeader(ret, msg);
		this.data = data;
	}
	
	/**
	 * 数据集合返回对象构造方法
	 * @param ret 返回码
	 * @param msg 返回信息
	 * @param datas 返回数据集合
	 */
	public JsonReturn(int ret, String msg, Collection<T> datas) {
		super();
		this.head = new JsonReturnHeader(ret, msg);
		this.datas = datas;
	}

	public JsonReturnHeader getHead() {
		return head;
	}

	public void setHead(JsonReturnHeader head) {
		this.head = head;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Collection<T> getDatas() {
		return datas;
	}

	public void setDatas(Collection<T> datas) {
		this.datas = datas;
	}

	@Override
	public String toString() {
		return "JsonReturn [head=" + head + ", data=" + data + ", datas=" + datas + "]";
	}

}
