package edu.wpi.cs3733.b19.dramaticexit.mashup;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs3733.b19.dramaticexit.mashup.http.UploadVideoRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.UploadVideoResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class uploadVideoTest extends LambdaTest{
	
	 File oggfile = new File("src/test/resources/videoseg2.ogg");

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
	
	@Test
	public void testShouldBeOk() {
		System.out.println("Testing: testShouldBeOK");
		String videoID = "vid1";
		String characterName = "Spock";
		String sentence = "Not again";
		File oggFile = oggfile;
		System.out.println(oggFile);
		boolean availability = true;
		
    	UploadVideoRequest testOK = new UploadVideoRequest(videoID, characterName, sentence, oggFile, availability);
    	//String SAMPLE_INPUT_STRING = "{\"name\": \"" + var + "\", \"value\": \"Mi43MTgyODE4Mjg=\"}";
        String SAMPLE_INPUT_STRING = new Gson().toJson(testOK);  
        
        
        try {
        	testSuccessInput(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
	
//	@Test
//    public void testFailInput() {
//		System.out.println("Testing: testFailInput");
//		String videoID = "vid2";
//		String characterName = "Kirk";
//		String sentence = "Here we go again";
//		String oggFile = resources.getAbsolutePath();
//		boolean availability = false;
//		
//    	UploadVideoRequest testFalse = new UploadVideoRequest(videoID, characterName, sentence, oggFile, availability);
//    	String SAMPLE_INPUT_STRING =  new Gson().toJson(testFalse);  
//        
//        try {
//        	testFailInput(SAMPLE_INPUT_STRING, 400);
//        } catch (IOException ioe) {
//        	Assert.fail("Invalid:" + ioe.getMessage());
//        }
//    }
}
