package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

import java.util.List;

import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Video;

public class SearchRequest {
	public String keywordname;
	public String keywordsentence;
	
	public String getKeywordName() {return keywordname;}
	public String getKeywordSentence() {return keywordsentence;}

	public void setKeyworkName(String keyword) {this.keywordname = keyword;}
	public void setKeyworkSentence(String keyword) {this.keywordsentence = keyword;}

	
	public SearchRequest() {
	}
	
	public SearchRequest(String n, String s) {
		this.keywordname = n;
		this.keywordsentence = s;
	}
	
	public String toString() {
		return "SearchRequest(" + keywordname +")";
	}

}
