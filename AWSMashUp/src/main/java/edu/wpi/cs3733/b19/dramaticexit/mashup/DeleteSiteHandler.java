package edu.wpi.cs3733.b19.dramaticexit.mashup;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs3733.b19.dramaticexit.mashup.db.PlaylistsDAO;
import edu.wpi.cs3733.b19.dramaticexit.mashup.db.SitesDAO;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.DeletePlaylistRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.DeletePlaylistResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.DeleteSiteRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.DeleteSiteResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Playlist;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Site;

public class DeleteSiteHandler implements RequestHandler<DeleteSiteRequest,DeleteSiteResponse>{
	LambdaLogger logger;

	/** Find in RDS.
	 * 
	 * @throws Exception 
	 */
	boolean deleteSite(String url) throws Exception {
		logger.log("in deleteSite");
		SitesDAO dao = new SitesDAO();
		
		// check if present
		System.out.println("checking if site exists before deleting");
		Site exist = dao.getSiteURL(url);
		if (exist == null) {
			System.out.println("site does not exist");
			return false;
		} else {
			System.out.println("site exists --> deleting");
			return dao.deleteSite(url);
		}
	}
	
	@Override
	public DeleteSiteResponse handleRequest(DeleteSiteRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());

		DeleteSiteResponse response;
		try {
			if (deleteSite(req.url)) {
				System.out.println("site deleted");
				response = new DeleteSiteResponse(req.url, 200);
			} else {
				System.out.println("site does not exist -- cannot delete");
				response = new DeleteSiteResponse(req.url, 422);
			}
			
		} catch (Exception e) {
			response = new DeleteSiteResponse("Unable to delate site: " + req.url + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}

}
