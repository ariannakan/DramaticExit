package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

import java.io.File;

public class UploadVideoRequest {
	public String videoID;
	public String characterName;
	public String sentence;
	public boolean availability;
	public boolean system;
/*NR*/
	public String video64;
	public String getVideo64() {
		return video64;
	}
	public void setVideo64(String video64) {
		this.video64 = video64;
	}
	/*--- NR*/
	public String getVideoID() { return videoID; }
	public void setVideoID(String videoID) { this.videoID = videoID; } 

	public String getCharacterName() { return characterName; }
	public void setCharacterName(String characterName) { this.characterName = characterName; }
	
	public String getSentence() { return sentence; }
	public void setSentence(String sentence) { this.sentence = sentence; }
	
	public boolean getAvailability() { return availability; }
	public void setAvailability(boolean availability) { this.availability = availability; }
	
	public boolean getSystem( ) { return system; }
	public void setSystem(boolean system) { this.system = system; }
	
	public UploadVideoRequest() {
		
	}
	
	public String toString() {
		return "UploadVideo(" + videoID + "," + characterName + "," + sentence + "," + video64 + "," + availability + ")";
	}
	
/* -- begin NR*/	
	public UploadVideoRequest(String videoID, String characterName, String sentence, String video64, boolean availability, boolean system) {
		this.videoID = videoID;
		this.characterName = characterName;
		this.sentence = sentence;
		this.video64 = video64; 
		this.availability = availability;
		this.system = system;
	}
	
	public UploadVideoRequest(String characterName, String sentence, String video64) {
		this.videoID = "";
		this.characterName = characterName;
		this.sentence = sentence;
		this.video64 = video64; 
		this.availability = true;
	}
	/*end NR*/
}
