package com.base.service;

import javax.annotation.PostConstruct;

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

}
