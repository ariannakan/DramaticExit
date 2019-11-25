package edu.wpi.cs3733.b19.dramaticexit.mashup.model;

public class Site {
<<<<<<< HEAD
=======
	
	public boolean system;	// when TRUE this is actually stored in S3 bucket
	public String url;
	
	public Site(String url) {
		this.url = url;
	}
	
	public Site(String url, boolean system) {
		this.url = url;
		this.system = system;
	}
	
	public boolean getSystem() { return system; }
	public void setSystem(boolean s) { system = s; }
	
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
	
>>>>>>> branch 'master' of https://github.com/ksnoddy/DramaticExit.git

}
