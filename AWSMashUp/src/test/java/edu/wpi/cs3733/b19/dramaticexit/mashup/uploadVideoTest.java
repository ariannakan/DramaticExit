//tests both upload and delete and list
package edu.wpi.cs3733.b19.dramaticexit.mashup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs3733.b19.dramaticexit.mashup.http.AllVideosResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.DeleteVideoRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.DeleteVideoResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.ListVideosRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.UploadVideoRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.UploadVideoResponse;

/** 
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class uploadVideoTest extends LambdaTest{
	
	 //File oggfile = new File("src/test/resources/videoseg2converted.ogg");

	void testSuccessInput(String incoming) throws IOException {
		UploadVideoHandler handler = new UploadVideoHandler();
		UploadVideoRequest req = new Gson().fromJson(incoming, UploadVideoRequest.class);
	   
	    UploadVideoResponse resp = handler.handleRequest(req, createContext("create"));
	    Assert.assertEquals(200, resp.statusCode);
	}
	
	void testFailInput(String incoming, int failureCode) throws IOException {
		UploadVideoHandler handler = new UploadVideoHandler();
		UploadVideoRequest req = new Gson().fromJson(incoming, UploadVideoRequest.class);
	   
	    UploadVideoResponse resp = handler.handleRequest(req, createContext("create"));
	    Assert.assertEquals(failureCode, resp.statusCode);
	}
	
	void testSuccessDelete(String incoming) throws IOException {
		DeleteVideoHandler handler = new DeleteVideoHandler();
		DeleteVideoRequest req = new Gson().fromJson(incoming, DeleteVideoRequest.class);
	   
	    DeleteVideoResponse resp = handler.handleRequest(req, createContext("create"));
	    Assert.assertEquals(200, resp.statusCode);
	}
	
	void testFailDelete(String incoming, int failureCode) throws IOException {
		DeleteVideoHandler handler = new DeleteVideoHandler();
		DeleteVideoRequest req = new Gson().fromJson(incoming, DeleteVideoRequest.class);
	   
	    DeleteVideoResponse resp = handler.handleRequest(req, createContext("create"));
	    Assert.assertEquals(failureCode, resp.statusCode);
	}
	
	void testSuccessList(String incoming) throws IOException {
		ListAllVideosHandler handler = new ListAllVideosHandler();
		ListVideosRequest req = new Gson().fromJson(incoming, ListVideosRequest.class);
	   
		AllVideosResponse resp = handler.handleRequest(req, createContext("create"));
	    Assert.assertEquals(200, resp.statusCode);
	}
	
	@Test
	public void testFirstUpload() {
		System.out.println("Testing: testFirstUpload");
		String characterName = "Sulu";
		String sentence = "Sir, Is he kidding?";

		try {
			byte[] fileContent = Files.readAllBytes(Paths.get("src/test/resources/videoseg6.ogg"));
			String encoded = java.util.Base64.getEncoder().encodeToString(fileContent);
			
	    	UploadVideoRequest testOK = new UploadVideoRequest(characterName, sentence, encoded);
	        String SAMPLE_INPUT_STRING = new Gson().toJson(testOK);  

        	testSuccessInput(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
	
	@Test
    public void testEmptyUpload() {
		System.out.println("Testing: Empty input");
		
    	UploadVideoRequest testInvalid = new UploadVideoRequest();
    	String SAMPLE_INPUT_STRING =  new Gson().toJson(testInvalid);  
        
        try {
        	testFailInput(SAMPLE_INPUT_STRING, 400);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
	
	@Test
	public void testDeleteVideo() {
		System.out.println("Testing: Delete video");
		String characterName = "Jamie";
		String sentence = "There you are. I just wanted one more look at you.";
		
		byte[] fileContent;
		try {
			fileContent = Files.readAllBytes(Paths.get("src/test/resources/videoseg10.ogg"));
			String encoded = java.util.Base64.getEncoder().encodeToString(fileContent);
			
	    	UploadVideoRequest testOK = new UploadVideoRequest(characterName, sentence, encoded);
	        String SAMPLE_INPUT_STRING = new Gson().toJson(testOK);  
	        
	        UploadVideoHandler handler = new UploadVideoHandler();
			UploadVideoRequest req = new Gson().fromJson(SAMPLE_INPUT_STRING, UploadVideoRequest.class);
		   
		    UploadVideoResponse resp = handler.handleRequest(req, createContext("create"));
		    
		    System.out.println(resp.response);
		
			
			DeleteVideoRequest testDelete = new DeleteVideoRequest(resp.response);
			String SAMPLE =  new Gson().toJson(testDelete); 
			
			testSuccessDelete(SAMPLE);
		} catch (IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}
	}
	
	@Test
	public void testEmptyDeleteVideo() {
		System.out.println("Testing: Delete video with incorrect input");
		String characterName = "Jamie";
		String sentence = "There you are. I just wanted one more look at you.";
		
		byte[] fileContent;
		try {
			fileContent = Files.readAllBytes(Paths.get("src/test/resources/videoseg10.ogg"));
			String encoded = java.util.Base64.getEncoder().encodeToString(fileContent);
			
	    	UploadVideoRequest testOK = new UploadVideoRequest(characterName, sentence, encoded);
	        String SAMPLE_INPUT_STRING = new Gson().toJson(testOK);  
	        
	        UploadVideoHandler handler = new UploadVideoHandler();
			UploadVideoRequest req = new Gson().fromJson(SAMPLE_INPUT_STRING, UploadVideoRequest.class);
		   
		    UploadVideoResponse resp = handler.handleRequest(req, createContext("create"));
		    
		    System.out.println(resp.response);
		
			
			DeleteVideoRequest testDelete = new DeleteVideoRequest(resp.toString());
			String SAMPLE =  new Gson().toJson(testDelete); 
			
			testFailDelete(SAMPLE, 422);
		} catch (IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}
	}
	
	@Test
    public void testListVideos() {
		System.out.println("Testing: List videos");
		
    	ListVideosRequest list = new ListVideosRequest();
    	String SAMPLE_INPUT_STRING =  new Gson().toJson(list);  
        
        try {
        	testSuccessList(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
	
}
