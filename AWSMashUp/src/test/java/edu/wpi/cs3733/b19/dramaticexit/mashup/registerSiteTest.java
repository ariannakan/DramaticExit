package edu.wpi.cs3733.b19.dramaticexit.mashup;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs3733.b19.dramaticexit.mashup.http.RegisterSiteRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.RegisterSiteResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.UploadVideoRequest;


public class registerSiteTest extends LambdaTest{
	
	void testSuccessInput(String incoming) throws IOException {
		RegisterSiteHandler handler = new RegisterSiteHandler();
		RegisterSiteRequest req = new Gson().fromJson(incoming, RegisterSiteRequest.class);
	   
	    RegisterSiteResponse resp = handler.handleRequest(req, createContext("create"));
	    Assert.assertEquals(200, resp.statusCode);
	}
	
	void testFailInput(String incoming, int failureCode) throws IOException {
		RegisterSiteHandler handler = new RegisterSiteHandler();
		RegisterSiteRequest req = new Gson().fromJson(incoming, RegisterSiteRequest.class);
	   
	    RegisterSiteResponse resp = handler.handleRequest(req, createContext("create"));
	    Assert.assertEquals(422, resp.statusCode);
	}
	
	@Test
	public void testRegister() {
		String url = "www.google.com";
		RegisterSiteRequest testOK = new RegisterSiteRequest(url);
	    String SAMPLE_INPUT_STRING = new Gson().toJson(testOK);  
	    
	    
	    try {
	    	testSuccessInput(SAMPLE_INPUT_STRING);
	    } catch (IOException ioe) {
	    	Assert.fail("Invalid:" + ioe.getMessage());
	    }
	}

}
