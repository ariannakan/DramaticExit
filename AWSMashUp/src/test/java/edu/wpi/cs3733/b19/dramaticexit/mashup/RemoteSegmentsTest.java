package edu.wpi.cs3733.b19.dramaticexit.mashup;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import edu.wpi.cs3733.b19.dramaticexit.mashup.http.RemoteSegmentsResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Segment;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class RemoteSegmentsTest extends LambdaTest {
	
    @Test
    public void testGetList() throws IOException {
    	RemoteSiteHandler handler = new RemoteSiteHandler();

        RemoteSegmentsResponse resp = handler.handleRequest(null, createContext("list"));
        
        boolean hasTest0 = false;
        for (Segment c : resp.segments) {
        	System.out.println(c.character);
        	if (c.character.equals("Trelane")) { hasTest0 = true; break; }
        }
        
        System.out.println(hasTest0);
        Assert.assertTrue(hasTest0);
        Assert.assertEquals(resp.segments.size(), 4); //one video is not available
        Assert.assertEquals(200, resp.statusCode);
    }

}
