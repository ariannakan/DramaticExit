package mua.model;

import java.util.ArrayList;
import java.util.Iterator;

public class Playlist {
	public final String playlistID;
	public final String name;
	public boolean system;      // when TRUE this is actually stored in S3 bucket
	public ArrayList<Video> videos = new ArrayList<Video>(); 
	Iterator<Video> iterator = videos.iterator(); //iterator to traverse the list 
	
	public Playlist (String id, String name) {
		this.playlistID = id;
		this.name = name;
	}
	
	public Playlist (String id, String name, boolean system) {
		this.playlistID = id;
		this.name = name;
		this.system = system;
	}
	
	public boolean getSystem() { return system; }
	public void setSystem(boolean s) { system = s; }
	
	public boolean addVideo(Video video){
		videos.add(video);
	}
	
	public boolean remVideo(String searchID){
		while (iterator.hasNext()) {
			if (iterator.next().videoID.contains(searchID)) {
				videos.remove(iterator.next());
			}
			
		}
	}
	
	//public void showVideo() {}
	
}

