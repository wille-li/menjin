package com.base.service;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.base.entity.SimplePage;
import com.base.mapper.BaseCrudMapper;

/**
 * Service 基础类实现方法
 * @author wille
 *
 * @param <ModelType>泛型
 */
public abstract class BaseServiceImpl<ModelType> implements BaseService<ModelType> {

	private BaseCrudMapper<ModelType> mapper;
	
	@PostConstruct
	private void initConfig(){
		mapper = init();
	}
	
	public abstract BaseCrudMapper<ModelType> init();
	
	@Override
	public int deleteById(ModelType entity) {
		return mapper.deleteByPrimaryKey(entity);
	}

	@Override
	public int add(ModelType entity) {
		return mapper.insert(entity);
	}

	@Override
	public int modifyById(ModelType entity) {
		return mapper.updateByPrimaryKey(entity);
	}

	@Override
	public ModelType findById(ModelType entity) {
		return mapper.selectByPrimaryKey(entity);
	}

	@Override
	public List<ModelType> findByPage(SimplePage page, Map<String, Object> params, String orderBy) {
		// TODO Auto-generated method stub
		return this.mapper.selectByPage(page, params, orderBy);
	}

	@Override
	public List<ModelType> findByParams(ModelType entity, Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return this.mapper.selectByParams(entity, paramMap);
	}

	@Override
	public int findCount(ModelType entity, Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return this.mapper.selectCount(entity, paramMap);
	}

}
