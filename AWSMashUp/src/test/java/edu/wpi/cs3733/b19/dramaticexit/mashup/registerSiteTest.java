package edu.wpi.cs3733.b19.dramaticexit.mashup;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs3733.b19.dramaticexit.mashup.http.DeleteSiteRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.DeleteSiteResponse;
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
	    Assert.assertEquals(failureCode, resp.statusCode);
	}
	
	void testSuccessDelete(String incoming) throws IOException {
		DeleteSiteHandler handler = new DeleteSiteHandler();
		DeleteSiteRequest req = new Gson().fromJson(incoming, DeleteSiteRequest.class);
	   
	    DeleteSiteResponse resp = handler.handleRequest(req, createContext("create"));
	    Assert.assertEquals(200, resp.statusCode);
	}
	
	void testFailDelete(String incoming, int failureCode) throws IOException {
		DeleteSiteHandler handler = new DeleteSiteHandler();
		DeleteSiteRequest req = new Gson().fromJson(incoming, DeleteSiteRequest.class);
	   
	    DeleteSiteResponse resp = handler.handleRequest(req, createContext("create"));
	    Assert.assertEquals(failureCode, resp.statusCode);
	}
	
	@Test
	public void testNewSiteRegister() {
		System.out.println("Testing: new site register");
		String url = "www.jumanjiTest" + (int)Math.random() + ".com";
		RegisterSiteRequest testNew = new RegisterSiteRequest(url);
	    String newSite = new Gson().toJson(testNew);  
	    
	    try {
	    	testSuccessInput(newSite);
	    } catch (IOException ioe) {
	    	Assert.fail("Invalid:" + ioe.getMessage());
	    }
	}
	
	@Test
	public void testInvalidSiteRegister() {
		System.out.println("Testing: invalid site register");
		RegisterSiteRequest testNew = new RegisterSiteRequest();
	    String newSite = new Gson().toJson(testNew);  
	    
	    try {
	    	testFailInput(newSite, 400);
	    } catch (IOException ioe) {
	    	Assert.fail("Invalid:" + ioe.getMessage());
	    }
	}
	
	@Test
	public void testExistingSiteRegister() {
		System.out.println("Testing: existing site register");
		String url = "www.google.com";
		
		RegisterSiteRequest firstUpload = new RegisterSiteRequest(url);
	    String string1 = new Gson().toJson(firstUpload);
	    RegisterSiteHandler handler = new RegisterSiteHandler();
		RegisterSiteRequest req = new Gson().fromJson(string1, RegisterSiteRequest.class);
		RegisterSiteResponse resp = handler.handleRequest(req, createContext("create"));
		
		RegisterSiteRequest secondUpload = new RegisterSiteRequest(url);
	    String string2 = new Gson().toJson(secondUpload);  
	    
	    try {
	    	testFailInput(string2, 422);
	    } catch (IOException ioe) {
	    	Assert.fail("Invalid:" + ioe.getMessage());
	    }
	}
	
	@Test
	public void testDeleteExistingSite() {
		System.out.println("Testing: delete existing site");
		String url = "www.google.com";
		DeleteSiteRequest testOK = new DeleteSiteRequest(url);
	    String deleteExisting = new Gson().toJson(testOK);  
	    
	    try {
	    	testSuccessDelete(deleteExisting);
	    } catch (IOException ioe) {
	    	Assert.fail("Invalid:" + ioe.getMessage());
	    }
	}
	
	@Test
	public void testDeleteNonExistingSite() {
		System.out.println("Testing: delete non-existing site");
		String url = "www.IDon'tExist.com";
		DeleteSiteRequest testOK = new DeleteSiteRequest(url);
	    String deleteExisting = new Gson().toJson(testOK);  
	    
	    try {
	    	testFailDelete(deleteExisting, 422);
	    } catch (IOException ioe) {
	    	Assert.fail("Invalid:" + ioe.getMessage());
	    }
	}

}
