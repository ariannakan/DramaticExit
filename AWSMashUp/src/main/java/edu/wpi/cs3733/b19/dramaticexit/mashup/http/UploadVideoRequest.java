package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

import java.io.File;

public class UploadVideoRequest {
	public String videoID;
	public String characterName;
	public String sentence;
	public boolean availability;
	public boolean system;
/*NR*/
	public String base64EncodedValue;
	
	public String getBase64EncodedValue() { return base64EncodedValue;}
	public void setBase64EncodedValue(String base64EncodedValue) { this.base64EncodedValue = base64EncodedValue;}
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
	
/* -- begin NR*/	
	public UploadVideoRequest(String videoID, String characterName, String sentence, boolean availability, String encoding, boolean system) {
		this.videoID = videoID;
		this.characterName = characterName;
		this.sentence = sentence; 
		this.availability = availability;
		this.base64EncodedValue = encoding;
		this.system = system;
	}
	
	public UploadVideoRequest(String characterName, String sentence, String encoding) {
		this.videoID = "";
		this.characterName = characterName;
		this.sentence = sentence;
		this.availability = true;
		this.base64EncodedValue = encoding;
	}
	/*end NR*/
	
	public String toString() {
		return "UploadVideo(" + videoID + "," + characterName + "," + sentence + "," + availability + "," + base64EncodedValue + ")";
	}
}
