package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class RegisterSiteRequest {
	public String url;
	public boolean system;
	
	public String getSiteURL() {return url;}
	public void setSiteURL(String url) {this.url = url;}
	
	public RegisterSiteRequest() {
		
	}
	
	public RegisterSiteRequest(String url) {
		this.url = url;
	}
	
	
	public String toString() {
		return "Register(" + url + ")";
	}

}
