package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

import java.util.ArrayList;
import java.util.List;

import mua.model.Playlist;

public class AllPlaylistsResponse {
	public List<Playlist> list;
	public int statusCode;
	public String error;
	
	public AllPlaylistsResponse (List<Playlist> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public AllPlaylistsResponse (int code, String errorMessage) {
		this.list = new ArrayList<Playlist>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (list == null) { return "EmptyPlaylists"; }
		return "AllPlaylists(" + list.size() + ")";
	}
}
