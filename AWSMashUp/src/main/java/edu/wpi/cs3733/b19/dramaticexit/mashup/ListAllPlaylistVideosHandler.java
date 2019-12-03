package edu.wpi.cs3733.b19.dramaticexit.mashup;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs3733.b19.dramaticexit.mashup.db.PlaylistsDAO;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.AllPlaylistVideosResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.ListPlaylistVideosRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Playlist;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Video;

public class ListAllPlaylistVideosHandler implements RequestHandler<ListPlaylistVideosRequest,AllPlaylistVideosResponse>{
	
	LambdaLogger logger;

	/** Load from RDS, if it exists
	 * 
	 * @throws Exception 
	 */
	List<Video> getPlaylistVideos(String playlistName) throws Exception {
		PlaylistsDAO dao = new PlaylistsDAO();
		
		return dao.getPlaylistVideos(playlistName);
	}

	@Override
	public AllPlaylistVideosResponse handleRequest(ListPlaylistVideosRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all playlist videos");

		AllPlaylistVideosResponse response;
		try {
			List<Video> list = getPlaylistVideos(req.playlistName);
		
			response = new AllPlaylistVideosResponse(list);
		} catch (Exception e) {
			response = new AllPlaylistVideosResponse(403, e.getMessage());
		}
		
		return response;
	}

}
