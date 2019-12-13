// tests create and delete and list

package edu.wpi.cs3733.b19.dramaticexit.mashup;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs3733.b19.dramaticexit.mashup.http.AllPlaylistsResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.CreatePlaylistRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.CreatePlaylistResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.DeletePlaylistRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.DeletePlaylistResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.ListPlaylistsRequest;

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
	    
		DeletePlaylistRequest testExisting = new DeletePlaylistRequest(n);
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

}
