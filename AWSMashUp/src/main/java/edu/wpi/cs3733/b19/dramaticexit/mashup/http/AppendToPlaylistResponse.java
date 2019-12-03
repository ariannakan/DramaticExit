package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class AppendToPlaylistResponse {

	public String playlistID;
	public String videoID;
	public int statusCode;
	public String error;
	
	// success - 200
	public AppendToPlaylistResponse (String playlistID, String videoID) {
		this.playlistID = playlistID; 
		this.videoID = videoID;
		this.statusCode = 200;
	}
	
	public AppendToPlaylistResponse (String playlistID, String videoID, int statusCode, String errorMessage) {
		this.playlistID = playlistID; 
		this.videoID = videoID;
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	public AppendToPlaylistResponse(String errorMessage, int statusCode) {
		this.error = errorMessage;
		this.statusCode = statusCode;
	}

	public String toString() {
		if (statusCode == 200) {  
			return "Result(" + playlistID + ", " + videoID  + ")";
		} else {
			return "ErrorResult(" + statusCode + ", err=" + error + ")";
		}
	}
}
