package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class UploadVideoRequest {
	public String videoID;
	public String characterName;
	public String sentence;
	public String oggFile;
	public boolean availability;
	public String url;
	public boolean system;
	
	public String getVideoID() { return videoID; }
	public void setVideoID(String videoID) { this.videoID = videoID; }

	public String getCharacterName() { return characterName; }
	public void setCharacterName(String characterName) { this.characterName = characterName; }
	
	public String getSentence() { return sentence; }
	public void setSentence(String sentence) { this.sentence = sentence; }
	
	public String getoggFile() { return oggFile; }
	public void setoggFile(String oggFile) { this.oggFile = oggFile; }
	
	public boolean getAvailability() { return availability; }
	public void setAvailability(boolean availability) { this.availability = availability; }
	
	public String getVideoURL() { return url; }
	public void setVideoURL(String videoURL) { this.url = videoURL; }
	
	public boolean getSystem( ) { return system; }
	public void setSystem(boolean system) { this.system = system; }
	
	public UploadVideoRequest() {
		
	}
	
	public UploadVideoRequest(String videoID, String characterName, String sentence, String oggFile, boolean availability, String videoURL) {
		this.videoID = videoID;
		this.characterName = characterName;
		this.sentence = sentence;
		this.oggFile = oggFile; 
		this.availability = availability;
		this.url = videoURL;
	}
	
	public UploadVideoRequest(String videoID, String characterName, String sentence, String oggFile, boolean availability, String videoURL, boolean system) {
		this.videoID = videoID;
		this.characterName = characterName;
		this.sentence = sentence;
		this.oggFile = oggFile; 
		this.availability = availability;
		this.url = videoURL;
		this.system = system;
	}
	
	public String toString() {
		return "UploadVideo(" + videoID + "," + characterName + "," + sentence + "," + oggFile + "," + availability + "," + url + ")";
	}
}
