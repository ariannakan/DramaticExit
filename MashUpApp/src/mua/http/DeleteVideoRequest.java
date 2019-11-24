package mua.http;

public class DeleteVideoRequest {
	public String videoID;
	
	public void setVideo(String videoID) {this.videoID = videoID;}
	public String getVideo() {return videoID;}
	
	public DeleteVideoRequest() {
		
	}
	
	public String toString() {
		return "Delete(" + videoID + ")";
	}

}
