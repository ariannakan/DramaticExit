package edu.wpi.cs3733.b19.dramaticexit.mashup;

import java.io.IOException;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs3733.b19.dramaticexit.mashup.http.*;

public class ListRemoteVideosTest extends LambdaTest {
	
	void testSuccessList(String incoming) throws IOException {
		ListAllRemoteVideosHandler handler = new ListAllRemoteVideosHandler();
		ListAllRemoteVideosRequest req = new Gson().fromJson(incoming, ListAllRemoteVideosRequest.class);
	   
		ListAllRemoteVideosResponse resp = handler.handleRequest(req, createContext("create"));
	    Assert.assertEquals(200, resp.statusCode);
	}

	@Test
	public void testListRemoteVideosSuccess() {
		System.out.println("Testing: listing remote videos");
		
		ListAllRemoteVideosRequest list = new ListAllRemoteVideosRequest();
		String listRemoteVideos = new Gson().toJson(list);
		
		try {
	    	testSuccessList(listRemoteVideos);
	    } catch (IOException ioe) {
	    	Assert.fail("Invalid:" + ioe.getMessage());
	    }

	}

}
