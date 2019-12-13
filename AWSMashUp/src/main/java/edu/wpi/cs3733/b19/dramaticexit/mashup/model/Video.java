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
	
	public Video(String videoID, String characterName, String sentence, String url) {
		this.videoID = videoID;
		this.characterName = characterName;
		this.sentence = sentence;
		this.url = url;
	}
	
	public Video(String videoID, String url) {
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
}
