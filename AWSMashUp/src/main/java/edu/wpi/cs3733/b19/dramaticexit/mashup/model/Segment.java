package edu.wpi.cs3733.b19.dramaticexit.mashup.model;

public class Segment {
	public String url;
	public String character;
	public String text;
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getCharacter() {
		return character;
	}
	
	public void setCharacter(String character) {
		this.character = character;
	}

	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	

	public Segment(String url, String characterName, String sentence) {
		this.url = url;
		this.character = characterName;
		this.text = sentence;
	}
}
