package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class CreatePlaylistResponse {
	public final String playlistID;
	public final int statusCode;
	public String error;
	
	public CreatePlaylistResponse(String playlistID, int statusCode) {
		this.playlistID = playlistID;
		this.statusCode = statusCode;
	}
	
	// 200 = success
	public CreatePlaylistResponse(String playlistID) {
		this.playlistID = playlistID;
		this.statusCode = 200;
	}
	
	public String toString() {
		if(statusCode == 200) {
			return "CreateResponse(" + playlistID + " has been uploaded successfully!)";
		} else {
			return "ErrorResult(" + playlistID + ", statusCode = " + statusCode + ", err=" + error + ")";
		}
	}

}
