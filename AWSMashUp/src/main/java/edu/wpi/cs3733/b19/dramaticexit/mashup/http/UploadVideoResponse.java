package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class UploadVideoResponse {
	public String response;
	public int statusCode;
	public String error;
	
	public UploadVideoResponse(String response, int statusCode) {
		this.response = response;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	// 200 = success
	public UploadVideoResponse(String response, int statusCode, String errorMessage) {
		this.response = response;
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	public String toString() {
		if(statusCode == 200) {
			return "UploadResponse(" + response + ")";
		} else {
			return "ErrorResult(" + response + ", statusCode = " + statusCode + ", err=" + error + ")";
		}
	}

}
