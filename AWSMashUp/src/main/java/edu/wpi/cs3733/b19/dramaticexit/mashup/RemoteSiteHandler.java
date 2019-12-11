package edu.wpi.cs3733.b19.dramaticexit.mashup;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs3733.b19.dramaticexit.mashup.http.RemoteSegmentsResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.SearchResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Segment;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Video;
import edu.wpi.cs3733.b19.dramaticexit.mashup.db.VideosDAO;

public class RemoteSiteHandler implements RequestHandler<Object,RemoteSegmentsResponse>{

	public LambdaLogger logger;
	
	List<Segment> AvailableForRemote() throws Exception {
		VideosDAO dao = new VideosDAO();

		return dao.AvailableForRemote();
	}
	
	@Override
	public RemoteSegmentsResponse handleRequest(Object input, Context context)  {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all remote segments");

		RemoteSegmentsResponse response;
		try {
			List<Segment> list = AvailableForRemote();
			response = new RemoteSegmentsResponse(list, 200);
		} catch (Exception e) {
			response = new RemoteSegmentsResponse(403, e.getMessage());
		}
		
		return response;
	}

}
