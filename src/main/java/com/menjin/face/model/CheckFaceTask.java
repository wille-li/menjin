package com.menjin.face.model;

import java.util.concurrent.Callable;

import org.bytedeco.javacv.FrameGrabber;

import com.menjin.face.service.FaceClient;

public class CheckFaceTask implements Callable<FaceInfo>{

	private String filePath;
	
	private String groups;
	
	private FaceClient faceClient;
	
	private FrameGrabber frameGrabber;
	
	public CheckFaceTask(String filePath, String groups, FaceClient faceClient, FrameGrabber frameGrabber){
		this.filePath = filePath;
		this.groups = groups;
		this.faceClient = faceClient;
		this.frameGrabber = frameGrabber;
	}
	
	@Override
	public FaceInfo call() throws Exception {
		FaceInfo faceInfo = faceClient.identifyUser(filePath, groups);
		faceInfo.setFrameGrabber(frameGrabber);
		return faceInfo;
	}

}
