package com.base.mapper;

/**
 * Mapper的基础接口
 * @author wille
 *
 */
public abstract interface BaseCrudMapper<ModelType> {

	/**
	 * 插入语句接口
	 * @param entity 对象
	 * @return 
	 */
	public abstract int insert(ModelType entity);
	
	
	/**
	 * 根据主键查询对象
	 * @param entity 对象
	 * @return 返回一个对象或者null
	 */
	public abstract ModelType selectByPrimaryKey(ModelType entity);

	/**
	 * 删除对象
	 * @param entity 传入主键
	 * @return
	 */
	public abstract int deleteByPrimaryKey(ModelType entity);
	
	/**
	 * 更新对象资料
	 * @param entity 必须存在主键
	 * @return
	 */
	public abstract int updateByPrimaryKey(ModelType entity);
}
