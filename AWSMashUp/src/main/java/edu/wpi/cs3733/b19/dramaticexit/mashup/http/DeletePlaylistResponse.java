package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class DeletePlaylistResponse {
	public String playlistID;
	public int statusCode;
	public String error;
	
	public DeletePlaylistResponse(String id, int code) {
		this.playlistID = id;
		this.statusCode = code;
		this.error = "";
	}
	
	// 200 = success
	public DeletePlaylistResponse(String id, int code, String errorMessage) {
		this.playlistID = id;
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if(statusCode == 200) {
			return "DeletePlaylist(" + playlistID + ")";
		} else {
			return "ErrorResult(" + playlistID + ", statusCode = " + statusCode + ", err=" + error + ")";
		}
	}

}
