package edu.wpi.cs3733.b19.dramaticexit.mashup.model;

public class Site {

	public String timestampID;
	public String url;
	
	public Site(String siteID, String url) {
		this.timestampID = siteID;
		this.url = url;
	}
	
	public Site(String siteID, String url, boolean system) {
		this.timestampID = siteID;
		this.url = url;
	}
	
	/**
	 * Equality of Sites determined by url alone.
	 */
	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Site) {
			Site other = (Site) o;
			return url.equals(other.url);
		}
		
		return false;  // not a Video
	}

}
