// tests create/delete playlist and list playlists and append/remove from playlist

package edu.wpi.cs3733.b19.dramaticexit.mashup;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs3733.b19.dramaticexit.mashup.http.AllPlaylistVideosResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.AllPlaylistsResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.AppendToPlaylistRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.AppendToPlaylistResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.CreatePlaylistRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.CreatePlaylistResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.DeletePlaylistRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.DeletePlaylistResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.ListPlaylistVideosRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.ListPlaylistsRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.RemoveFromPlaylistRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.RemoveFromPlaylistResponse;

public class CreatePlaylistTest extends LambdaTest{
	
	void testSuccessInput(String incoming) throws IOException {
		CreatePlaylistHandler handler = new CreatePlaylistHandler();
		CreatePlaylistRequest req = new Gson().fromJson(incoming, CreatePlaylistRequest.class);
	   
		CreatePlaylistResponse resp = handler.handleRequest(req, createContext("create"));
	    Assert.assertEquals(200, resp.statusCode);
	}
	
	void testFailInput(String incoming, int failureCode) throws IOException {
		CreatePlaylistHandler handler = new CreatePlaylistHandler();
		CreatePlaylistRequest req = new Gson().fromJson(incoming, CreatePlaylistRequest.class);
	   
		CreatePlaylistResponse resp = handler.handleRequest(req, createContext("create"));
	    Assert.assertEquals(failureCode, resp.statusCode);
	}
	
	void testSuccessDelete(String incoming) throws IOException {
		DeletePlaylistHandler handler = new DeletePlaylistHandler();
		DeletePlaylistRequest req = new Gson().fromJson(incoming, DeletePlaylistRequest.class);
	   
	    DeletePlaylistResponse resp = handler.handleRequest(req, createContext("create"));
	    Assert.assertEquals(200, resp.statusCode);
	}
	
	void testFailDelete(String incoming, int failureCode) throws IOException {
		DeletePlaylistHandler handler = new DeletePlaylistHandler();
		DeletePlaylistRequest req = new Gson().fromJson(incoming, DeletePlaylistRequest.class);
	   
	    DeletePlaylistResponse resp = handler.handleRequest(req, createContext("create"));
	    Assert.assertEquals(failureCode, resp.statusCode);
	}
	
	void testSuccessList(String incoming) throws IOException {
		ListAllPlaylistsHandler handler = new ListAllPlaylistsHandler();
		ListPlaylistsRequest req = new Gson().fromJson(incoming, ListPlaylistsRequest.class);
	   
		AllPlaylistsResponse resp = handler.handleRequest(req, createContext("create"));
	    Assert.assertEquals(200, resp.statusCode);
	}
	
	void testSuccessAppend(String incoming) throws IOException {
		AppendtoPlaylistHandler handler = new AppendtoPlaylistHandler();
		AppendToPlaylistRequest req = new Gson().fromJson(incoming, AppendToPlaylistRequest.class);
	   
		AppendToPlaylistResponse resp = handler.handleRequest(req, createContext("create"));
	    Assert.assertEquals(200, resp.statusCode);
	    
	    RemoveFromPlaylistRequest remove = new RemoveFromPlaylistRequest("Michelle", "2019.12.12.20.46.08");
	    String removeFromPlaylist = new Gson().toJson(remove);  
	    RemoveFromPlaylistHandler removehandler = new RemoveFromPlaylistHandler();
		RemoveFromPlaylistRequest rreq = new Gson().fromJson(removeFromPlaylist, RemoveFromPlaylistRequest.class);
	   
		RemoveFromPlaylistResponse rresp = removehandler.handleRequest(rreq, createContext("create"));
	}
	
	void testFailAppend(String incoming, int failureCode) throws IOException {
		AppendtoPlaylistHandler handler = new AppendtoPlaylistHandler();
		AppendToPlaylistRequest req = new Gson().fromJson(incoming, AppendToPlaylistRequest.class);
	   
		AppendToPlaylistResponse resp = handler.handleRequest(req, createContext("create"));
	    Assert.assertEquals(failureCode, resp.statusCode);
	}
	
	void testSuccessRemove(String incoming) throws IOException {
		RemoveFromPlaylistHandler handler = new RemoveFromPlaylistHandler();
		RemoveFromPlaylistRequest req = new Gson().fromJson(incoming, RemoveFromPlaylistRequest.class);
	   
		RemoveFromPlaylistResponse resp = handler.handleRequest(req, createContext("create"));
	    Assert.assertEquals(200, resp.statusCode);
	}
	
	void testFailRemove(String incoming, int failureCode) throws IOException {
		RemoveFromPlaylistHandler handler = new RemoveFromPlaylistHandler();
		RemoveFromPlaylistRequest req = new Gson().fromJson(incoming, RemoveFromPlaylistRequest.class);
	   
		RemoveFromPlaylistResponse resp = handler.handleRequest(req, createContext("create"));
	    Assert.assertEquals(failureCode, resp.statusCode);
	}
	
	void testSuccessListPlaylistVideos(String incoming, int expected) throws IOException {
		ListAllPlaylistVideosHandler handler = new ListAllPlaylistVideosHandler();
		ListPlaylistVideosRequest req = new Gson().fromJson(incoming, ListPlaylistVideosRequest.class);
		
		AllPlaylistVideosResponse resp = handler.handleRequest(req, createContext("create"));
		Assert.assertEquals(resp.list.size(), expected);
	}
	
	@Test
	public void testCreateNewPlaylist() {
		System.out.println("Testing: create new playlist");
		String n = "" + Math.random();
		CreatePlaylistRequest testNew = new CreatePlaylistRequest(n);
	    String newPlaylist = new Gson().toJson(testNew);  
	    
	    try {
	    	testSuccessInput(newPlaylist);
	    } catch (IOException ioe) {
	    	Assert.fail("Invalid:" + ioe.getMessage());
	    }
	}
	
	@Test
	public void testCreateInvalidPlaylist() {
		System.out.println("Testing: invalid playlist name");
		CreatePlaylistRequest testNew = new CreatePlaylistRequest();
	    String newPlaylist = new Gson().toJson(testNew);  
	    
	    try {
	    	testFailInput(newPlaylist, 422);
	    } catch (IOException ioe) {
	    	Assert.fail("Invalid:" + ioe.getMessage());
	    }
	}
	
	@Test
	public void testCreateExistingPlaylist() {
		System.out.println("Testing: create playlist with same name");
		String name = "Michelle";
		CreatePlaylistRequest testNew = new CreatePlaylistRequest(name);
	    String newPlaylist = new Gson().toJson(testNew);  
		CreatePlaylistHandler handler = new CreatePlaylistHandler();
		CreatePlaylistRequest req = new Gson().fromJson(newPlaylist, CreatePlaylistRequest.class);
	   
		CreatePlaylistResponse resp = handler.handleRequest(req, createContext("create"));

		CreatePlaylistRequest testSame = new CreatePlaylistRequest(name);
	    String samePlaylist = new Gson().toJson(testSame);  
	    
	    try {
	    	testFailInput(samePlaylist, 422);
	    } catch (IOException ioe) {
	    	Assert.fail("Invalid:" + ioe.getMessage());
	    }
	}
	
	@Test
	public void testDeleteExistingPlaylist() {
		System.out.println("Testing: delete existing playlist");
		String n = "Moody";
		CreatePlaylistRequest testNew = new CreatePlaylistRequest(n);
	    String newPlaylist = new Gson().toJson(testNew);  
		CreatePlaylistHandler handler = new CreatePlaylistHandler();
		CreatePlaylistRequest req = new Gson().fromJson(newPlaylist, CreatePlaylistRequest.class);
	   
		CreatePlaylistResponse resp = handler.handleRequest(req, createContext("create"));
		
		System.out.println("playlist created: " + resp.playlistID);
	    
		DeletePlaylistRequest testExisting = new DeletePlaylistRequest(resp.playlistID);
	    String deleteExisting = new Gson().toJson(testExisting);  
	    
	    try {
	    	testSuccessDelete(deleteExisting);
	    } catch (IOException ioe) {
	    	Assert.fail("Invalid:" + ioe.getMessage());
	    }
	}
	
	@Test
	public void testDeleteNonExistingPlaylist() {
		System.out.println("Testing: delete nonexisting playlist");
	    
		DeletePlaylistRequest testExisting = new DeletePlaylistRequest("");
	    String deleteExisting = new Gson().toJson(testExisting);  
	    
	    try {
	    	testFailDelete(deleteExisting, 422);
	    } catch (IOException ioe) {
	    	Assert.fail("Invalid:" + ioe.getMessage());
	    }
	}
	
	@Test
	public void testListPlaylistsSuccess() {
		System.out.println("Testing: listing playlists");
		
		ListPlaylistsRequest list = new ListPlaylistsRequest();
	    String listPlaylists = new Gson().toJson(list);  
	    
	    try {
	    	testSuccessList(listPlaylists);
	    } catch (IOException ioe) {
	    	Assert.fail("Invalid:" + ioe.getMessage());
	    }
	}
	
	@Test
	public void testAppendToPlaylistSuccess() {
		System.out.println("Testing: appending to playlist");
		
		AppendToPlaylistRequest append = new AppendToPlaylistRequest("Michelle", "2019.12.12.20.46.08");
	    String appendToPlaylist = new Gson().toJson(append);  
	    
	    System.out.println("append: " + appendToPlaylist);
	    
	    try {
	    	testSuccessAppend(appendToPlaylist);
	    } catch (IOException ioe) {
	    	Assert.fail("Invalid:" + ioe.getMessage());
	    }
	}
	
	@Test
	public void testAppendToPlaylistFail() {
		System.out.println("Testing: fail appending to playlist");
		
		AppendToPlaylistRequest append = new AppendToPlaylistRequest("Michelle", "");
	    String appendToPlaylist = new Gson().toJson(append);  
	    
	    try {
	    	testFailAppend(appendToPlaylist, 422);
	    } catch (IOException ioe) {
	    	Assert.fail("Invalid:" + ioe.getMessage());
	    }
	}
	
	@Test
	public void testRemoveFromPlaylistSuccess() {
		System.out.println("Testing: removing from playlist");
		
		AppendToPlaylistRequest append = new AppendToPlaylistRequest("Michelle", "2019.12.12.20.46.01");
	    String appendToPlaylist = new Gson().toJson(append);
	    AppendtoPlaylistHandler handler = new AppendtoPlaylistHandler();
		AppendToPlaylistRequest req = new Gson().fromJson(appendToPlaylist, AppendToPlaylistRequest.class);
	   
		AppendToPlaylistResponse resp = handler.handleRequest(req, createContext("create"));
		
		System.out.println("append resp: " + resp);
		
		RemoveFromPlaylistRequest remove = new RemoveFromPlaylistRequest("Michelle", "2019.12.12.20.46.01");
	    String removeFromPlaylist = new Gson().toJson(remove);  
	    
	    try {
	    	testSuccessRemove(removeFromPlaylist);
	    } catch (IOException ioe) {
	    	Assert.fail("Invalid:" + ioe.getMessage());
	    }
	}
	
	@Test
	public void testRemoveFromPlaylistFail() {
		System.out.println("Testing: fail to remove from playlist");
		
		RemoveFromPlaylistRequest remove = new RemoveFromPlaylistRequest("Michelle", "");
	    String removeFromPlaylist = new Gson().toJson(remove);  
	    
	    try {
	    	testFailRemove(removeFromPlaylist, 422);
	    } catch (IOException ioe) {
	    	Assert.fail("Invalid:" + ioe.getMessage());
	    }
	}
	
	@Test
	public void testRemoveFromPlaylistFail2() {
		System.out.println("Testing: fail to remove from playlist (playlist doesn't exist)");
		
		RemoveFromPlaylistRequest remove = new RemoveFromPlaylistRequest("bob", "2019.12.12.20.46.01");
	    String removeFromPlaylist = new Gson().toJson(remove);  
	    
	    try {
	    	testFailRemove(removeFromPlaylist, 422);
	    } catch (IOException ioe) {
	    	Assert.fail("Invalid:" + ioe.getMessage());
	    }
	}
	
	@Test
	public void testListPlaylistVideosSuccess() {
		System.out.println("Testing: listing playlist videos");
		
		AppendToPlaylistRequest append1 = new AppendToPlaylistRequest("Michelle", "2019.12.12.20.46.01");
	    String appendToPlaylist1 = new Gson().toJson(append1);  
	    System.out.println("append: " + appendToPlaylist1);
	    AppendtoPlaylistHandler handler1 = new AppendtoPlaylistHandler();
		AppendToPlaylistRequest req1 = new Gson().fromJson(appendToPlaylist1, AppendToPlaylistRequest.class);
		AppendToPlaylistResponse resp1 = handler1.handleRequest(req1, createContext("create"));
		
		AppendToPlaylistRequest append2 = new AppendToPlaylistRequest("Michelle", "2019.12.12.20.51.09");
	    String appendToPlaylist2 = new Gson().toJson(append2);  
	    System.out.println("append: " + appendToPlaylist2);
	    AppendtoPlaylistHandler handler2 = new AppendtoPlaylistHandler();
		AppendToPlaylistRequest req2 = new Gson().fromJson(appendToPlaylist2, AppendToPlaylistRequest.class);
		AppendToPlaylistResponse resp2 = handler2.handleRequest(req2, createContext("create"));
		
		ListPlaylistVideosRequest list = new ListPlaylistVideosRequest("Michelle");
	    String listPlaylistVideos = new Gson().toJson(list);  
	    
	    try {
	    	testSuccessListPlaylistVideos(listPlaylistVideos, 2);
	    } catch (IOException ioe) {
	    	Assert.fail("Invalid:" + ioe.getMessage());
	    }
	}

}
