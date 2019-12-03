package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Video;

public class AllPlaylistVideosResponse {
	public List<Video> list;
	public int statusCode;
	public String error;
	
	//success - 200
	public AllPlaylistVideosResponse (List<Video> list) {
		this.list = list;
		this.statusCode = 200;
	}
	
	public AllPlaylistVideosResponse (int code, String errorMessage) {
		this.list = new ArrayList<Video>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (list == null) { return "EmptyPlaylistVideos"; }
		return "AllPlaylistVideos(" + list.size() + ")";
	}

}
