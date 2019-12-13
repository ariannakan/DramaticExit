package edu.wpi.cs3733.b19.dramaticexit.mashup;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs3733.b19.dramaticexit.mashup.http.SearchRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.SearchResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Video;


public class SearchTest extends LambdaTest{
	
	void testSuccessInput(String incoming) throws IOException {
		SearchHandler handler = new SearchHandler();
		SearchRequest req = new Gson().fromJson(incoming, SearchRequest.class);
	   
	    SearchResponse resp = handler.handleRequest(req, createContext("create"));
	    Assert.assertEquals(200, resp.statusCode);
	}
	
	void testFailInput(String incoming, int failureCode) throws IOException {
		SearchHandler handler = new SearchHandler();
		SearchRequest req = new Gson().fromJson(incoming, SearchRequest.class);
	   
	    SearchResponse resp = handler.handleRequest(req, createContext("create"));
	    Assert.assertEquals(failureCode, resp.statusCode);
	}
	
	void testCorrectResponse(String responseList, int expected) throws IOException {
		SearchHandler handler = new SearchHandler();
		SearchRequest req = new Gson().fromJson(responseList, SearchRequest.class);
	   
	    SearchResponse resp = handler.handleRequest(req, createContext("create"));
	    System.out.println(resp.list.size());
	    //Video must = new Video("2019.12.12.20.46.01", "Trelane", "You must stay." , "https://3733dramaticexit.s3.us-east-2.amazonaws.com/Videos/2019.12.12.20.46.01.ogg");
	    Assert.assertEquals(expected, resp.list.size());
	}
	
	
	@Test
	public void testSuccessSearchKeyword() {
		System.out.println("Testing: search by keyword");
		String name = "";
		String keyword = "must";
		
		SearchRequest testKeyword = new SearchRequest(name, keyword);
	    String searchResult = new Gson().toJson(testKeyword);  
	    
	    try {
	    	testSuccessInput(searchResult);
	    } catch (IOException ioe) {
	    	Assert.fail("Invalid:" + ioe.getMessage());
	    }
	}
	
	@Test
	public void testCorrectSearchVideo() {
		System.out.println("Testing: search for specific video");
		String name = "Trelane";
		String keyword = "you must stay";
		
		SearchRequest testKeyword = new SearchRequest(name, keyword);
	    String searchResult = new Gson().toJson(testKeyword);  
	    
	    try {
	    	testCorrectResponse(searchResult, 1);
	    } catch (IOException ioe) {
	    	Assert.fail("Invalid:" + ioe.getMessage());
	    }
	}
	
	@Test
	public void testCorrectSearchKeyword() {
		System.out.println("Testing: search by keyword");
		String name = "Trelane";
		String keyword = "must";
		
		SearchRequest testKeyword = new SearchRequest(name, keyword);
	    String searchResult = new Gson().toJson(testKeyword);  
	    
	    try {
	    	testCorrectResponse(searchResult, 3);
	    } catch (IOException ioe) {
	    	Assert.fail("Invalid:" + ioe.getMessage());
	    }
	}
	
	@Test
	public void testCorrectSearchName() {
		System.out.println("Testing: search by name");
		String name = "Sulu";
		String keyword = "";
		
		SearchRequest testName = new SearchRequest(name, keyword);
	    String searchResult = new Gson().toJson(testName);  
	    
	    try {
	    	testCorrectResponse(searchResult, 1);
	    } catch (IOException ioe) {
	    	Assert.fail("Invalid:" + ioe.getMessage());
	    }
	}
	
	@Test
	public void testEmptySearch() {
		System.out.println("Testing: search everything");
		String name = "";
		String keyword = "";
		
		SearchRequest testName = new SearchRequest(name, keyword);
	    String searchResult = new Gson().toJson(testName);  
	    
	    try {
	    	testCorrectResponse(searchResult, 5);
	    } catch (IOException ioe) {
	    	Assert.fail("Invalid:" + ioe.getMessage());
	    }
	}

}
