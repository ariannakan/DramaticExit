package edu.wpi.cs3733.b19.dramaticexit.mashup.model;

public class Segment {
	public final String url;
	public final String character;
	public final String text;
	
	public Segment(String url, String characterName, String sentence) {
		this.url = url;
		this.character = characterName;
		this.text = sentence;
	}
}
