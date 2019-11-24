package mua.http;

public class DeleteVideoRequest {
	public String videoID;
	
	public void setVideoID(String videoID) {this.videoID = videoID;}
	public String getVideoID() { return videoID; }
	
	public String getVideo() { return videoID; }
	
	public DeleteVideoRequest(String videoID) {
		this.videoID = videoID;
	}
	
	public DeleteVideoRequest() {
		
	}
	
	public String toString() {
		return "Delete(" + videoID + ")";
	}

}
