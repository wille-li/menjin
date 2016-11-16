package com.base.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.base.entity.SimplePage;

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
	
	/**
	 * 分页查询对象信息
	 * @param page 分页信息
	 * @param params 其他参数
	 * @param orderBy 排序
	 * @return
	 */
	public abstract List<ModelType> findByPage(
			SimplePage page, 
			Map<String, Object> params, 
			String orderBy);
	
	/**
	 * 查询对象集合,不分页方法(建议限制条数)
	 * @param entity 对象信息
	 * @param paramMap 其他参数集合
	 * @return
	 */
	public abstract List<ModelType> findByParams(
			@Param("model") ModelType entity,
			@Param("params") Map<String, Object> paramMap);
	
	/**
	 * 获取条数
	 * @param entity 对象信息, (不一定要用到,可以传空)
	 * @param paramMap 其他参数, (不一定要用到,可以传空)
	 * @return
	 */
	public abstract int findCount(
			@Param("model") ModelType entity,
			@Param("params") Map<String, Object> paramMap);
}
