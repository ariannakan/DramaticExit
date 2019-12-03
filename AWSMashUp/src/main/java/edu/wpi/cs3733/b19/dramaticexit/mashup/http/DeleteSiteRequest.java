package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class DeleteSiteRequest {
	public String siteURL;
	
	public void setSiteURL(String url) {this.siteURL = url;}
	public String getSiteURL() {return this.siteURL;}
	
	public DeleteSiteRequest() {
		
	}
	
	public DeleteSiteRequest(String url) {
		this.siteURL = url;
	}
	
	public String toString() {
		return "Delete(" + siteURL + ")";
	}

}
