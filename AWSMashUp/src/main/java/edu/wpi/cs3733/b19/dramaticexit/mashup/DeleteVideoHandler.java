package edu.wpi.cs3733.b19.dramaticexit.mashup;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs3733.b19.dramaticexit.mashup.db.VideosDAO;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.DeleteVideoRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.DeleteVideoResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Video;

public class DeleteVideoHandler implements RequestHandler<DeleteVideoRequest,DeleteVideoResponse>{
	
	LambdaLogger logger;
	
	/** Find in RDS.
	 * 
	 * @throws Exception 
	 */
	boolean deleteVideo(String id) throws Exception {
//		logger.log("in deleteVideo");
		VideosDAO dao = new VideosDAO();

		//check if present
		Video exist = dao.getVideo(id);
		if(exist == null) {
			System.out.println("video does not exist");
			return false;
		} else {
			System.out.println("video exists");
			return dao.deleteVideo(id);
		}
	}
	
	@Override
	public DeleteVideoResponse handleRequest(DeleteVideoRequest req, Context context) {
		logger = context.getLogger();
		System.out.println("Loading Java Lambda handler to delete video");
		logger.log(req.toString());

		DeleteVideoResponse response;
		try {
			if (deleteVideo(req.videoID)) {
				response = new DeleteVideoResponse(req.videoID, 200);
			} else {
				response = new DeleteVideoResponse(req.videoID, 422, "Unable to delete video.");
			}
		} catch (Exception e) {
			response = new DeleteVideoResponse(req.videoID, 403, "Unable to delete video: " + req.videoID + "(" + e.getMessage() + ")");
		}

		return response;
	}

}
