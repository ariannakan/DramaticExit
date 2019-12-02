package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class RegisterSiteResponse {
	public String siteURL;
	public int statusCode;
	
	public RegisterSiteResponse(String url, int code) {
		this.siteURL = url;
		this.statusCode = code;
	}
	
	// 200 = success
	public RegisterSiteResponse(String url) {
		this.siteURL = url;
		this.statusCode = 200;
	}
	
	public String toString() {
		if(statusCode == 200) {
			return "Site(" + siteURL + ")registered!";
		} else {
			return "ErrorResult(" + siteURL + ", statusCode=" + statusCode + ")";
		}
	}

}
