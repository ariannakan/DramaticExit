package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class DeleteVideoResponse {
	public String videoID;
	public int statusCode;
	public String error;
	
	public DeleteVideoResponse(String id, int code) {
		this.videoID = id;
		this.statusCode = code;
		this.error = "";
	}
	
	// 200 = success
	public DeleteVideoResponse(String id, int code, String errorMessage) {
		this.videoID = id;
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if(statusCode == 200) {
			return "DeleteResponse(" + videoID + ")";
		} else {
			return "ErrorResult(" + videoID + ", statusCode = " + statusCode + ", err=" + error + ")";
		}
	}

}
