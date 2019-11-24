package mua.http;

public class AppendToPlaylistResponse {

	public String result;
	public int statusCode;
	public String error;
	
	public AppendToPlaylistResponse (String videoID, int statusCode) {
		this.result = "" + videoID; // doesn't matter since error
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public AppendToPlaylistResponse (int statusCode, String errorMessage) {
		this.result = ""; // doesn't matter since error
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (statusCode / 100 == 2) {  // too cute?
			return "Result(" + result + ")";
		} else {
			return "ErrorResult(" + statusCode + ", err=" + error + ")";
		}
	}
}
