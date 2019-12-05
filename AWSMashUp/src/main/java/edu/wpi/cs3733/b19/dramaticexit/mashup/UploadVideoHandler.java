package edu.wpi.cs3733.b19.dramaticexit.mashup;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import edu.wpi.cs3733.b19.dramaticexit.mashup.db.VideosDAO;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.UploadVideoRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.UploadVideoResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Video;

public class UploadVideoHandler implements RequestHandler<UploadVideoRequest,UploadVideoResponse> {
	
LambdaLogger logger;
	
	// To access S3 storage
	private AmazonS3 s3 = null;
	
	String bucket = "b19dramaticexit";
	
	boolean useTestDB() {
		if(System.getenv("TESTING") != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	/** Create S3 bucket
	 * 
	 * @throws Exception 
	 */
	boolean uploadVideo(File oggFile, String videoID, String characterName, String sentence, boolean availability) throws Exception {
		if(useTestDB()){ bucket = "3733dramaticexit"; }
		if (logger != null) { logger.log("in uploadVideo"); }
		
		if (s3 == null) {
			logger.log("attach to S3 request");
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
			logger.log("attach to S3 succeed");
		}
		
		InputStream inputstream = new FileInputStream(oggFile);
		ObjectMetadata omd = new ObjectMetadata();
		omd.setContentLength(oggFile.length());
		
		System.out.printf("Uploading %s to S3 bucket %s...\n", oggFile, "b19dramaticexit");
		try {
			//makes object publicly visible
			PutObjectResult res = s3.putObject(new PutObjectRequest(bucket, "Videos/" + oggFile, inputstream, omd)
				.withCannedAcl(CannedAccessControlList.PublicRead));
			String objectURL = s3.getUrl(bucket, "Videos/" + oggFile).toString();
			if(objectURL == null) {
				System.out.println("cannot put into s3");
				return false;
			}
			System.out.println(objectURL);
			return uploadVideotoRDS(objectURL, videoID, characterName, sentence, availability);
		} catch (AmazonServiceException e) {
		    System.err.println(e.getErrorMessage());
		    return false;
		}
	}
		
	/** Store into RDS.
	 * 
	 * @throws Exception 
	 */
	boolean uploadVideotoRDS(String objectURL, String videoID, String characterName, String sentence, boolean availability) throws Exception {
		if (logger != null) { logger.log("in uploadVideo"); }
		VideosDAO dao = new VideosDAO();
		
		// check if present
		Video exist = dao.getVideoByURL(objectURL);
		
		Video video = new Video (videoID, characterName, sentence, availability, objectURL);
		if (exist == null) {
			System.out.println("adding video to RDS");
			return dao.addVideo(video);
		} else {
			return false;
		}
	}
	
	@Override 
	public UploadVideoResponse handleRequest(UploadVideoRequest req, Context context)  {
		logger = context.getLogger();
		logger.log(req.toString());

		UploadVideoResponse response;
		try {
			if (req.system) {
				if (uploadVideo(req.oggFile, req.videoID, req.characterName, req.sentence, req.availability)) {
					response = new UploadVideoResponse(req.videoID, 200);
				} else {
					response = new UploadVideoResponse(req.videoID, 422);
				}
			} else {			
				if (uploadVideo(req.oggFile, req.videoID, req.characterName, req.sentence, req.availability)) {
					response = new UploadVideoResponse(req.videoID, 200);
				} else {
					response = new UploadVideoResponse(req.videoID, 422);
				}
			}
		} catch (Exception e) {
			response = new UploadVideoResponse("Unable to create video: " + req.videoID + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}


}
