package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class RemoveFromPlaylistRequest {
	public String playlistName;
	public String videoID;
	
	public RemoveFromPlaylistRequest(String playlistName, String videoID) {
		this.playlistName = playlistName;
		this.videoID = videoID;
	}
	
	public RemoveFromPlaylistRequest() {
		
	}
	
	public String getPlaylistName() { return playlistName; }
	
	public String getVideoID() { return videoID; }
	
	public String toString() {
		return "Remove " + videoID + " from " + playlistName;
	}

}
