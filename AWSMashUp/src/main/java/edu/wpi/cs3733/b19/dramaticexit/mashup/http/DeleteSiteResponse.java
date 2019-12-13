package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class DeleteSiteResponse {
	public String siteURL;
	public int statusCode;
	public String error;
	
	public DeleteSiteResponse(String url , int code) {
		this.siteURL = url;
		this.statusCode = code;
		this.error = "";
	}
	
	// 403 response
	public DeleteSiteResponse(String url, int code, String errorMessage) {
		this.siteURL = url;
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if(statusCode == 200) {
			return "DeleteSite(" + siteURL + ") statusCode: " + statusCode;
		} else {
			return "ErrorResult(" + siteURL + ", statusCode=" + statusCode + ", err=" + error + ")";
		}
	}

}
