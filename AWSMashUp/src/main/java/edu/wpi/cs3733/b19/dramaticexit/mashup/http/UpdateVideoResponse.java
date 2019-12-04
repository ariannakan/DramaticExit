package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class UpdateVideoResponse {
	
	public String videoID;
	public boolean availability;
	int statusCode;
	String errorMsg;
	
	// success - 200
	public UpdateVideoResponse(String videoID, boolean availability) {
		this.videoID = videoID;
		this.availability = availability;
		this.statusCode = 200;
	}
	
	public UpdateVideoResponse(String videoID, boolean availability, int statusCode, String errorMsg) {
		this.videoID = videoID;
		this.availability = availability;
		this.statusCode = statusCode;
		this.errorMsg = errorMsg;
	}
	
	public UpdateVideoResponse(String errorMsg, int statusCode) {
		this.errorMsg = errorMsg;
		this.statusCode = statusCode;
	}
	
	public String toString() {
		if(statusCode == 200) {
			return "UpdateResponse(" + videoID + ", " + availability + ")" + "statusCode = " + statusCode;
		} else {
			return "ErrorResult(" + videoID + ", " + availability + ", statusCode = " + statusCode + ", err=" + errorMsg + ")";
		}
	}

}
