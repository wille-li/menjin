package com.base.service;

/**
 * Service 基础接口
 * @author wille
 *
 * @param <ModelType> 泛型
 */
public abstract interface BaseService<ModelType> {

	/**
	 * 删除对象
	 * @param entity
	 * @return
	 */
	public abstract int deleteById(ModelType entity);
	
	/**
	 * 创建对象
	 * @param entity
	 * @return
	 */
	public abstract int add(ModelType entity);
	
	/**
	 * 修改对象
	 * @param entity
	 * @return
	 */
	public abstract int modifyById(ModelType entity);
	
	/**
	 * 查找对象
	 * @param entity
	 * @return
	 */
	public abstract ModelType findById(ModelType entity);
	
}
