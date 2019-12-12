package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

public class RegisterSiteRequest {
	public String apikey;
	public String url;
	public String characterName;
	public String sentence;
	public boolean system;
	
	public String getSiteURL() {return url;}
	public void setSiteURL(String url) {this.url = url;}
	
	public RegisterSiteRequest() {
		
	}
	
	public RegisterSiteRequest(String apikey, String url, String character, String sentence) {
		this.url = url;
		this.apikey = apikey;
		this.sentence = sentence;
		this.characterName = character;
	}
	
	
	public String toString() {
		return "Register(" + url + ")";
	}

}


