package edu.wpi.cs3733.b19.dramaticexit.mashup;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs3733.b19.dramaticexit.mashup.db.SitesDAO;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.AllSitesResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.ListSitesRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Site;

public class ListAllSitesHandler implements RequestHandler<ListSitesRequest,AllSitesResponse>{

	LambdaLogger logger;
	
	List<Site> getSites() throws Exception {
		SitesDAO dao = new SitesDAO();
		
		return dao.getAllSites();
	}

	@Override
	public AllSitesResponse handleRequest(ListSitesRequest input, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all sites");

		AllSitesResponse response;
		try {

			List<Site> list = getSites();
		
			response = new AllSitesResponse(list, 200);
		} catch (Exception e) {
			response = new AllSitesResponse(403, e.getMessage());
		}
		
		return response;
	}

}
