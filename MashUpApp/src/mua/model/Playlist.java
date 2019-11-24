package mua.model;

import java.util.*;

public class Playlist {
	public final String playlistID;
	public final String playlistName;
	public boolean system;      // when TRUE this is actually stored in S3 bucket
	public ArrayList<String> videos = new ArrayList<String>(); 
    Iterator<String> iterator = videos.iterator(); //iterator to traverse the list 
	public String videoID;
	
	public Playlist (String id, String name) {
		this.playlistID = id;
		this.playlistName = name;
	}
	
	public Playlist (String id, String name, boolean system) {
		this.playlistID = id;
		this.playlistName = name;
		this.system = system;
	}
	
	public boolean getSystem() { return system; }
	public void setSystem(boolean s) { system = s; }
	
}

