package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class UpdateVideoRequest {
	public String videoID;
	public boolean availability;
	
	public String getVideoID() { return videoID; }
	
	public boolean getAvailability() { return availability; }
	public void setAvailability(boolean availability) { this.availability = availability; }
	
	public UpdateVideoRequest() {
		
	}
	
	public UpdateVideoRequest(String videoID, boolean availability) {
		this.availability = availability;
	}

	
	public String toString() {
		return "UpdateVideo(" + videoID + "," + availability + ")";
	}

}
