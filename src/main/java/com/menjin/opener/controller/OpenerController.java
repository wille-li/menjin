package com.menjin.opener.controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.annotation.log.SystemControllerLog;
import com.base.entity.ReturnInfo;
import com.base.entity.SimplePage;
import com.menjin.opener.model.Opener;
import com.menjin.opener.service.OpenerService;

@Controller
public class OpenerController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
    public final static int SUCCESS = 0;
	
	public final static int FAIL = 1;
	
	@Value("${opener.ip}")
	private String openerIP = "192.168.1.106";
	
	@Value("${opener.port}")
	private Integer openerPort = 5000;
	
	@Autowired
	private OpenerService openerService;
	
	@RequestMapping(value="/openerDoor.do")
	@ResponseBody
	public Map<String, Object> openDoor(Opener opener) {
		logger.info("Start to update opener!");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		ReturnInfo rInfo = new ReturnInfo();
		Opener temOpen = openerService.findById(opener);
		if (null == temOpen) {
			rInfo.setMsg("闸机数据添加失败,请重试！");
			rInfo.setRet(FAIL);
			returnMap.put("rInfo", rInfo);
			return returnMap;
		}
		
		checkOpenerStatus("on1", temOpen.getIp(), temOpen.getPort());
		
		return returnMap;
	}
	
	private SocketChannel createSocketChannel(String hostName, int port) throws IOException{
		SocketChannel sChannel = SocketChannel.open();
		sChannel.configureBlocking(true);
		boolean connection = sChannel.connect(new InetSocketAddress(hostName, port));
		
		if (connection){
			System.out.println("Connect OK..");
		} else {
			System.out.println("Fail connection..");
		}
		return sChannel;
	}
	
	@RequestMapping(value="/opener.do")
	@SystemControllerLog
	public String openerManage(){
		return "opener/openerManage";
	}
	
	@RequestMapping(value="/opener1.do")
	@ResponseBody
	public String checkOpenerStatus(String sendContent){
		return checkOpenerStatus(sendContent, openerIP, openerPort);
	}
	
	private String checkOpenerStatus(String sendContent, String openerIP, Integer openerPort){
		StringBuffer resultBF = new StringBuffer();
		ByteBuffer buf = ByteBuffer.allocate(1024);
		try {
			buf.clear();
			
			SocketChannel channel = createSocketChannel(openerIP, openerPort);
			if (channel.isConnected()){
				System.out.println("Connected");
			}
			
			int result = -1;
			buf.clear();
			
			channel.read(buf);
			buf.flip();
			while (buf.remaining() > 0){
				System.out.println((char)buf.get());
			}
			buf.clear();
			buf.put(sendContent.getBytes());
			buf.flip();
			result = channel.write(buf);
			if (result > 0){
				System.out.println("已经写入" + result + "个字节");
			}
			buf.clear();
			channel.read(buf);
			buf.flip();
			
			while (buf.remaining() > 0){
				resultBF.append((char)buf.get());
			}
			buf.clear();
			channel.close();
			
		} catch (UnknownHostException e) {
			logger.error("Connect opener error", e);
			resultBF.append("UnknownHosterror");
		} catch (IOException e) {
			logger.error("Connect opener error", e);
			resultBF.append("IOerror");
		}
		return resultBF.toString();
	}
	
	@RequestMapping(value="/addOpener.do")
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> addOpener(@ModelAttribute Opener opener,
			HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to insert new Opener!Opener Name:"+opener.getName());
		Map<String, Object> returnMap = new HashMap<String, Object>();
		ReturnInfo rInfo = new ReturnInfo();
		opener.setCreateBy("Admin");//根据现在操作用户修改
		opener.setCreateTime(new Date());
		int returnCode = openerService.add(opener);
		if(returnCode > SUCCESS){
			rInfo.setMsg("闸机数据添加成功!");
			rInfo.setRet(SUCCESS);
		}else{
			rInfo.setMsg("闸机数据添加失败,请重试！");
			rInfo.setRet(FAIL);
		}
		returnMap.put("rInfo", rInfo);
		logger.info("End to Insert Opener!ReturnCode:"+returnCode);
		return returnMap;
	}
	
	@RequestMapping(value="/updateOpener.do")
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> updateOpener(@ModelAttribute Opener opener,HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to update opener!");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		ReturnInfo rInfo = new ReturnInfo();
		opener.setCreateTime(new Date());
		opener.setCreateBy("Admin");
		int returnCode = openerService.modifyById(opener);
		if(returnCode > SUCCESS){
			rInfo.setMsg("更新闸机数据成功!");
			rInfo.setRet(SUCCESS);
		}else{
			rInfo.setMsg("更新闸机数据失败,请重试!");
			rInfo.setRet(FAIL);
		}
		returnMap.put("rInfo", rInfo);
		logger.info("End to update opener!ReturnCode:"+returnCode);
		return returnMap;
	}
	
	@RequestMapping(value="/deleteOpener.do")
	@SystemControllerLog
	@ResponseBody
	public Map<String, Object> deleteOpener(@ModelAttribute Opener Opener,HttpServletRequest request,HttpServletResponse response){
		logger.info("Start to delete Opener!");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		ReturnInfo rInfo = new ReturnInfo();
		int returnCode = openerService.deleteById(Opener);
		if(returnCode > SUCCESS){
			rInfo.setMsg("刪除闸机数据成功!");
			rInfo.setRet(SUCCESS);
		}else{
			rInfo.setMsg("刪除闸机数据失败,请重试!");
			rInfo.setRet(FAIL);
		}
		returnMap.put("rInfo", rInfo);
		logger.info("End to delete Opener。RetrunCode:"+returnCode);
		return returnMap;
	}
	
	@RequestMapping(value="/openerlist.do")
	@SystemControllerLog
	@ResponseBody
	public Map getAllOpenerList(@Param(value="searchName")String searchName, HttpServletRequest request){
		logger.info("Start to getAllOpenerList");
		Integer page = Integer.parseInt(request.getParameter("start"));
        System.out.println(page);
        Integer rows = Integer.parseInt(request.getParameter("length"));
        System.out.println(rows);
        String draw = request.getParameter("draw") == null ? "0" : request.getParameter("draw") + 1;
		int count = openerService.findCount(null, null);
		logger.info("Opener Count:"+count);
		SimplePage simplepage = new SimplePage(page/rows+1, rows, count);
		Map<String, Object> params = new HashMap<String, Object>();
		if(searchName != null && !searchName.equals("")){
			params.put("searchName", searchName);
		}
		String orderBy = null;
		logger.info("page="+page+", rows="+rows);
		List<Opener> openers = openerService.findByPage(simplepage, params, orderBy);
		Map maps = new HashMap();
		maps.put("data", openers);
		maps.put("draw", draw);
		maps.put("recordsTotal", count);
		maps.put("recordsFiltered", count);
		logger.info("End getAllOpenerList");
		return maps;
	}
	
	@RequestMapping(value="/allOpener.do")
	@SystemControllerLog
	@ResponseBody
	public List<Opener> getAll(){
		return openerService.findByParams(null, null);
	}
	
	
	public static void main(String[] args){
		OpenerController open = new OpenerController();
		
		open.checkOpenerStatus("on1");
	}
}
