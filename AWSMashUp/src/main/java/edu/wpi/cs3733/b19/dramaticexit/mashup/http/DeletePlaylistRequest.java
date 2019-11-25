package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class DeletePlaylistRequest {
	public String playlistID;
	
	public void setPlaylist(String playlistID) {this.playlistID = playlistID;}
	public String getPlaylist() {return playlistID;}
	
	public DeletePlaylistRequest() {
		
	}
	
	public String toString() {
		return "Delete(" + playlistID + ")";
	}

}
