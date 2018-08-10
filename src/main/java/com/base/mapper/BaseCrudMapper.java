package com.base.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.base.entity.SimplePage;

/**
 * Mapper的基础接口
 * @author wille
 *
 */
public interface BaseCrudMapper<ModelType> {

	/**
	 * 插入语句接口
	 * @param entity 对象
	 * @return 
	 */
	int insert(ModelType entity);
	
	
	/**
	 * 根据主键查询对象
	 * @param entity 对象
	 * @return 返回一个对象或者null
	 */
	ModelType selectByPrimaryKey(ModelType entity);

	/**
	 * 删除对象
	 * @param entity 传入主键
	 * @return
	 */
	int deleteByPrimaryKey(ModelType entity);
	
	/**
	 * 更新对象资料
	 * @param entity 必须存在主键
	 * @return
	 */
	int updateByPrimaryKey(ModelType entity);
	
	/**
	 * 查询对象集合,不分页方法(建议限制条数)
	 * @param entity 对象信息
	 * @param paramMap 其他参数集合
	 * @return
	 */
	List<ModelType> selectByParams(
			@Param("model") ModelType entity,
			@Param("params") Map<String, Object> paramMap);
	
	/**
	 * 获取条数
	 * @param entity 对象信息, (不一定要用到,可以传空)
	 * @param paramMap 其他参数, (不一定要用到,可以传空)
	 * @return
	 */
	int selectCount(
			@Param("model") ModelType entity,
			@Param("params") Map<String, Object> paramMap);
	
	/**
	 * 查询对象集合,分页方法
	 * @param page 分页对象
	 * @param paramMap 其他参数
	 * @param orderBy  排序
	 * @return
	 */
	List<ModelType> selectByPage(
			@Param("page") SimplePage page,
			@Param("params") Map<String, Object> paramMap,
			@Param("orderByField") String orderByField);
	
}
