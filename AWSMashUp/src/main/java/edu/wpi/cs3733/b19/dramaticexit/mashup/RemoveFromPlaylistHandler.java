package edu.wpi.cs3733.b19.dramaticexit.mashup;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs3733.b19.dramaticexit.mashup.db.PlaylistsDAO;
import edu.wpi.cs3733.b19.dramaticexit.mashup.db.VideosDAO;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.RemoveFromPlaylistRequest;
import edu.wpi.cs3733.b19.dramaticexit.mashup.http.RemoveFromPlaylistResponse;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Playlist;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Video;

public class RemoveFromPlaylistHandler implements RequestHandler<RemoveFromPlaylistRequest,RemoveFromPlaylistResponse>{

	LambdaLogger logger;

	/** Find in RDS.
	 * 
	 * @throws Exception 
	 */
	boolean removeVideo(String playlistName, String videoID) throws Exception {
		System.out.println("playlistName: " + playlistName + " VideoID: " + videoID);
		logger.log("in removeVideoFromPlaylist");
		PlaylistsDAO pdao = new PlaylistsDAO();
		
		// check if present
		Playlist pExist = pdao.getPlaylist(playlistName);
		if (pExist == null) {
			System.out.println("playlist doesn't exist");
			return false;
		} else {
			System.out.println("dao problem");
			return pdao.removeVideo(playlistName, videoID);
		}
	}
	
	@Override
	public RemoveFromPlaylistResponse handleRequest(RemoveFromPlaylistRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());

		RemoveFromPlaylistResponse response;
		try {
			if (removeVideo(req.playlistName, req.videoID)) {
				response = new RemoveFromPlaylistResponse(req.playlistName, req.videoID);
			} else {
				response = new RemoveFromPlaylistResponse("unable to remove video from playlist", 422);
			}
			
		} catch (Exception e) {
			response = new RemoveFromPlaylistResponse("Unable to remove " + req.videoID + " from playlist: " + req.playlistName + "(" + e.getMessage() + ")", 400);
		}
		System.out.println("response: " + response);
		return response;
	}

}


