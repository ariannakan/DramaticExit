package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class CreatePlaylistRequest {
	public String playlistID;
	public String playlistName;
	public boolean system;
	
	public String getPlaylist() { return playlistID; }
	
	public boolean getSystem( ) { return system; }
	public void setSystem(boolean system) { this.system = system; }
	
	public CreatePlaylistRequest(String playlistID, String playlistName, String encoding) {
		this.playlistID = playlistID; 
		this.playlistName = playlistName;
	}
	
	public CreatePlaylistRequest(String playlistID, String playlistName, String encoding, boolean system) {
		this.playlistID = playlistID; 
		this.playlistName = playlistName;
		this.system = system;
	}
	
	public String toString() {
		return "CreatePlaylist(" + playlistID + "," + playlistName + "," + base64EncodedValue + ")";
	}
}
