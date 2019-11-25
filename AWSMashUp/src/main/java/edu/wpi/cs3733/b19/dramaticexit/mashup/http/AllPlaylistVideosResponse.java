package edu.wpi.cs3733.b19.dramaticexit.mashup.http;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Video;
=======
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Playlist;
>>>>>>> branch 'master' of https://github.com/ksnoddy/DramaticExit.git

public class AllPlaylistVideosResponse {
<<<<<<< HEAD
	public List<Video> list;
=======
	public List<Playlist> list;
>>>>>>> branch 'master' of https://github.com/ksnoddy/DramaticExit.git
	public int statusCode;
	public String error;
	
<<<<<<< HEAD
	public AllPlaylistVideosResponse (List<Video> list, int code) {
=======
	public AllPlaylistVideosResponse (List<Playlist> list, int code) {
>>>>>>> branch 'master' of https://github.com/ksnoddy/DramaticExit.git
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public AllPlaylistVideosResponse (int code, String errorMessage) {
<<<<<<< HEAD
		this.list = new ArrayList<Video>();
=======
		this.list = new ArrayList<Playlist>();
>>>>>>> branch 'master' of https://github.com/ksnoddy/DramaticExit.git
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (list == null) { return "EmptyPlaylistVideos"; }
		return "AllPlaylistVideos(" + list.size() + ")";
	}

}
