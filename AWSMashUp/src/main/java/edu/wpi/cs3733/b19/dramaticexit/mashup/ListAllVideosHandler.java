package edu.wpi.cs3733.b19.dramaticexit.mashup;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import edu.wpi.cs3733.b19.dramaticexit.mashup.db.VideosDAO;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Video;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.AllVideosResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.ListVideosRequest;

public class ListAllVideosHandler implements RequestHandler<ListVideosRequest,AllVideosResponse>{

	public LambdaLogger logger;

	/** Load from RDS, if it exists
	 * 
	 * @throws Exception 
	 */
	List<Video> getVideos() throws Exception {
		logger.log("in getVideos");
		VideosDAO dao = new VideosDAO();
		
		return dao.getAllVideos();
	}
	
	// I am leaving in this S3 code so it can be a LAST RESORT if the constant is not in the database
	private AmazonS3 s3 = null;
	
	/**
	 * Retrieve all SYSTEM constants. This code is surprisingly dangerous since there could
	 * be an incredible number of objects in the bucket. Ignoring this for now.
	 */
	List<Video> systemVideos() throws Exception {
		logger.log("in systemVideos");
		if (s3 == null) {
			logger.log("attach to S3 request");
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
			logger.log("attach to S3 succeed");
		}
		ArrayList<Video> videoList = new ArrayList<>();
	    
		// retrieve listing of all objects in the designated bucket
		ListObjectsV2Request listObjectsRequest = new ListObjectsV2Request()
				  .withBucketName("b19dramaticexit")    // top-level bucket
				  .withPrefix("Videos");       // sub-folder declarations here (i.e., a/b/c)
												  
		
		// request the s3 objects in the s3 bucket 'b19dramaticexit/Video Segments' -- change based on your bucket name
		logger.log("process request");
		ListObjectsV2Result result = s3.listObjectsV2(listObjectsRequest);
		logger.log("process request succeeded");
		List<S3ObjectSummary> objects = result.getObjectSummaries();
		
		for (S3ObjectSummary os: objects) {
	      String name = os.getKey();
		  logger.log("S3 found:" + name);

	      // If name ends with slash it is the 'constants/' bucket itself so you skip
	      if (name.endsWith("/")) { continue; }
			
	      S3Object obj = s3.getObject("b19dramaticexit", name);
// WHAT TO DO HERE!!! PLEASE EXPLAIN	    	
	    	try (S3ObjectInputStream constantStream = obj.getObjectContent()) {
				Scanner sc = new Scanner(constantStream);
				String val = sc.nextLine();
				sc.close();
				
				// just grab name *after* the slash. Note this is a SYSTEM constant
				int postSlash = name.indexOf('/');
				videoList.add(new Video());
			} catch (Exception e) {
				logger.log("Unable to parse contents of " + name);
			}
	    }
		
		return videoList;
	}
	
	@Override
	public AllVideosResponse handleRequest(ListVideosRequest input, Context context)  {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all videos");

		AllVideosResponse response;
		try {
			// get all user defined constants AND system-defined constants.
			// Note that user defined constants override system-defined constants.
			List<Video> list = getVideos();
			for (Video v : systemVideos()) {
				if (!list.contains(v)) {
					list.add(v);
				}
			}
			response = new AllVideosResponse(list, 200);
		} catch (Exception e) {
			response = new AllVideosResponse(403, e.getMessage());
		}
		
		return response;
	}

}
