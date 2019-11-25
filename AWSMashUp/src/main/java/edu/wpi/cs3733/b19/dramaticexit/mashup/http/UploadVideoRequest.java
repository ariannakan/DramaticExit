package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Video;

public class UploadVideoRequest {
	public Video videoSegment;
	public boolean system;
	
	public Video getVideo() { return videoSegment; }
	public void setVideo(Video videoSegment) { this.videoSegment = videoSegment; }
	
	public boolean getSystem( ) { return system; }
	public void setSystem(boolean system) { this.system = system; }
	
	public UploadVideoRequest() {
		
	}
	
	public UploadVideoRequest(Video videoSegment) {
		this.videoSegment = videoSegment; 
	}
	
	public UploadVideoRequest(Video videoSegment, boolean system) {
		this.videoSegment = videoSegment;
		this.system = system;
	}
	
	public String toString() {
		return "UploadVideo(" + videoSegment + ")";
	}
}
