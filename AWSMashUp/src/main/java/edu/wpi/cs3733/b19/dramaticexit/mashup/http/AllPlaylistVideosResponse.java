package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

import java.util.ArrayList;
import java.util.List;

public class AllPlaylistVideosResponse {
	public List<Constant> list;
	public int statusCode;
	public String error;
	
	public AllConstantsResponse (List<Constant> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public AllConstantsResponse (int code, String errorMessage) {
		this.list = new ArrayList<Constant>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (list == null) { return "EmptyConstants"; }
		return "AllConstants(" + list.size() + ")";
	}

}
