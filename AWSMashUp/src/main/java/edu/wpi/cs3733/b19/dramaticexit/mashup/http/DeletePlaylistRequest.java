package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class DeletePlaylistRequest {
	public String playlistName;
	
	public String getPlaylistName() {return playlistName;}
	
	public DeletePlaylistRequest(String name) {
		this.playlistName = name;
	}
	
	public DeletePlaylistRequest() {
	}
	
	public String toString() {
		return "Delete(" + playlistName + ")";
	}

}
