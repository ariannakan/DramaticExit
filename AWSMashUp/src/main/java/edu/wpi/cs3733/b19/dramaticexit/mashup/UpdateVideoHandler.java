package edu.wpi.cs3733.b19.dramaticexit.mashup;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs3733.b19.dramaticexit.mashup.db.VideosDAO;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.UpdateVideoRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.UpdateVideoResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.UploadVideoResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Video;


public class UpdateVideoHandler implements RequestHandler<UpdateVideoRequest,UpdateVideoResponse>{

	LambdaLogger logger;
	
	boolean updateVideo(String videoID, boolean availability) throws Exception {
		VideosDAO dao = new VideosDAO();
		
		//check if video is in RDS
		Video exist = dao.getVideo(videoID);
		if(exist == null){
			System.out.println("video does not exist");
			return false;
		}
		else {
			return dao.updateVideo(videoID, availability);
		}
	}
	
	@Override
	public UpdateVideoResponse handleRequest(UpdateVideoRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());

		UpdateVideoResponse response;
		try {
			if (updateVideo(req.videoID, req.availability)) {
				response = new UpdateVideoResponse(req.videoID, req.availability);
			} else {
				response = new UpdateVideoResponse(req.videoID, req.availability, 422, "unable to update video");
			}
		} catch (Exception e) {
			response = new UpdateVideoResponse("Unable to update video: " + req.videoID + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}

}
