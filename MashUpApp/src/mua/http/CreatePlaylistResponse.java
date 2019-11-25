package mua.http;

public class CreatePlaylistResponse {
	public final String playlistID;
	public final int statusCode;
	public String error;
	
	public CreatePlaylistResponse(String playlistID, int statusCode) {
		this.playlistID = playlistID;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	// 200 = success
	public CreatePlaylistResponse(String playlistID, int statusCode, String errorMessage) {
		this.playlistID = playlistID;
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	public String toString() {
		if(statusCode == 200) {
			return "CreateResponse(" + playlistID + " has been uploaded successfully!)";
		} else {
			return "ErrorResult(" + playlistID + ", statusCode = " + statusCode + ", err=" + error + ")";
		}
	}

}
