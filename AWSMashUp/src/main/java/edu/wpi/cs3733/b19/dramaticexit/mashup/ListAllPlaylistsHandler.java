package edu.wpi.cs3733.b19.dramaticexit.mashup;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs3733.b19.dramaticexit.mashup.db.PlaylistsDAO;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.AllPlaylistsResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.ListPlaylistsRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Playlist;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Video;

public class ListAllPlaylistsHandler implements RequestHandler<ListPlaylistsRequest,AllPlaylistsResponse>{
	
	LambdaLogger logger;

	/** Load from RDS, if it exists
	 * 
	 * @throws Exception 
	 */
	List<Playlist> getPlaylists() throws Exception {
		PlaylistsDAO dao = new PlaylistsDAO();
		
		return dao.getAllPlaylists();
	}

	@Override
	public AllPlaylistsResponse handleRequest(ListPlaylistsRequest input, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all videos");

		AllPlaylistsResponse response;
		try {
			// get all user defined constants AND system-defined constants.
			// Note that user defined constants override system-defined constants.
			List<Playlist> list = getPlaylists();
		
//			for (Video v : systemVideos()) {
//				if (!list.contains(v)) {
//					list.add(v);
//				}
//			}
			response = new AllPlaylistsResponse(list, 200);
		} catch (Exception e) {
			response = new AllPlaylistsResponse(403, e.getMessage());
		}
		
		return response;
	}

}
