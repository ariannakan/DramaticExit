package edu.wpi.cs3733.b19.dramaticexit.mashup;

import java.util.Date;
import java.text.SimpleDateFormat;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs3733.b19.dramaticexit.mashup.db.SitesDAO;
import edu.wpi.cs3733.b19.dramaticexit.mashup.db.VideosDAO;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.RegisterSiteRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.RegisterSiteResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Site;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Video;

public class RegisterSiteHandler implements RequestHandler<RegisterSiteRequest,RegisterSiteResponse> {
	
	LambdaLogger logger;

	/** Store into RDS.
	 * 
	 * @throws Exception 
	 */
	boolean registerSite(String apikey, String url, String characterName, String sentence) throws Exception {
		if (logger != null) { logger.log("in registerSite"); }
		SitesDAO dao = new SitesDAO();
		VideosDAO vdao = new VideosDAO();
		
		// check if site present
		Site exist = dao.getSiteURL(apikey);
		System.out.println("checked if site is present");
		//check if remote video exist
		Video vexist = vdao.getRemoteVideoByURL(url);
		System.out.println("checked if remote video is present");
		Video video = new Video(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SS").format(new Date()), characterName, sentence, url);
		Site site = new Site (new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SS").format(new Date()), apikey);
		
		if (exist == null) {
			System.out.println("site does not exist - adding");
			//this is where we also create videos and add to remote videos database
			return dao.addSite(site)&&vdao.addRemoteVideo(video, apikey);
		} else if (vexist == null ){
			System.out.println("video does not exist - adding");
			return vdao.addRemoteVideo(video, apikey);
		} else {
			System.out.println("site and video exist");
			return false;
		}
		
		

	}
	
//	boolean addRemoteVideos(String url) throws Exception {		//url == apikey
//		if (logger != null) { logger.log("in registerSite - in addRemoteVideos"); }
//		SitesDAO dao = new SitesDAO();
//		VideosDAO vdao = new VideosDAO();
//		
//		// check if present
//		Site exist = dao.getSiteURL(url);
//		if (exist == null) {
//			System.out.println("site does not exist - cannot add remote videos");
//			return false;
//		} else {
//			System.out.println("site exists - adding remote videos");
//			return vdao.addRemoteVideos(url);
//		}
//	}
	
	
	@Override
	public RegisterSiteResponse handleRequest(RegisterSiteRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());

		RegisterSiteResponse response;
		try {
			if (registerSite(req.apikey, req.url, req.characterName, req.sentence)) {
				response = new RegisterSiteResponse(req.apikey);
			} else {
				response = new RegisterSiteResponse(req.apikey, 422);
			}
			
		} catch (Exception e) {
			System.out.println("Unable to create site: " + req.apikey + "(" + e.getMessage() + ")");
			response = new RegisterSiteResponse("Unable to create site: " + req.apikey + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}

}



