package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Segment;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Video;

public class RemoteSegmentsResponse {
	public final List<Segment> Segments;
	public final int statusCode;
	public final String error;
	
	public RemoteSegmentsResponse (List<Segment> list, int code) {
		this.Segments = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public RemoteSegmentsResponse (int code, String errorMessage) {
		this.Segments = new ArrayList<Segment>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (Segments == null) { return "EmptyVideos"; }
		return "SearchVideos(" + Segments.size() + ")";
	}

}
