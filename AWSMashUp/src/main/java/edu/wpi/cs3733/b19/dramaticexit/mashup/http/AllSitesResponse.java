package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Site;

public class AllSitesResponse {
	public List<Site> list;
	public int statusCode;
	public String error;
	
	public AllSitesResponse(List<Site> list, int code) {
		this.list = list;
		this.statusCode = code;
	}
	
	public AllSitesResponse(int code, String errorMessage) {
		this.list = new ArrayList<Site>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if(list == null) {return "EmptySites";}
		return "AllSites(" + list.size() + ")";
	}

}
