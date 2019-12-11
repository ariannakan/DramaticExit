package edu.wpi.cs3733.b19.dramaticexit.mashup.model;

public class Video {
	public String videoID;
	public String characterName;
	public String sentence;
	public boolean availability;
	public String url;
	public boolean system;      // when TRUE this is actually stored in S3 bucket
	
	public Video (String videoID, String characterName, String sentence, boolean availability, String url) {
		this.sentence = "";
		this.videoID = videoID;
		this.characterName = characterName;
		this.sentence = sentence;
		this.availability = availability;
		this.url = url;
	}
	
	public Video (String videoID, String characterName, String sentence, String url) {
		this.sentence = "";
		this.videoID = videoID;
		this.characterName = characterName;
		this.sentence = sentence;
		this.url = url;
	}
	
	public Video (String url, String characterName, String sentence) {
		this.characterName = characterName;
		this.url = url;
		this.sentence = sentence;
	}
	
	public Video (String videoID, String url) {
		this.videoID = videoID;
		this.url = url;
		this.availability = true;
	}
	
	public Video() {
		this.sentence = "";
		this.videoID = "";
		this.characterName = "";
		this.sentence = "";
		this.availability = true;
		this.url = "";
	}
	
	public boolean getSystem() { return system; }
	public void setSystem(boolean s) { system = s; }
	
	/**
	 * Equality of Videos determined by videoID alone.
	 */
	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Video) {
			Video other = (Video) o;
			return videoID.equals(other.videoID);
		}
		
		return false;  // not a Video
	}
}
