package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class RegisterSiteResponse {
	public String siteURL;
	public int statusCode;
	public String error;
	
	public RegisterSiteResponse(String url, int code) {
		this.siteURL = url;
		this.statusCode = code;
		this.error = "";
	}
	
	// 200 = success
	public RegisterSiteResponse(String url, int code, String errorMessage) {
		this.siteURL = url;
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if(statusCode == 200) {
			return "Site(" + siteURL + ")registered!";
		} else {
			return "ErrorResult(" + siteURL + ", statusCode=" + statusCode + ", err=" + error + ")";
		}
	}

}
