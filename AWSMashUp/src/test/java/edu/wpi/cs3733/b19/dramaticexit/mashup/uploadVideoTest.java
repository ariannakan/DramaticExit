package edu.wpi.cs3733.b19.dramaticexit.mashup;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs3733.b19.dramaticexit.mashup.http.DeleteVideoRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.DeleteVideoResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.UploadVideoRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.UploadVideoResponse;

/** 
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class uploadVideoTest extends LambdaTest{
	
	 File oggfile = new File("src/test/resources/videoseg2converted.ogg");

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
	
	@Test
	public void testFirstUpload() {
		System.out.println("Testing: testFirstUpload");
		String videoID = "vid1";
		String characterName = "Spock";
		String sentence = "Not again";
		File oggFile = oggfile;
		System.out.println(oggFile);
		boolean availability = true;
		
    	UploadVideoRequest testOK = new UploadVideoRequest(characterName, sentence, oggFile);
        String SAMPLE_INPUT_STRING = new Gson().toJson(testOK);  
        
        
        try {
        	testSuccessInput(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
	
	@Test
    public void testSameUpload() {
		System.out.println("Testing: test");
		String videoID = "vid1";
		String characterName = "Spock";
		String sentence = "Not again";
		File oggFile = oggfile;
		System.out.println(oggFile);
		boolean availability = true;
		
    	UploadVideoRequest testFalse = new UploadVideoRequest(characterName, sentence, oggFile);
    	String SAMPLE_INPUT_STRING =  new Gson().toJson(testFalse);  
        
        try {
        	testFailInput(SAMPLE_INPUT_STRING, 422);
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
		String characterName = "testingDelete";
		String sentence = "okie-dokie";
		File oggFile = oggfile;
		
    	UploadVideoRequest input = new UploadVideoRequest(characterName, sentence, oggFile);
    	String SAMPLE = new Gson().toJson(input);  
    	UploadVideoHandler handler = new UploadVideoHandler();
		UploadVideoRequest req = new Gson().fromJson(SAMPLE, UploadVideoRequest.class);
	   
	    UploadVideoResponse resp = handler.handleRequest(req, createContext("create"));
	    
	    System.out.println(resp.videoID);
	
		
		DeleteVideoRequest testDelete = new DeleteVideoRequest(resp.videoID);
		String SAMPLE_INPUT_STRING =  new Gson().toJson(testDelete); 
		
		try {
        	testSuccessDelete(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) { 
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
	}
}
