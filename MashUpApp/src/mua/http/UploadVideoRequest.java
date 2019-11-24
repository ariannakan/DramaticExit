package mua.http;

public class UploadVideoRequest {
	public String videoID;
	public String characterName;
	public String sentence;
	public boolean availability;
	public String base64EncodedValue;
	public boolean system;
	
	public String getVideo() { return videoID; }
	
	public boolean getSystem( ) { return system; }
	public void setSystem(boolean system) { this.system = system; }
	
	public String getBase64EncodedValue() { return base64EncodedValue; }
	public void setBase64EncodedValue(String base64EncodedValue) { this.base64EncodedValue = base64EncodedValue; }
	
	public UploadVideoRequest(String videoID, String characterName, String sentence, boolean availability, String encoding) {
		this.videoID = videoID; 
		this.characterName = characterName;
		this.sentence = sentence;
		this.availability = true;
		this.base64EncodedValue = encoding;
	}
	
	public UploadVideoRequest(String videoID, String characterName, String sentence, boolean availability, String encoding, boolean system) {
		this.videoID = videoID; 
		this.characterName = characterName;
		this.sentence = sentence;
		this.availability = true;
		this.base64EncodedValue = encoding;
		this.system = system;
	}
	
	public String toString() {
		return "UploadVideo(" + videoID + "," + characterName + "," + sentence + "," + base64EncodedValue + ")";
	}
}
