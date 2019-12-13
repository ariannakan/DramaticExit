package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Video;

public class SearchResponse {
	public final List<Video> list;
	public final int statusCode;
	public final String error;
	
	public SearchResponse (List<Video> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public SearchResponse (int code, String errorMessage) {
		this.list = new ArrayList<Video>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (list == null) { return "EmptyVideos"; }
		return "SearchVideos size: " + list.size() + " statusCode: " + statusCode;
	}

}
