package edu.wpi.cs3733.b19.dramaticexit.mashup.model;

import java.util.ArrayList;
import java.util.*;

public class Playlist {
	public final String playlistID;
	public final String playlistName;
	public boolean system;      // when TRUE this is actually stored in S3 bucket
	public ArrayList<Video> videos = new ArrayList<Video>(); 
	Iterator<Video> iterator = videos.iterator(); //iterator to traverse the list 
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
	
}

