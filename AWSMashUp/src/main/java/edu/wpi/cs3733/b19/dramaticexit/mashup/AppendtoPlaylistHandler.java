package edu.wpi.cs3733.b19.dramaticexit.mashup;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs3733.b19.dramaticexit.mashup.db.PlaylistsDAO;
import edu.wpi.cs3733.b19.dramaticexit.mashup.db.VideosDAO;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.AppendToPlaylistRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.AppendToPlaylistResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.CreatePlaylistResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Playlist;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Video;


public class AppendtoPlaylistHandler implements RequestHandler<AppendToPlaylistRequest,AppendToPlaylistResponse>{
	
	LambdaLogger logger;

	/** Store into RDS.
	 * 
	 * @throws Exception 
	 */
	boolean appendToPlaylist(String playlistName, String videoID) throws Exception {
		logger.log("in appendToPlaylist");
		PlaylistsDAO dao = new PlaylistsDAO();
		VideosDAO vdao = new VideosDAO();
		
		// check if video is present
		Video vExist = vdao.getVideo(videoID);
		
		// check if playlist exists
		Playlist pExist = dao.getPlaylist(playlistName);
		
		if (vExist == null) {
			return false;
		} else if (pExist == null) {
			return false;
		} else {
			return dao.appendVideo(pExist, vExist);
		}
	}

	@Override
	public AppendToPlaylistResponse handleRequest(AppendToPlaylistRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());

		AppendToPlaylistResponse response;
		logger.log("in handleRequest");
		try {
			if (appendToPlaylist(req.playlistID, req.videoID)) {
				response = new AppendToPlaylistResponse(req.playlistID, req.videoID);
			} else {
				response = new AppendToPlaylistResponse(req.playlistID, req.videoID, 422, "Unable to append video to playlist");
			}
			
		} catch (Exception e) {
			response = new AppendToPlaylistResponse("Unable to append " + req.videoID + " to playlist: " + req.playlistID + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}

}
