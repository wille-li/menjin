package com.menjin.face.model;

import org.bytedeco.javacv.FrameGrabber;

public class FaceInfo {
	
	private Double scope;
	
	private String id;
	
	private String message;
	
	private FrameGrabber frameGrabber;

	public FrameGrabber getFrameGrabber() {
		return frameGrabber;
	}

	public void setFrameGrabber(FrameGrabber frameGrabber) {
		this.frameGrabber = frameGrabber;
	}

	public Double getScope() {
		return scope;
	}

	public void setScope(Double scope) {
		this.scope = scope;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public FaceInfo(Double scope, String id, String message) {
		super();
		this.scope = scope;
		this.id = id;
		this.message = message;
	}

	@Override
	public String toString() {
		return "FaceInfo [scope=" + scope + ", id=" + id + ", message=" + message + "]";
	}
	
}
