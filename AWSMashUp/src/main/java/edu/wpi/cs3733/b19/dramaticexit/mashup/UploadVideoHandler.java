package edu.wpi.cs3733.b19.dramaticexit.mashup;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import edu.wpi.cs3733.b19.dramaticexit.mashup.db.VideosDAO;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.UploadVideoRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.UploadVideoResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Video;

public class UploadVideoHandler implements RequestHandler<UploadVideoRequest,UploadVideoResponse> {
	
	// I am leaving in this S3 code so it can be a LAST RESORT if the constant is not in the database
	private AmazonS3 s3 = null;
	
	LambdaLogger logger;
	
	/**
	 * Try to get from RDS first. Then get from bucket.
	 * 
	 * @param arg
	 * @return
	 * @throws Exception
	 */
	public double loadVideo(String arg) throws Exception {
		double val = 0;
		try {
			val = loadVideoIDFromRDS(arg);
			return val;
		} catch (Exception e) {
			return getDoubleFromBucket(arg);
		}
	}
	
	/** Load from RDS, if it exists
	 * 
	 * @throws Exception 
	 */
	String loadVideoIDFromRDS(String arg) throws Exception {
		if (logger != null) { logger.log("in loadValue"); }
		VideosDAO dao = new VideosDAO();
		Video video = dao.getVideo(arg);
		return video.videoID;
	}
	
	@Override
	public UploadVideoResponse handleRequest(UploadVideoRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		logger.log(req.toString());

		boolean fail = false;
		String failMessage = "";
		double val1 = 0.0;
		try {
			val1 = Double.parseDouble(req.getArg1());
		} catch (NumberFormatException e) {
			try {
				val1 = loadConstant(req.getArg1());
			} catch (Exception ex) {
				failMessage = req.getArg1() + " is an invalid constant.";
				fail = true;
			}
		}

		double val2 = 0.0;
		try {
			val2 = Double.parseDouble(req.getArg2());
		} catch (NumberFormatException e) {
			try {
				val2 = loadConstant(req.getArg2());
			} catch (Exception ex) {
				failMessage = req.getArg2() + " is an invalid constant.";
				fail = true;
			}
		}

		// compute proper response and return. Note that the status code is internal to the HTTP response
		// and has to be processed specifically by the client code.
		UploadVideoResponse response;
		if (fail) {
			response = new UploadVideoResponse(failMessage, 400);
		} else {
			response = new UploadVideoResponse(val1 + val2, 200);  // success
		}

		return response; 
	}
	

}
