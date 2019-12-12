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
		Video video = null;
		if (vdao.getVideo(videoID) != null) {
			video = vdao.getVideo(videoID);
		} else if (vdao.getRemoteVideoByID(videoID) != null) {
			video = vdao.getRemoteVideoByID(videoID);
		} 
		
		
		// check if playlist exists
		Playlist pExist = dao.getPlaylist(playlistName);
		
		if (video == null) {
			return false;
		} else if (pExist == null) {
			return false;
		} else {
			return dao.appendVideo(pExist, video);
		}
	}

	@Override
	public AppendToPlaylistResponse handleRequest(AppendToPlaylistRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());

		AppendToPlaylistResponse response;
		logger.log("in handleRequest");
		try {
			if (appendToPlaylist(req.playlistName, req.videoID)) {
				response = new AppendToPlaylistResponse(req.playlistName, req.videoID);
			} else {
				response = new AppendToPlaylistResponse(req.playlistName, req.videoID, 422, "Unable to append remote video to playlist");
			}
			
		} catch (Exception e) {
			response = new AppendToPlaylistResponse("Unable to append " + req.videoID + " to playlist: " + req.playlistName + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}

}
