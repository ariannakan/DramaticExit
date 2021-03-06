package edu.wpi.cs3733.b19.dramaticexit.mashup;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

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
	boolean uploadVideo(String videoID, String characterName, String sentence, String base64EncodedValue) throws Exception {
		if (useTestDB()) { bucket = "3733dramaticexit"; }
		if (logger != null) { logger.log("in uploadVideo"); }
		
		if (s3 == null) {
			logger.log("attach to S3 request");
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
			logger.log("attach to S3 succeed");
		}
		
		//System.out.printf("Uploading %s to S3 bucket %s...\n", base64EncodedValue, "b19dramaticexit");
		
		try {
			byte[] videoByteArray = Base64.getDecoder().decode(base64EncodedValue);
			InputStream inputstream = new ByteArrayInputStream(videoByteArray);
			ObjectMetadata omd = new ObjectMetadata(); 
			omd.setContentLength(videoByteArray.length);
			
			//makes object publicly visible
			PutObjectResult res = s3.putObject(new PutObjectRequest(bucket, "Videos/" + videoID + ".ogg", inputstream, omd)
				.withCannedAcl(CannedAccessControlList.PublicRead));
			
			String videoURL = s3.getUrl(bucket, "Videos/" + videoID + ".ogg").toString();
			if(videoURL == null) {
				System.out.println("cannot put into s3");
				return false;
			}
			System.out.println(videoURL);
			return uploadVideotoRDS(videoID, characterName, sentence, true, videoURL);
		} catch (AmazonServiceException e) {
		    System.err.println(e.getErrorMessage());
		    return false;
		}
	}
		
	/** Store into RDS.
	 * 
	 * @throws Exception 
	 */
	boolean uploadVideotoRDS(String videoID, String characterName, String sentence, boolean availability, String url) throws Exception {
		if (logger != null) { logger.log("in uploadVideo"); }
		VideosDAO dao = new VideosDAO();
		
		// check if present
		Video exist = dao.getVideoByURL(url);
		
		Video video = new Video (videoID, characterName, sentence, availability, url);
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
		//logger.log(req.toString());

		UploadVideoResponse response;
		
		String videoID = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		try { 
				if (uploadVideo(videoID, req.characterName, req.sentence, req.base64EncodedValue)) {
					System.out.println("successful upload");
					response = new UploadVideoResponse(videoID, 200);
				} else {
					System.out.println("failed upload");
					response = new UploadVideoResponse(videoID, 422);
				}
		} catch (Exception e) {
			response = new UploadVideoResponse("Unable to upload video: " + videoID + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}


}
