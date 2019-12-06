package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class CreatePlaylistRequest {
	public String playlistName;
	public boolean system;
	
	
	public String getPlaylistName() { return playlistName; }
	public void setPlaylistName(String name) { this.playlistName = name;}
	
	
	public boolean getSystem( ) { return system; }
	public void setSystem(boolean system) { this.system = system; }
	
	public CreatePlaylistRequest() { }
	
	public CreatePlaylistRequest(String playlistName) {
		this.playlistName = playlistName;
	}
	
	public CreatePlaylistRequest(String playlistName, boolean system) {
		this.playlistName = playlistName;
		this.system = system;
	}
	
	public String toString() {
		return "CreatePlaylist(" + playlistName + ")";
	}
}
