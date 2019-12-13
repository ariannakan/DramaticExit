package edu.wpi.cs3733.b19.dramaticexit.mashup.model;

public class Site {

	public String timestampID;
	public String url;
	public boolean system;
	
	public Site(String siteID, String url) {
		this.timestampID = siteID;
		this.url = url;
	}
	

}
