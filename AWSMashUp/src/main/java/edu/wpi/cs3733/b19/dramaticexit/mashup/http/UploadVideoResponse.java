package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class UploadVideoResponse {
	public String videoID;
	public int statusCode;
	public String error;
	
	public UploadVideoResponse(String videoID, int statusCode) {
		this.videoID = videoID;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	// 200 = success
	public UploadVideoResponse(String videoID, int statusCode, String errorMessage) {
		this.videoID = videoID;
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	public String toString() {
		if(statusCode == 200) {
			return "UploadResponse(" + videoID + ")";
		} else {
			return "ErrorResult(" + videoID + ", statusCode = " + statusCode + ", err=" + error + ")";
		}
	}

}
