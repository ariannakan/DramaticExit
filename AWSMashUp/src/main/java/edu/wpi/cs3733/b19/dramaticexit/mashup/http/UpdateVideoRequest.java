package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class UpdateVideoRequest {
	public String videoID;
	public boolean availability;
	public boolean system;
	
	public String getVideoID() { return videoID; }
	public void setVideoID(String videoID) { this.videoID = videoID; }
	
	public boolean getAvailability() { return availability; }
	public void setAvailability(boolean availability) { this.availability = availability; }
	
	public boolean getSystem( ) { return system; }
	public void setSystem(boolean system) { this.system = system; }
	
	public UpdateVideoRequest() {
		
	}
	
	public UpdateVideoRequest(String videoID, boolean availability) {
		this.availability = availability;
	}
	
	public UpdateVideoRequest(String videoID, boolean availability, boolean system) {
		this.availability = availability;
		this.system = system;
	}
	
	public String toString() {
		return "UpdateVideo(" + videoID + "," + availability + ")";
	}

}
