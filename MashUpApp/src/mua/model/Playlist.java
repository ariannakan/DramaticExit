package mua.model;

public class Playlist {
	public final String id;
	public final String name;
	public boolean system;      // when TRUE this is actually stored in S3 bucket
	public ArrayList<String> videos = new ArrayList<String>(); 
    Iterator iterator = videos.iterator(); //iterator to traverse the list 
	
	public Playlist (String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Playlist (String id, String name, boolean system) {
		this.id = id;
		this.name = name;
		this.sentence = sentence;
		this.system = system;
	}
	
	public boolean getSystem() { return system; }
	public void setSystem(boolean s) { system = s; }
	
	public boolean addVideo(){}
	
	public boolean remVideo(){}
	
	//public void showVideo() {}
	/**
	 * Equality of Constants determined by name alone.
	 */
	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Video) {
			Video other = (Video) o;
			return id.equals(other.id);
		}
		
		return false;  // not a Constant
	}
}

