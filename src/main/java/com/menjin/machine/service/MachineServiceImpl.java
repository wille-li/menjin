package com.menjin.machine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.mapper.BaseCrudMapper;
import com.base.service.BaseServiceImpl;
import com.menjin.machine.mapper.MachineMapper;
import com.menjin.machine.model.Machine;

@Service
public class MachineServiceImpl extends BaseServiceImpl<Machine> implements MachineService{

	@Autowired
	MachineMapper machineMapper;
	
	@Override
	public BaseCrudMapper<Machine> init() {
		return this.machineMapper;
	}

}
