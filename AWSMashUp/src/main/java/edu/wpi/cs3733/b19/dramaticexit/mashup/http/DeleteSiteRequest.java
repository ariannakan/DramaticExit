package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class DeleteSiteRequest {
	public String url;
	
	public void setSiteURL(String url) {this.url = url;}
	public String getSiteURL() {return this.url;}
	
	public DeleteSiteRequest() {
		
	}
	
	public DeleteSiteRequest(String url) {
		this.url = url;
	}
	
	public String toString() {
		return "Delete(" + url + ")";
	}

}
