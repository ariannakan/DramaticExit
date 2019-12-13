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
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.UpdateVideoRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.UpdateVideoResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.UploadVideoRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.UploadVideoResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Video;

/** 
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class uploadVideoTest extends LambdaTest{
	
	 //File oggfile = new File("src/test/resources/videoseg2converted.ogg");

	void testSuccessInput(String incoming) throws IOException {
		UploadVideoHandler handler = new UploadVideoHandler();
		UploadVideoRequest req = new Gson().fromJson(incoming, UploadVideoRequest.class);
	   
	    UploadVideoResponse resp = handler.handleRequest(req, createContext("create"));
	    System.out.println(resp.toString());
	    
	    Assert.assertEquals(200, resp.statusCode);
	    
	    DeleteVideoRequest deleteThis = new DeleteVideoRequest(resp.response);
		String SAMPLE =  new Gson().toJson(deleteThis); 
		DeleteVideoHandler deletehandler = new DeleteVideoHandler();
		DeleteVideoRequest dreq = new Gson().fromJson(SAMPLE, DeleteVideoRequest.class);
	   
	    DeleteVideoResponse dresp = deletehandler.handleRequest(dreq, createContext("create"));
	    System.out.println(dresp.toString());
	}
	
	void testFailInput(String incoming, int failureCode) throws IOException {
		UploadVideoHandler handler = new UploadVideoHandler();
		UploadVideoRequest req = new Gson().fromJson(incoming, UploadVideoRequest.class);
	   
	    UploadVideoResponse resp = handler.handleRequest(req, createContext("create"));
	    System.out.println(resp.toString());
	    
	    Assert.assertEquals(failureCode, resp.statusCode);
	}
	
	void testSuccessDelete(String incoming) throws IOException {
		DeleteVideoHandler handler = new DeleteVideoHandler();
		DeleteVideoRequest req = new Gson().fromJson(incoming, DeleteVideoRequest.class);
	   
	    DeleteVideoResponse resp = handler.handleRequest(req, createContext("create"));
	    System.out.println(resp.toString());
	    
	    Assert.assertEquals(200, resp.statusCode);
	}
	
	void testFailDelete(String incoming, int failureCode) throws IOException {
		DeleteVideoHandler handler = new DeleteVideoHandler();
		DeleteVideoRequest req = new Gson().fromJson(incoming, DeleteVideoRequest.class);
	   
	    DeleteVideoResponse resp = handler.handleRequest(req, createContext("create"));
	    System.out.println(resp.toString());
	    
	    Assert.assertEquals(failureCode, resp.statusCode);
	}
	
	void testSuccessList(String incoming) throws IOException {
		ListAllVideosHandler handler = new ListAllVideosHandler();
		ListVideosRequest req = new Gson().fromJson(incoming, ListVideosRequest.class);
	   
		AllVideosResponse resp = handler.handleRequest(req, createContext("create"));
		System.out.println(resp.toString());
		
	    Assert.assertEquals(200, resp.statusCode);
	}
	
	void testSuccessUpdate(String incoming) throws IOException {
		UpdateVideoHandler handler = new UpdateVideoHandler();
		UpdateVideoRequest req = new Gson().fromJson(incoming, UpdateVideoRequest.class);
	   
		UpdateVideoResponse resp = handler.handleRequest(req, createContext("create"));
		System.out.println(resp.toString());
		
	    Assert.assertEquals(200, resp.statusCode);
	}
	
	void testFailUpdate(String incoming, int failureCode) throws IOException {
		UpdateVideoHandler handler = new UpdateVideoHandler();
		UpdateVideoRequest req = new Gson().fromJson(incoming, UpdateVideoRequest.class);
	   
		UpdateVideoResponse resp = handler.handleRequest(req, createContext("create"));
		System.out.println(resp.toString());
		
	    Assert.assertEquals(failureCode, resp.statusCode);
	} 
	
	@Test
	public void testCreateVideoObject() {
		System.out.println("Testing: testCreateVideoObject");
		
		String characterName = "Pom";
		String sentence = "Where are you, Pom?";
		String videoID = "508-410-7005";
		String url = "www.Thailand.com";
		
		Video pom = new Video(videoID, characterName, sentence, url);
		Video monoBoy = new Video();
		monoBoy.setCharacterName(pom.getCharacterName());
		monoBoy.setSentence(pom.getSentence());
		monoBoy.setUrl(pom.getUrl());
		monoBoy.setVideoID(pom.getVideoID());
		
		Assert.assertTrue(pom.equals(monoBoy));
		Assert.assertFalse(pom.equals("hello"));
		Assert.assertFalse(pom.equals(null));
    }
	
	@Test
	public void testCreateVideoObject2() {
		System.out.println("Testing: testCreateVideoObject2");
		
		String videoID = "1234";
		String url = "www.bye.com";
		
		Video ari = new Video(videoID, url);
		Video hi = new Video();
		hi.setVideoID(ari.getVideoID());
		hi.setUrl(ari.getUrl());
		
		Assert.assertTrue(ari.equals(hi));
    }
	
	@Test
	public void testUploadVideoRequest() {
		System.out.println("Testing: testUploadVideoRequest");
		
		String characterName = "Pom";
		String sentence = "Where are you, Pom?";
		String videoID = "508-410-7005";
		boolean availability = true;
		boolean system = false;
		String encoding = "asdfasdgsdf";
		
		UploadVideoRequest pom = new UploadVideoRequest(videoID, characterName, sentence, availability, encoding, system);
		UploadVideoRequest monoBoy = new UploadVideoRequest();
		monoBoy.setCharacterName(pom.getCharacterName());
		monoBoy.setSentence(pom.getSentence());
		monoBoy.setAvailability(pom.getAvailability());
		monoBoy.setVideoID(pom.getVideoID());
		monoBoy.setBase64EncodedValue(pom.getBase64EncodedValue());
		monoBoy.setSystem(pom.getSystem());
		
		
		Assert.assertTrue(pom.equals(monoBoy));
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
	
//	@Test
//    public void testFailUpload() {
//		System.out.println("Testing: nonexisting video input");
//		
//    	UploadVideoRequest testInvalid = new UploadVideoRequest("I", "don't", "exist");
//    	String SAMPLE_INPUT_STRING =  new Gson().toJson(testInvalid);  
//        
//        try {
//        	testFailInput(SAMPLE_INPUT_STRING, 400);
//        } catch (IOException ioe) {
//        	Assert.fail("Invalid:" + ioe.getMessage());
//        }
//    }
	
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
		    
		    System.out.println("upload video response: " + resp.response);
		
			
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
		
		byte[] fileContent;
		try {
			DeleteVideoRequest testDelete = new DeleteVideoRequest();
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
	
	@Test
    public void testUpdateVideoSuccess() {
		System.out.println("Testing: update video");
		String videoID = "2019.12.12.20.46.12";
		
    	UpdateVideoRequest update = new UpdateVideoRequest(videoID, false);
    	String SAMPLE_INPUT_STRING =  new Gson().toJson(update);  
    	
    	System.out.println(SAMPLE_INPUT_STRING);
        
        try {
        	testSuccessUpdate(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
	
	@Test
    public void testUpdateVideoFail() {
		System.out.println("Testing: update video with nonexisting video");
		
    	UpdateVideoRequest update = new UpdateVideoRequest("", false);
    	String SAMPLE_INPUT_STRING =  new Gson().toJson(update);  
    	
    	System.out.println(SAMPLE_INPUT_STRING);
        
        try {
        	testFailUpdate(SAMPLE_INPUT_STRING, 422);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
	
}
