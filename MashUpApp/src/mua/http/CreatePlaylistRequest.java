package mua.http;

public class CreatePlaylistRequest {
	public String playlistID;
	public String playlistName;
	public String base64EncodedValue;
	public boolean system;
	
	public String getPlaylist() { return playlistID; }
	
	public boolean getSystem( ) { return system; }
	public void setSystem(boolean system) { this.system = system; }
	
	public String getBase64EncodedValue() { return base64EncodedValue; }
	public void setBase64EncodedValue(String base64EncodedValue) { this.base64EncodedValue = base64EncodedValue; }
	
	public CreatePlaylistRequest(String playlistID, String playlistName, String encoding) {
		this.playlistID = playlistID; 
		this.playlistName = playlistName;
		this.base64EncodedValue = encoding;
	}
	
	public CreatePlaylistRequest(String playlistID, String playlistName, String encoding, boolean system) {
		this.playlistID = playlistID; 
		this.playlistName = playlistName;
		this.base64EncodedValue = encoding;
		this.system = system;
	}
	
	public String toString() {
		return "UploadPlaylist(" + playlistID + "," + playlistName + "," + base64EncodedValue + ")";
	}
}
