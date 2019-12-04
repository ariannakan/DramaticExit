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
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.SearchRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.SearchResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Video;

public class SearchHandler implements RequestHandler<SearchRequest, SearchResponse>{
	public LambdaLogger logger;

	/** Load from RDS, if it exists
	 * 
	 * @throws Exception 
	 */
	List<Video> getVideos() throws Exception {
		VideosDAO dao = new VideosDAO();

		return dao.getAllVideos();
	}
	
	@Override
	public SearchResponse handleRequest(SearchRequest input, Context context)  {
		
		
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all videos");

		SearchResponse response;
		try {
		
			List<Video> list = getVideos();

			response = new SearchResponse(list, 200);
		} catch (Exception e) {
			response = new SearchResponse(403, e.getMessage());
		}
		
		return response;
	}
}
