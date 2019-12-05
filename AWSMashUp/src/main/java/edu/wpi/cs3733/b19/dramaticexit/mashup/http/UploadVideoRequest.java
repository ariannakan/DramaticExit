package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

import java.io.File;

public class UploadVideoRequest {
	public String videoID;
	public String characterName;
	public String sentence;
	public File oggFile;
	public boolean availability;
	public boolean system;
	
	public String getVideoID() { return videoID; }
	public void setVideoID(String videoID) { this.videoID = videoID; }

	public String getCharacterName() { return characterName; }
	public void setCharacterName(String characterName) { this.characterName = characterName; }
	
	public String getSentence() { return sentence; }
	public void setSentence(String sentence) { this.sentence = sentence; }
	
	public File getoggFile() { return oggFile; }
	public void setoggFile(File oggFile) { this.oggFile = oggFile; }
	
	public boolean getAvailability() { return availability; }
	public void setAvailability(boolean availability) { this.availability = availability; }
	
	public boolean getSystem( ) { return system; }
	public void setSystem(boolean system) { this.system = system; }
	
	public UploadVideoRequest() {
		
	}
	
	public UploadVideoRequest(String videoID, String characterName, String sentence, File oggFile, boolean availability) {
		this.videoID = videoID;
		this.characterName = characterName;
		this.sentence = sentence;
		this.oggFile = oggFile; 
		this.availability = availability;
	}
	
	public UploadVideoRequest(String videoID, String characterName, String sentence, File oggFile, boolean availability, boolean system) {
		this.videoID = videoID;
		this.characterName = characterName;
		this.sentence = sentence;
		this.oggFile = oggFile; 
		this.availability = availability;
		this.system = system;
	}
	
	public String toString() {
		return "UploadVideo(" + videoID + "," + characterName + "," + sentence + "," + oggFile + "," + availability + ")";
	}
}
