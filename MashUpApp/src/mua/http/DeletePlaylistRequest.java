package mua.http;

public class DeletePlaylistRequest {
	public String playlistID;
	
	public void setPlaylist(String playlistID) {this.playlistID = playlistID;}
	public String getPlaylist() {return playlistID;}
	
	public DeletePlaylistRequest() {
		
	}
	
	public String toString() {
		return "Delete(" + playlistID + ")";
	}

}
