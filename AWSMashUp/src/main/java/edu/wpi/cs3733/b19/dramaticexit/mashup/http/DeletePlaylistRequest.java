package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class DeletePlaylistRequest {
	public String playlistID;
	
	public void setPlaylistID(String id) {this.playlistID = id;}
	public String getPlaylistID() {return playlistID;}
	
	public DeletePlaylistRequest(String id) {
		this.playlistID = id;
	}
	
	public DeletePlaylistRequest() {
	}
	
	public String toString() {
		return "Delete(" + playlistID + ")";
	}

}
