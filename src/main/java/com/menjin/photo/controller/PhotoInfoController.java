package com.menjin.photo.controller;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.base.annotation.log.SystemControllerLog;
import com.base.entity.ReturnInfo;
import com.menjin.api.controller.APIController;
import com.menjin.photo.model.PhotoInfo;
import com.menjin.photo.service.PhotoInfoService;

@Controller
public class PhotoInfoController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${photo.path}")
	private String imagePath;
	
	@Autowired
	private PhotoInfoService photoInfoService;
	
	@RequestMapping(value="/api/upload.do", method = RequestMethod.POST)
	@SystemControllerLog
	@ResponseBody
	private Map<String, Object> uploadFile(HttpServletRequest request, @RequestParam("file") MultipartFile file){
		Map <String, Object> result = new HashMap<>();
		ReturnInfo returnInfo = new ReturnInfo();
		// 文件大小判断
		String filename = Calendar.getInstance().getTimeInMillis() + ".jpg";
		File tmpFile=new File(imagePath+filename); 
		logger.info(imagePath + filename);
        if(filename!=null&&!file.isEmpty()){  
            try {  
                FileCopyUtils.copy(file.getBytes(), tmpFile); 
                savePhotoInfo(filename, imagePath, file.getBytes().length + 0L);
                System.out.println("上传成功"); 
                returnInfo.setRet(APIController.SUCCESS);
                returnInfo.setMsg("上传图片成功。");
                result.put("fileName", filename);
            } catch (IOException e) {  
                logger.error("Upload file error.", e); 
                returnInfo.setRet(APIController.FAIL);
                returnInfo.setMsg("上传图片失败。");
            }  
        } 
        result.put(APIController.HEAD_KEY, returnInfo);
        return result;
	}
	
	private void savePhotoInfo(String fileName, String filePath, Long size){
		PhotoInfo photoInfo = new PhotoInfo();
		photoInfo.setName(fileName);
		photoInfo.setPath(filePath);
		photoInfo.setSize(size);
		photoInfo.setCreateBy("Wille");
		photoInfo.setCreateDate(Calendar.getInstance().getTime());
		photoInfoService.add(photoInfo);
	}
}
