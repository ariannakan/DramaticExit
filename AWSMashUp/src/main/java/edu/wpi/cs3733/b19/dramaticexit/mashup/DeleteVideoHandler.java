package edu.wpi.cs3733.b19.dramaticexit.mashup;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs3733.b19.dramaticexit.mashup.db.VideosDAO;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.DeleteVideoRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.DeleteVideoResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Video;

public class DeleteVideoHandler implements RequestHandler<DeleteVideoRequest,DeleteVideoResponse>{
	
	public LambdaLogger logger = null;
	
	boolean deleteVideo(String id) throws Exception {
		VideosDAO dao = new VideosDAO();

		//check if present
		Video exist = dao.getVideo(id);
		if(exist == null) {
			logger.log("false: video does not exist");
			return false;
		} else {
			return dao.deleteVideo(id);
		}
	}
	
	@Override
	public DeleteVideoResponse handleRequest(DeleteVideoRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to delete video");

		DeleteVideoResponse response;

		try {
			if (deleteVideo(req.videoID)) {
				response = new DeleteVideoResponse(req.videoID);
			} else {
				response = new DeleteVideoResponse(req.videoID, 422, "Unable to delete video.");
			}
		} catch (Exception e) {
			response = new DeleteVideoResponse(req.videoID, 403, "Unable to delete video: " + req.videoID + "(" + e.getMessage() + ")");
		}

		return response;
	}

}
