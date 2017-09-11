package com.menjin.photo.mapper;

import org.apache.ibatis.annotations.Param;

import com.base.mapper.BaseCrudMapper;
import com.menjin.photo.model.PhotoInfo;

public interface PhotoInfoMapper extends BaseCrudMapper<PhotoInfo>{

	public PhotoInfo selectByIDCard(@Param("idCard")String idCard);
}
