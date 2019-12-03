package edu.wpi.cs3733.b19.dramaticexit.mashup;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

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
	
	/** Create S3 bucket
	 * 
	 * @throws Exception 
	 */
	boolean uploadVideo(String oggFile) throws Exception {
		if (logger != null) { logger.log("in uploadVideo"); }
		
		if (s3 == null) {
			logger.log("attach to S3 request");
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
			logger.log("attach to S3 succeed");
		}
		
		InputStream inputstream = new FileInputStream(oggFile);
		ObjectMetadata omd = new ObjectMetadata();
		omd.setContentLength(oggFile.length());
		
		PutObjectResult res = s3.putObject(new PutObjectRequest("b19dramaticexit", "Videos/" + name, bais, omd)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		
		// if we ever get here, then whole thing was stored
		return true;
	}
		
	/** Store into RDS.
	 * 
	 * @throws Exception 
	 */
	boolean uploadVideotoRDS(String videoID, String characterName, String sentence, boolean availability, String url) throws Exception {
		if (logger != null) { logger.log("in uploadVideo"); }
		VideosDAO dao = new VideosDAO();
		
		// check if present
		Video exist = dao.getVideo(sentence);
		Video video = new Video (videoID, characterName, sentence, availability, url);
		if (exist == null) {
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
			byte[] encoded = java.util.Base64.getDecoder().decode(req.base64EncodedValue);
			if (req.system) {
				if (uploadVideotoRDS(req.videoID, encoded)) {
					response = new UploadVideoResponse(req.videoID);
				} else {
					response = new UploadVideoResponse(req.videoID, 422);
				}
			} else {
				String contents = new String(encoded);
				double value = Double.valueOf(contents);
				
				if (uploadVideotoRDS(req.videoID, value)) {
					response = new UploadVideoResponse(req.videoID);
				} else {
					response = new UploadVideoResponse(req.videoID, 422);
				}
			}
		} catch (Exception e) {
			response = new UploadVideoResponse("Unable to create constant: " + req.videoID + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}


}
