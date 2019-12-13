// tests create/delete playlist and list playlists and append/remove from playlist

package edu.wpi.cs3733.b19.dramaticexit.mashup;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs3733.b19.dramaticexit.mashup.http.AllPlaylistsResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.AppendToPlaylistRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.AppendToPlaylistResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.CreatePlaylistRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.CreatePlaylistResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.DeletePlaylistRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.DeletePlaylistResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.ListPlaylistsRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.RemoveFromPlaylistRequest;

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
	}
	
	void testFailAppend(String incoming, int failureCode) throws IOException {
		AppendtoPlaylistHandler handler = new AppendtoPlaylistHandler();
		AppendToPlaylistRequest req = new Gson().fromJson(incoming, AppendToPlaylistRequest.class);
	   
		AppendToPlaylistResponse resp = handler.handleRequest(req, createContext("create"));
	    Assert.assertEquals(failureCode, resp.statusCode);
	}
	
	@Test
	public void testCreateNewPlaylist() {
		System.out.println("Testing: create new playlist");
		String n = "SneakyNarwhal";
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
	   
		
		RemoveFromPlaylistRequest remove = new RemoveFromPlaylistRequest("Michelle", "2019.12.12.20.46.01");
	    String removeFromPlaylist = new Gson().toJson(remove);  
	    
	    try {
	    	testSuccessInput(removeFromPlaylist);
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
	    	testSuccessInput(removeFromPlaylist);
	    } catch (IOException ioe) {
	    	Assert.fail("Invalid:" + ioe.getMessage());
	    }
	}

}
