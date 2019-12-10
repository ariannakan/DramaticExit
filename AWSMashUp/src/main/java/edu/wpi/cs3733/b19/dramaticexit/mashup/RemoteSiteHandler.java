package edu.wpi.cs3733.b19.dramaticexit.mashup;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs3733.b19.dramaticexit.mashup.http.RemoteSegmentsResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.SearchResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Video;
import edu.wpi.cs3733.b19.dramaticexit.mashup.db.VideosDAO;

public class RemoteSiteHandler implements RequestHandler<Object,RemoteSegmentsResponse>{

	public LambdaLogger logger;
	
	List<Video> AvailableForRemote() throws Exception {
		VideosDAO dao = new VideosDAO();

		return dao.AvailableForRemote();
	}
	
	@Override
	public RemoteSegmentsResponse handleRequest(Object input, Context context)  {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all remote segments");

		RemoteSegmentsResponse response;
		try {
			List<Video> list = AvailableForRemote();
			response = new RemoteSegmentsResponse(list, 200);
		} catch (Exception e) {
			response = new RemoteSegmentsResponse(403, e.getMessage());
		}
		
		return response;
	}
//		Segment one = new Segment("https://cs3733wpi.s3.amazonaws.com/segments/output1.ogg", "worker", "one to beam up");
//		Segment two = new Segment("https://cs3733wpi.s3.amazonaws.com/segments/output2.ogg", "bones", "he's dead, Jim.");
//		Segment three = new Segment("https://cs3733wpi.s3.amazonaws.com/segments/output3.ogg", "worker", "Kirk, Out.");
//		
//		List<Segment> list = new ArrayList<Segment>();
//		list.add(one);
//		list.add(two);
//		list.add(three);
//		
//		RemoteSegmentsResponse response = new RemoteSegmentsResponse(list, 200);
}
