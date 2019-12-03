package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class ListPlaylistVideosRequest {
	
	public String playlistName;
	
	public String getPlaylist() {
		return this.playlistName;
	}
	
	public ListPlaylistVideosRequest (String playlistName) {
		this.playlistName = playlistName;
	}
	
	public ListPlaylistVideosRequest() { }
	
	public String toString() {
		return "List()";
	}

}
