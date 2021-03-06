package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class DeleteVideoResponse {
	public String videoID;
	public int statusCode;
	public String error;
	
	// 200 = success
	public DeleteVideoResponse(String videoID, int statusCode) {
		this.videoID = videoID;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public DeleteVideoResponse(String videoID, int statusCode, String errorMessage) {
		this.videoID = videoID;
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	public String toString() {
		if(statusCode == 200) {
			return "DeleteResponse(" + videoID + ") statusCode: " + statusCode;
		} else {
			return "ErrorResult(" + videoID + ", statusCode = " + statusCode + ", err=" + error + ")";
		}
	}

}
