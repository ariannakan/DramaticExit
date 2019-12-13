package edu.wpi.cs3733.b19.dramaticexit.mashup.model;

public class Site {

	public String timestampID;
	public String url;
	public boolean system;
	
	
	public String getTimestampID() {
		return timestampID;
	}

	public void setTimestampID(String timestampID) {
		this.timestampID = timestampID;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Site(String siteID, String url) {
		this.timestampID = siteID;
		this.url = url;
	}
	
	public Site() {
		this.timestampID = "";
		this.url = "";
	}
	
	/**
	 * Equality of Sites determined by name alone.
	 */
	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Site) {
			Site other = (Site) o;
			return url.equals(other.url);
		}
		
		return false;  // not a Constant
	}
}
