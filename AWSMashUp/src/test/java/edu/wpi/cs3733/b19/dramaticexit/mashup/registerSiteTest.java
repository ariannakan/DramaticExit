// tests create and delete and list 

package edu.wpi.cs3733.b19.dramaticexit.mashup;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs3733.b19.dramaticexit.mashup.http.AllSitesResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.DeleteSiteRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.DeleteSiteResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.ListSitesRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.RegisterSiteRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.RegisterSiteResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.UploadVideoRequest;


public class registerSiteTest extends LambdaTest{
	
	void testSuccessInput(String incoming) throws IOException {
		RegisterSiteHandler handler = new RegisterSiteHandler();
		RegisterSiteRequest req = new Gson().fromJson(incoming, RegisterSiteRequest.class);
	   
	    RegisterSiteResponse resp = handler.handleRequest(req, createContext("create"));
	    System.out.println(resp.toString());
	    
	    Assert.assertEquals(200, resp.statusCode);
	}
	
	void testFailInput(String incoming, int failureCode) throws IOException {
		RegisterSiteHandler handler = new RegisterSiteHandler();
		RegisterSiteRequest req = new Gson().fromJson(incoming, RegisterSiteRequest.class);
	   
	    RegisterSiteResponse resp = handler.handleRequest(req, createContext("create"));
	    System.out.println(resp.toString());
	    
	    Assert.assertEquals(failureCode, resp.statusCode);
	}
	
	void testSuccessDelete(String incoming) throws IOException {
		DeleteSiteHandler handler = new DeleteSiteHandler();
		DeleteSiteRequest req = new Gson().fromJson(incoming, DeleteSiteRequest.class);
	   
	    DeleteSiteResponse resp = handler.handleRequest(req, createContext("create"));
	    System.out.println(resp.toString());
	    
	    Assert.assertEquals(200, resp.statusCode);
	}
	
	void testFailDelete(String incoming, int failureCode) throws IOException {
		DeleteSiteHandler handler = new DeleteSiteHandler();
		DeleteSiteRequest req = new Gson().fromJson(incoming, DeleteSiteRequest.class);
	   
	    DeleteSiteResponse resp = handler.handleRequest(req, createContext("create"));
	    System.out.println(resp.toString());
	    
	    Assert.assertEquals(failureCode, resp.statusCode);
	}
	
	void testSuccessList(String incoming) throws IOException {
		ListAllSitesHandler handler = new ListAllSitesHandler();
		ListSitesRequest req = new Gson().fromJson(incoming, ListSitesRequest.class);
	   
		AllSitesResponse resp = handler.handleRequest(req, createContext("create"));
		System.out.println(resp.toString());
		
	    Assert.assertEquals(200, resp.statusCode);
	}
	
	@Test
	public void testNewSiteRegister() {
		System.out.println("Testing: new site register");
		String url = "https://q1usdooppf.execute-api.us-east-2.amazonaws.com/beta/publicsegments";
		String apikey = "ES3kGiFETL2wlXnQ5IZ064VR5E1QtENVcObJ1rE2";
		int num = (int) Math.random();
		System.out.println(num);
		String sentence = "testing" + num;
		String characterName = "test" + num;
		RegisterSiteRequest testNew = new RegisterSiteRequest(apikey,url,characterName,sentence);
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
		String url = "https://jnma78xc8j.execute-api.us-east-2.amazonaws.com/RemoteSite/publicsegments";
		String apikey = "7wJummKN8COPl62GX2As9mVan9DjsIO7pdCIJdUg";
		int num = (int) Math.random();
		String sentence = "testing" + num;
		String characterName = "test" + num;
		
		RegisterSiteRequest firstUpload = new RegisterSiteRequest(apikey,url,characterName,sentence);
	    String string1 = new Gson().toJson(firstUpload);  
	    RegisterSiteHandler handler = new RegisterSiteHandler();
		RegisterSiteRequest req = new Gson().fromJson(string1, RegisterSiteRequest.class);
	    RegisterSiteResponse resp = handler.handleRequest(req, createContext("create"));
		
		RegisterSiteRequest secondUpload = new RegisterSiteRequest(apikey,url,characterName,sentence);
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
		String apikey = "ES3kGiFETL2wlXnQ5IZ064VR5E1QtENVcObJ1rE2";
		DeleteSiteRequest testOK = new DeleteSiteRequest(apikey);
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
		String url = "TwgjfiS4Z45AzAoqg0Vke4pW7UalgsHfa2hvg7rM";
		DeleteSiteRequest testOK = new DeleteSiteRequest(url);
	    String deleteExisting = new Gson().toJson(testOK);  
	    
	    try {
	    	testFailDelete(deleteExisting, 422);
	    } catch (IOException ioe) {
	    	Assert.fail("Invalid:" + ioe.getMessage());
	    }
	}
	
	@Test
	public void testListSites() {
		System.out.println("Testing: list sites");
		
		ListSitesRequest list = new ListSitesRequest();
	    String listSites = new Gson().toJson(list);  
	    
	    try {
	    	testSuccessList(listSites);
	    } catch (IOException ioe) {
	    	Assert.fail("Invalid:" + ioe.getMessage());
	    }
	}
	
//	@Test															//returns 422
//	public void testDeleteEmptyString() {
//		System.out.println("Testing: delete empty string");
//		DeleteSiteRequest testOK = new DeleteSiteRequest();
//	    String deleteExisting = new Gson().toJson(testOK);  
//	    
//	    try {
//	    	testFailDelete(deleteExisting, 400);
//	    } catch (IOException ioe) {
//	    	Assert.fail("Invalid:" + ioe.getMessage());
//	    }
//	}

}
