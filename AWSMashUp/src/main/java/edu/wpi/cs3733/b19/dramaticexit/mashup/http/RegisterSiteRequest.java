package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class RegisterSiteRequest {
	String siteURL;
	
	public String getSiteURL() {return siteURL;}
	public void setSiteURL(String url) {this.siteURL = url;}
	
	public RegisterSiteRequest() {
		
	}
	
	public RegisterSiteRequest(String url) {
		this.siteURL = url;
	}
	
	
	public String toString() {
		return "Register(" + siteURL + ")";
	}

}
