package mua.http;

import mua.model.Video;

public class AppendToPlaylistRequest {
	
	public String playlistID;
	public String videoID;
	public boolean system;
	
	public String getPlaylist() { return playlistID; }
	
	public String getVideo() { return videoID; }
	public void setVideo(String id) { this.videoID = id;	} 	//change to boolean
	
	public boolean getSystem( ) { return system; }
	public void setSystem(boolean system) { this.system = system; }
	
	public String toString() {
		return "Append " + videoID + " to " + playlistID;
	}

	public AppendToPlaylistRequest(String playlistID, String videoID) {
		this.playlistID = playlistID;
		this.videoID = videoID;
	}
	
	public AppendToPlaylistRequest() {	}

}
