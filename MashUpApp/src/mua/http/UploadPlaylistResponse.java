package mua.http;

public class UploadPlaylistResponse {
	public final String playlistID;
	public final int statusCode;
	public String error;
	
	public UploadPlaylistResponse(String playlistID, int statusCode) {
		this.playlistID = playlistID;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	// 200 = success
	public UploadPlaylistResponse(String playlistID, int statusCode, String errorMessage) {
		this.playlistID = playlistID;
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	public String toString() {
		if(statusCode == 200) {
			return "UploadResponse(" + playlistID + " has been uploaded successfully!)";
		} else {
			return "ErrorResult(" + playlistID + ", statusCode = " + statusCode + ", err=" + error + ")";
		}
	}

}
