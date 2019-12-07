package edu.wpi.cs3733.b19.dramaticexit.mashup;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs3733.b19.dramaticexit.mashup.db.PlaylistsDAO;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.CreatePlaylistRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.CreatePlaylistResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Playlist;


public class CreatePlaylistHandler implements RequestHandler<CreatePlaylistRequest,CreatePlaylistResponse>{

	LambdaLogger logger;

	/** Store into RDS.
	 * 
	 * @throws Exception 
	 */
	boolean createPlaylist(String name) throws Exception {
		logger.log("in createPlaylist");
		PlaylistsDAO dao = new PlaylistsDAO();
		
		// check if present
		Playlist exist = dao.getPlaylist(name);
		Playlist playlist = new Playlist(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()),name);
		if (exist == null && name != "" && name != null) {
			return dao.addPlaylist(playlist);
		} else {
			return false;
		}
	}
	
	@Override
	public CreatePlaylistResponse handleRequest(CreatePlaylistRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());

		CreatePlaylistResponse response;
		logger.log("in handleRequest");
		try {
			if (createPlaylist(req.playlistName)) {
				response = new CreatePlaylistResponse(req.playlistName);
			} else {
				response = new CreatePlaylistResponse(req.playlistName, 422);
			}
			
		} catch (Exception e) {
			response = new CreatePlaylistResponse("Unable to create playlist: " + req.playlistName + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}

}
