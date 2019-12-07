package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Video;

public class AppendToPlaylistRequest {
	
	public String playlistName;
	public String videoID;
	public boolean system;
	
	public String getPlaylistID() { return playlistName; }
	
	public String getVideoID() { return videoID; }
	public void setVideoID(String id) { this.videoID = id;	} 	//change to boolean
	
	public boolean getSystem( ) { return system; }
	public void setSystem(boolean system) { this.system = system; }
	
	public String toString() {
		return "Append " + videoID + " to " + playlistName;
	}

	public AppendToPlaylistRequest(String playlistID, String videoID) {
		this.playlistName = playlistID;
		this.videoID = videoID;
	}
	
	public AppendToPlaylistRequest() {	}

}
