package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class DeletePlaylistResponse {
	public String playlistID;
	public int statusCode;
	
	public DeletePlaylistResponse(String name, int code) {
		this.playlistID = name;
		this.statusCode = code;
	}
	
	// 200 = success
	public DeletePlaylistResponse(String name) {
		this.playlistID = name;
		this.statusCode = 200;
	}
	
	public String toString() {
		if(statusCode == 200) {
			return "DeletePlaylist(" + playlistID + ")";
		} else {
			return "ErrorResult(" + playlistID + ", statusCode = " + statusCode + ")";
		}
	}

}
