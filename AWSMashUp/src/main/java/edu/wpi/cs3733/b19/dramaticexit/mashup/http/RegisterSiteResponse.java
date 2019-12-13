package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class RegisterSiteResponse {
	public String url;
	public int statusCode;
	
	public RegisterSiteResponse(String url, int code) {
		this.url = url;
		this.statusCode = code;
	}
	
	// 200 = success
	public RegisterSiteResponse(String url) {
		this.url = url;
		this.statusCode = 200;
	}
	
	public String toString() {
		if(statusCode == 200) {
			return "Site(" + url + ") registered! StatusCode: " + statusCode;
		} else {
			return "ErrorResult(" + url + ", statusCode=" + statusCode + ")";
		}
	}

}


