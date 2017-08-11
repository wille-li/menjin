package com.menjin.machine.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.annotation.log.SystemControllerLog;
import com.base.entity.ReturnInfo;
import com.base.entity.SimplePage;
import com.base.util.UserSessionUtil;
import com.menjin.machine.service.MachineService;
import com.menjin.machine.model.Machine;

@Controller
public class MachineController {

    public final static int SUCCESS = 0;
	
	public final static int FAIL = 1;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MachineService machineService;
	
	@RequestMapping(value="/machine.do")
	@SystemControllerLog
	public String machineManage(){
		return "machine/machineManage";
	}
	
	@RequestMapping(value="/addMachine.do")
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> addMachine(@ModelAttribute Machine machine,
			HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to insert new Machine!Machine Name:"+machine.getName());
		Map<String, Object> returnMap = new HashMap<String, Object>();
		ReturnInfo rInfo = new ReturnInfo();
		String username = UserSessionUtil.getCurrentUsername(request);
		machine.setCreateBy(username);//根据现在操作用户修改
		machine.setCreateTime(new Date());
		int returnCode = machineService.add(machine);
		if(returnCode > SUCCESS){
			rInfo.setMsg("终端数据添加成功!");
			rInfo.setRet(SUCCESS);
		}else{
			rInfo.setMsg("终端数据添加失败,请重试！");
			rInfo.setRet(FAIL);
		}
		returnMap.put("rInfo", rInfo);
		logger.info("End to Insert Machine!ReturnCode:"+returnCode);
		return returnMap;
	}
	
	@RequestMapping(value="/updateMachine.do")
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> updateMachine(@ModelAttribute Machine machine,HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to update machine!");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		ReturnInfo rInfo = new ReturnInfo();
		machine.setCreateTime(new Date());
		String username = UserSessionUtil.getCurrentUsername(request);
		machine.setCreateBy(username);
		int returnCode = machineService.modifyById(machine);
		if(returnCode > SUCCESS){
			rInfo.setMsg("更新终端数据成功!");
			rInfo.setRet(SUCCESS);
		}else{
			rInfo.setMsg("更新终端数据失败,请重试!");
			rInfo.setRet(FAIL);
		}
		returnMap.put("rInfo", rInfo);
		logger.info("End to update machine!ReturnCode:"+returnCode);
		return returnMap;
	}
	
	@RequestMapping(value="/deleteMachine.do")
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> deleteMachine(@ModelAttribute Machine Machine,HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to delete Machine!");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		ReturnInfo rInfo = new ReturnInfo();
		int returnCode = machineService.deleteById(Machine);
		if(returnCode > SUCCESS){
			rInfo.setMsg("刪除终端数据成功!");
			rInfo.setRet(SUCCESS);
		}else{
			rInfo.setMsg("刪除终端数据失败,请重试!");
			rInfo.setRet(FAIL);
		}
		returnMap.put("rInfo", rInfo);
		logger.info("End to delete Machine。RetrunCode:"+returnCode);
		return returnMap;
	}
	
	@RequestMapping(value="/machinelist.do")
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> getAllMachineList(@Param(value="searchName")String searchName, HttpServletRequest request){
		logger.info("Start to getAllMachineList");
		Integer page = Integer.parseInt(request.getParameter("start"));
        System.out.println(page);
        Integer rows = Integer.parseInt(request.getParameter("length"));
        System.out.println(rows);
        String draw = request.getParameter("draw") == null ? "0" : request.getParameter("draw") + 1;
		int count = machineService.findCount(null, null);
		logger.info("Machine Count:"+count);
		SimplePage simplepage = new SimplePage(page/rows+1, rows, count);
		Map<String, Object> params = new HashMap<String, Object>();
		if(searchName != null && !searchName.equals("")){
			params.put("searchName", searchName);
		}
		String orderBy = null;
		logger.info("page="+page+", rows="+rows);
		List<Machine> machines = machineService.findByPage(simplepage, params, orderBy);
		Map<String, Object> maps = new HashMap<>();
		maps.put("data", machines);
		maps.put("draw", draw);
		maps.put("recordsTotal", count);
		maps.put("recordsFiltered", count);
		logger.info("End getAllMachineList");
		return maps;
	}
	
}
