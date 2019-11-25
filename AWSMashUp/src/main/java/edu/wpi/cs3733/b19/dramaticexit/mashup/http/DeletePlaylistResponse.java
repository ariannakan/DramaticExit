package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class DeletePlaylistResponse {
	public String playlistID;
	public int statusCode;
	public String error;
	
	public DeletePlaylistResponse(String playlistID, int statusCode) {
		this.playlistID = playlistID;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	// 200 = success
	public DeletePlaylistResponse(String playlistID, int statusCode, String errorMessage) {
		this.playlistID = playlistID;
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	public String toString() {
		if(statusCode == 200) {
			return "DeleteResponse(" + playlistID + " deleted successfully!)";
		} else {
			return "ErrorResult(" + playlistID + ", statusCode = " + statusCode + ", err=" + error +")";
		}
	}

}
