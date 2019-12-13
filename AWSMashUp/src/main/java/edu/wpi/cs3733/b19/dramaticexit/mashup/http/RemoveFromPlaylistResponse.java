package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class RemoveFromPlaylistResponse {

	public String playlistName;
	public String videoID;
	public int statusCode;
	public String error;
	
	// success - 200
	public RemoveFromPlaylistResponse (String playlistID, String videoID) {
		this.playlistName = playlistID; 
		this.videoID = videoID;
		this.statusCode = 200;
	}
	
	public RemoveFromPlaylistResponse (String playlistID, String videoID, int statusCode, String errorMessage) {
		this.playlistName = playlistID; 
		this.videoID = videoID;
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	public RemoveFromPlaylistResponse(String errorMessage, int statusCode) {
		this.error = errorMessage;
		this.statusCode = statusCode;
	}

	public String toString() {
		if (statusCode == 200) {  
			return "Result(" + playlistName + ", " + videoID  + ") statusCode: " + statusCode;
		} else {
			return "ErrorResult(" + statusCode + ", err=" + error + ")";
		}
	}
}
