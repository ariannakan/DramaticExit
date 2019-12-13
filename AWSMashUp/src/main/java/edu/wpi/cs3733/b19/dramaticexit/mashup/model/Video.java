package edu.wpi.cs3733.b19.dramaticexit.mashup.model;

public class Video {
	public String videoID;
	public String characterName;
	public String sentence;
	public boolean availability;
	public String url;
	public boolean system;      // when TRUE this is actually stored in S3 bucket
	
	public String getVideoID() {
		return videoID;
	}

	public void setVideoID(String videoID) {
		this.videoID = videoID;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
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
	
	/**
	 * Equality of Videos determined by videoID alone.
	 */
	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Video) {
			Video other = (Video) o;
			return videoID.equals(other.videoID);
		}
		
		return false;  // not a Constant
	}
}
