package edu.wpi.cs3733.b19.dramaticexit.mashup.model;

import java.util.ArrayList;
import java.util.Iterator;

public class Library {
	public boolean system;      // when TRUE this is actually stored in S3 bucket
	public ArrayList<Video> videos = new ArrayList<Video>(); 
	public ArrayList<Playlist> playlists = new ArrayList<Playlist>(); 
	ArrayList<Video> searchvid;
	ArrayList<Playlist> searchplay;

    Iterator<Video> iterator1 = videos.iterator(); //iterator to traverse the list 
    Iterator<Playlist> iterator2 = playlists.iterator(); //iterator to traverse the list 

	
	public Library () {
	}
	
	public boolean addVideo(Video video){
		videos.add(video);
	}
	
	public boolean remVideo(String searchID){
		while (iterator1.hasNext()) {
			if (iterator1.next().videoID.contains(searchID)) {
				videos.remove(iterator1.next());
			}
		}
	}
	
	public boolean addPlaylist(Playlist playlist){
		playlists.add(playlist);
	}
	
	public boolean remPlaylist(String searchID){
		while (iterator2.hasNext()) {
			if (iterator2.next().playlistID.contains(searchID)) {
				videos.remove(iterator2.next());
			}
		}
	}
	
	//public void showVideo() {}
	/**
	 * Equality of Constants determined by name alone
	 */
	public boolean search(String searchkey) {
		searchvid = new ArrayList<Video>(); 
		searchplay = new ArrayList<Playlist>(); 

		while (iterator1.hasNext()) {
			if (true) {
				searchvid.add(iterator1.next());
			}
		}
		
		while (iterator2.hasNext()) {
			if (true) {
				searchplay.add(iterator2.next());
			}
		}
	}
}

