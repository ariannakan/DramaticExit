package edu.wpi.cs3733.b19.dramaticexit.mashup.model;

import java.util.ArrayList;
import java.util.*;

public class Playlist {
	public String playlistID;
	public String playlistName;
	public boolean system;      // when TRUE this is actually stored in S3 bucket
	public ArrayList<Video> videos = new ArrayList<Video>(); 
	Iterator<Video> iterator = videos.iterator(); //iterator to traverse the list 
	public String videoID;
	
	
	
	public String getPlaylistID() {
		return playlistID;
	}
	public void setPlaylistID(String id) {
		playlistID = id;
	}

	public String getPlaylistName() {
		return playlistName;
	}
	public void setPlaylistName(String name) {
		playlistName = name;
	}

	public Playlist (String id, String name) {
		this.playlistID = id;
		this.playlistName = name;
	}
	
	public Playlist() {
		this.playlistID = "";
		this.playlistName = "";
	}
	
	/**
	 * Equality of Playlists determined by name alone.
	 */
	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Playlist) {
			Playlist other = (Playlist) o;
			return playlistName.equals(other.playlistName);
		}
		
		return false;  // not a Constant
	}
	
}

