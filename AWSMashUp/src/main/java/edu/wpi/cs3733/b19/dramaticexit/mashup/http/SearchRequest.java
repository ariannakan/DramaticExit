package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

import java.util.List;

import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Video;

public class SearchRequest {
	public String keyword;
	
	public String getKeyword() {return keyword;}
	public void setKeywork(String keyword) {this.keyword = keyword;}
	
	public SearchRequest() {
	}
	
	public SearchRequest(String keyword) {
		this.keyword = keyword;
	}
	
	public String toString() {
		return "SearchRequest(" + keyword +")";
	}

}
