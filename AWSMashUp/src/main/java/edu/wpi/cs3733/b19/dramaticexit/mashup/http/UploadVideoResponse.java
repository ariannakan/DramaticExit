package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class UploadVideoResponse {
	public String response;
	public int statusCode;
	
	public UploadVideoResponse(String r, int statusCode) {
		this.response = r;
		this.statusCode = statusCode;
	}
	
	// 200 = success
	public UploadVideoResponse(String r) {
		this.response = r;
		this.statusCode = 200;
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}

}
