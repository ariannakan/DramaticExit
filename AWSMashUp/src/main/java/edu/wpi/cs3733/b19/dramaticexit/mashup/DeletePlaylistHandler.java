package edu.wpi.cs3733.b19.dramaticexit.mashup;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs3733.b19.dramaticexit.mashup.db.PlaylistsDAO;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.DeletePlaylistRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.DeletePlaylistResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Playlist;


public class DeletePlaylistHandler implements RequestHandler<DeletePlaylistRequest,DeletePlaylistResponse>{

	LambdaLogger logger;

	/** Find in RDS.
	 * 
	 * @throws Exception 
	 */
	boolean deletePlaylist(String name) throws Exception {
		logger.log("in deletePlaylist");
		PlaylistsDAO dao = new PlaylistsDAO();
		
		// check if present
		Playlist exist = dao.getPlaylist(name);
		if (exist == null) {
			return false;
		} else {
			return dao.deletePlaylist(name);
		}
	}
	
	@Override
	public DeletePlaylistResponse handleRequest(DeletePlaylistRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());

		DeletePlaylistResponse response;
		try {
			if (deletePlaylist(req.playlistName)) {
				response = new DeletePlaylistResponse(req.playlistName);
			} else {
				response = new DeletePlaylistResponse(req.playlistName, 422);
			}
			
		} catch (Exception e) {
			response = new DeletePlaylistResponse("Unable to create playlist: " + req.playlistName + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}

}
