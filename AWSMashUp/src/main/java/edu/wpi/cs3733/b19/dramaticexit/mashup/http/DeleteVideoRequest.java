package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class DeleteVideoRequest {
	public String videoID;
	public boolean system;

	
	public void setVideoID(String videoID) {this.videoID = videoID;}
	public String getVideoID() { return videoID; }
	
	public DeleteVideoRequest() {
		
	}
	
	public DeleteVideoRequest(String videoID) {
		this.videoID = videoID;
	}
	
	public String toString() {
		return "Delete(" + videoID + ")";
	}

}
