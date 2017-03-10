package com.menjin.camera.controller;

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
import com.menjin.camera.model.Camera;
import com.menjin.camera.service.CameraService;

@Controller
public class CameraController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
    public final static int SUCCESS = 0;
	
	public final static int FAIL = 1;
	
	@Autowired
	private CameraService cameraService;
	
	@RequestMapping(value="/camera.do")
	@SystemControllerLog
	public String cameraManage(){
		return "camera/cameraManage";
	}
	

	@RequestMapping(value="/addCamera.do")
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> addCamera(@ModelAttribute Camera camera,
			HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to insert new camera!camera Name:"+camera.getName());
		Map<String, Object> returnMap = new HashMap<String, Object>();
		ReturnInfo rInfo = new ReturnInfo();
		camera.setCreateBy("Admin");//根据现在操作用户修改
		camera.setCreateTime(new Date());
		int returnCode = cameraService.add(camera);
		if(returnCode > SUCCESS){
			rInfo.setMsg("摄像头数据添加成功!");
			rInfo.setRet(SUCCESS);
		}else{
			rInfo.setMsg("摄像头数据添加失败,请重试！");
			rInfo.setRet(FAIL);
		}
		returnMap.put("rInfo", rInfo);
		logger.info("End to Insert camera!ReturnCode:"+returnCode);
		return returnMap;
	}
	
	@RequestMapping(value="/updateCamera.do")
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> updateCamera(@ModelAttribute Camera camera,HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to update camera!");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		ReturnInfo rInfo = new ReturnInfo();
		camera.setCreateTime(new Date());
		camera.setCreateBy("Admin");
		int returnCode = cameraService.modifyById(camera);
		if(returnCode > SUCCESS){
			rInfo.setMsg("更新摄像头数据成功!");
			rInfo.setRet(SUCCESS);
		}else{
			rInfo.setMsg("更新摄像头数据失败,请重试!");
			rInfo.setRet(FAIL);
		}
		returnMap.put("rInfo", rInfo);
		logger.info("End to update camera!ReturnCode:"+returnCode);
		return returnMap;
	}
	
	@RequestMapping(value="/deleteCamera.do")
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> deleteCamera(@ModelAttribute Camera camera,HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to delete camera!");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		ReturnInfo rInfo = new ReturnInfo();
		int returnCode = cameraService.deleteById(camera);
		if(returnCode > SUCCESS){
			rInfo.setMsg("刪除摄像头数据成功!");
			rInfo.setRet(SUCCESS);
		}else{
			rInfo.setMsg("刪除摄像头数据失败,请重试!");
			rInfo.setRet(FAIL);
		}
		returnMap.put("rInfo", rInfo);
		logger.info("End to delete camera。RetrunCode:"+returnCode);
		return returnMap;
	}
	
	@RequestMapping(value="/cameralist.do")
	@SystemControllerLog
	@ResponseBody
	public Map getAllCameraList(@Param(value="searchName")String searchName, HttpServletRequest request){
		logger.info("Start to getAllCameraList");
		Integer page = Integer.parseInt(request.getParameter("start"));
        System.out.println(page);
        Integer rows = Integer.parseInt(request.getParameter("length"));
        System.out.println(rows);
        String draw = request.getParameter("draw") == null ? "0" : request.getParameter("draw") + 1;
		int count = cameraService.findCount(null, null);
		logger.info("Opener Count:"+count);
		SimplePage simplepage = new SimplePage(page/rows+1, rows, count);
		Map<String, Object> params = new HashMap<String, Object>();
		if(searchName != null && !searchName.equals("")){
			params.put("searchName", searchName);
		}
		String orderBy = null;
		logger.info("page="+page+", rows="+rows);
		List<Camera> cameras = cameraService.findByPage(simplepage, params, orderBy);
		Map maps = new HashMap();
		maps.put("data", cameras);
		maps.put("draw", draw);
		maps.put("recordsTotal", count);
		maps.put("recordsFiltered", count);
		logger.info("End getAllCameraList");
		return maps;
	}
}
