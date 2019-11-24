package mua.model;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Iterator;
=======
import java.util.*;
>>>>>>> branch 'master' of https://github.com/ksnoddy/DramaticExit

public class Playlist {
	public final String playlistID;
<<<<<<< HEAD
	public final String name;
=======
	public final String playlistName;
>>>>>>> branch 'master' of https://github.com/ksnoddy/DramaticExit
	public boolean system;      // when TRUE this is actually stored in S3 bucket
<<<<<<< HEAD
	public ArrayList<Video> videos = new ArrayList<Video>(); 
	Iterator<Video> iterator = videos.iterator(); //iterator to traverse the list 
=======
	public ArrayList<String> videos = new ArrayList<String>(); 
    Iterator<String> iterator = videos.iterator(); //iterator to traverse the list 
	public String videoID;
>>>>>>> branch 'master' of https://github.com/ksnoddy/DramaticExit
	
	public Playlist (String id, String name) {
		this.playlistID = id;
<<<<<<< HEAD
		this.name = name;
=======
		this.playlistName = name;
>>>>>>> branch 'master' of https://github.com/ksnoddy/DramaticExit
	}
	
	public Playlist (String id, String name, boolean system) {
		this.playlistID = id;
<<<<<<< HEAD
		this.name = name;
=======
		this.playlistName = name;
>>>>>>> branch 'master' of https://github.com/ksnoddy/DramaticExit
		this.system = system;
	}
	
	public boolean getSystem() { return system; }
	public void setSystem(boolean s) { system = s; }
	
<<<<<<< HEAD
=======
<<<<<<< HEAD
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
=======
	public boolean addVideo(){
		return false;
	}
	
	public boolean removeVideo(){
		return false;
	}
	
	public void showVideo() {}
	
	public void listVideos() {}
>>>>>>> branch 'master' of https://github.com/ksnoddy/DramaticExit
	
>>>>>>> branch 'master' of https://github.com/ksnoddy/DramaticExit.git
}

