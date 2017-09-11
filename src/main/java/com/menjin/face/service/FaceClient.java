package com.menjin.face.service;

import java.util.Arrays;
import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.baidu.aip.face.AipFace;
import com.menjin.face.model.FaceInfo;

@Component("faceClient")
public class FaceClient {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${app.id}")
	private String appID;
	
	@Value("${api.key}")
	private String apiKey;
	
	@Value("${secret.key}")
	private String token;
	
	@Value("${connect.timeout}")
	private Integer connectTimeout;
	
	@Value("${socket.timeout}")
	private Integer socketTimeout;
	
	private AipFace aipFace;
	
	@PostConstruct
	private void initFaceClient(){
		aipFace = new AipFace(appID, apiKey, token);
		aipFace.setConnectionTimeoutInMillis(connectTimeout);
		aipFace.setSocketTimeoutInMillis(socketTimeout);
	}
	
	public Boolean registerFace(String photoPath, String userID, String faceGroup, String name){
		HashMap<String, String> options = new HashMap<String, String>();
	    JSONObject res = aipFace.addUser(userID, name, Arrays.
	    		asList(faceGroup.split(",")), photoPath, options);
	    logger.info(res.toString(2));
	    return true;
	}
	
	public FaceInfo identifyUser(String facePath, String userGroup) {
		HashMap<String, Object> options = new HashMap<String, Object>(1);
	    options.put("user_top_num", 1);
	    JSONObject res = aipFace.identifyUser(Arrays.asList(userGroup.split(",")),
	    		facePath, options);
	    FaceInfo faceInfo = getFaceInfo(res);
		return faceInfo;
	}
	
	private static FaceInfo getFaceInfo(JSONObject res) {
		try {
			JSONArray array = res.getJSONArray("result"); 
			JSONObject resultObj = array.getJSONObject(0);
			array = resultObj.getJSONArray("scores");
			Double scope = array.getDouble(0);
			String id = resultObj.getString("uid");
			String userInfo = resultObj.getString("user_info");
			return new FaceInfo(scope, id, userInfo);
		} catch (org.json.JSONException e) {
			e.printStackTrace();
			return new FaceInfo(0.0, "-1", "没有改用户");
		} 
	}
	
}
