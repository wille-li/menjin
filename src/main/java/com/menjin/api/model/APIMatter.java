package com.menjin.api.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.menjin.visit.model.Matter;

public class APIMatter extends Matter {
	
	public APIMatter(Matter matter){
		this.setId(matter.getId());
		this.setMatterDecs(matter.getMatterDecs());
	}

	@Override
	@JsonIgnore
	public String getCreateBy() {
		// TODO Auto-generated method stub
		return super.getCreateBy();
	}

	@Override
	@JsonIgnore
	public Date getModifiedDate() {
		// TODO Auto-generated method stub
		return super.getModifiedDate();
	}

	@Override
	@JsonIgnore
	public Date getCreateTime() {
		// TODO Auto-generated method stub
		return super.getCreateTime();
	}

	

}
