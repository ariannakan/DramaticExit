package edu.wpi.cs3733.b19.dramaticexit.mashup;

import java.util.Date;
import java.text.SimpleDateFormat;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs3733.b19.dramaticexit.mashup.db.SitesDAO;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.RegisterSiteRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.RegisterSiteResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Site;

public class RegisterSiteHandler implements RequestHandler<RegisterSiteRequest,RegisterSiteResponse> {
	
	LambdaLogger logger;

	/** Store into RDS.
	 * 
	 * @throws Exception 
	 */
	boolean registerSite(String url) throws Exception {
		if (logger != null) { logger.log("in createConstant"); }
		SitesDAO dao = new SitesDAO();
		
		// check if present
		Site exist = dao.getSiteURL(url);
		Site site = new Site (new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()), url);
		if (exist == null) {
			return dao.addSite(site);
		} else {
			return false;
		}
	}
	
	
	@Override
	public RegisterSiteResponse handleRequest(RegisterSiteRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());

		RegisterSiteResponse response;
		try {
			if (registerSite(req.url)) {
				response = new RegisterSiteResponse(req.url);
			} else {
				response = new RegisterSiteResponse(req.url, 422);
			}
			
		} catch (Exception e) {
			response = new RegisterSiteResponse("Unable to create site: " + req.url + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}

}
