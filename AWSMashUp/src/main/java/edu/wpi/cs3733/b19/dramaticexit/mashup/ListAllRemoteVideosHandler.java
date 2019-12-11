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
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.ListAllRemoteVideosRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.ListAllRemoteVideosResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.ListVideosRequest;

public class ListAllRemoteVideosHandler implements RequestHandler<ListAllRemoteVideosRequest,ListAllRemoteVideosResponse>{

	public LambdaLogger logger;

	/** Load from RDS, if it exists
	 * 
	 * @throws Exception 
	 */
	List<Video> getVideos() throws Exception {
		VideosDAO dao = new VideosDAO();

		return dao.getRemoteVideos(); 
	}
	
	// I am leaving in this S3 code so it can be a LAST RESORT if the constant is not in the database
	private AmazonS3 s3 = null;
	
	@Override
	public ListAllRemoteVideosResponse handleRequest(ListAllRemoteVideosRequest input, Context context)  {
		
		
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all remote videos");

		ListAllRemoteVideosResponse response;
		try {
			// get all user defined constants AND system-defined constants.
			// Note that user defined constants override system-defined constants.
			List<Video> list = getVideos();
		
//			for (Video v : systemVideos()) {
//				if (!list.contains(v)) {
//					list.add(v);
//				}
//			}
			response = new ListAllRemoteVideosResponse(list, 200);
		} catch (Exception e) {
			response = new ListAllRemoteVideosResponse(403, e.getMessage());
		}
		
		return response;
	}

}
