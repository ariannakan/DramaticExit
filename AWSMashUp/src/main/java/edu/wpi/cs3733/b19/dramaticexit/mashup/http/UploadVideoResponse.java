package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Video;

public class UploadVideoResponse {
	public Video videoSegment;
	public int statusCode;
	public String error;
	
	public UploadVideoResponse(Video video, int code) {
		this.videoSegment = video;
		this.statusCode = code;
		this.error = "";
	}
	
	// 200 = success
	public UploadVideoResponse(Video video, int code, String errorMessage) {
		this.videoSegment = video;
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if(statusCode == 200) {
			return "UploadResponse(" + videoSegment + ")";
		} else {
			return "ErrorResult(" + videoSegment + ", statusCode = " + statusCode + ", err=" + error + ")";
		}
	}

}
