package com.base.util;

import java.io.Serializable;
import java.util.Collection;

import com.base.entity.JsonReturn;

/**
 * 
 * @ClassName: JsonReturnUtil 
 * @Description: 仅仅为了统一构造Json返回对象<br/>不允许添加任何方法
 * @author Wille Li 
 * @date 2016年12月27日 下午10:45:05 
 *
 */
public final class JsonReturnUtil {

	/**
	 * 没有没回数据的返回构造方法
	 * @param ret 返回码
	 * @param msg 返回信息
	 * @return 构造对象
	 */
	public static  JsonReturn buildReturn(int ret, String msg){
		return new JsonReturn(ret,msg);
	}
	
	/**
	 * 返回单个数据对象
	 * @param ret 返回码
	 * @param msg 返回信息
	 * @param data 返回的数据对象
	 * @return 构造对象
	 */
	public static <T extends Serializable> JsonReturn<T> buildReturn(int ret, String msg, T data){
		return new JsonReturn<T>(ret,msg, data);
	}
	
	/**
	 * 返回单个数据对象
	 * @param ret 返回码
	 * @param msg 返回信息
	 * @param data 返回的数据对象集合
	 * @return 构造对象
	 */
	public static <T extends Serializable> JsonReturn<T> buildReturn(int ret, String msg, Collection<T> datas){
		return new JsonReturn<T>(ret, msg, datas);
	}
}
