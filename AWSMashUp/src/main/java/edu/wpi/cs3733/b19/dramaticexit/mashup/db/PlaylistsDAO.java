package edu.wpi.cs3733.b19.dramaticexit.mashup.db;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import edu.wpi.cs3733.b19.dramaticexit.mashup.db.DatabaseUtil;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Video;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Playlist;
import edu.wpi.cs3733.b19.dramaticexit.mashup.db.VideosDAO;

/**
 * Note that CAPITALIZATION matters regarding the table name. If you create with 
 * a capital "Constants" then it must be "Constants" in the SQL queries.
 * 
 * @author b193733 DramaticExit
 *
 */

public class PlaylistsDAO {

	java.sql.Connection conn;

    public PlaylistsDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }

    public Playlist getPlaylist(String name) throws Exception {
        
        try {
            Playlist playlist = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Playlists WHERE playlistName=?;");
            ps.setString(1,  name);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                playlist = generatePlaylist(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return playlist;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting playlist: " + e.getMessage());
        }
    }
    
/**    
    public boolean updateVideo(Video video) throws Exception {
        try {
        	String query = "UPDATE Videos SET availability=? WHERE videoID=?;";
        	PreparedStatement ps = conn.prepareStatement(query);
            ps.setBoolean(1, video.availability);
            ps.setString(2, video.videoID);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);
        } catch (Exception e) {
            throw new Exception("Failed to update video: " + e.getMessage());
        }
    }
**/
    public boolean appendVideo(Playlist playlist, Video video) throws Exception {
        try {
        	String query = "INSERT INTO PlaylistVideos (playlistName,videoID,url) values(?,?,?);";
        	PreparedStatement ps = conn.prepareStatement(query);
        	ps.setString(1, playlist.playlistName);
            ps.setString(2, video.videoID);
            ps.setString(3, video.url);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);
        } catch (Exception e) {
            throw new Exception("Failed to append video: " + e.getMessage());
        }
    }
    
    public boolean removeVideo(String playlistName, String videoID) throws Exception {
    	try {
    		PreparedStatement ps = conn.prepareStatement("DELETE FROM PlaylistVideos WHERE playlistName = ? AND videoID = ?;");
    		ps.setString(1, playlistName);
    		ps.setString(2, videoID);
    		int numAffected = ps.executeUpdate();
    		ps.close();
    		
    		return (numAffected == 1);
    	}
    	catch (Exception e){
    		throw new Exception("Failed to remove video from playlist " + e.getMessage());
    	}
    }
    
    public boolean deletePlaylist(String playlistName) throws Exception {
        try {
        	System.out.println("in deletePlaylist DAO");
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Playlists WHERE playlistName = ?;");
            ps.setString(1, playlistName);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to delete playlist: " + e.getMessage());
        }
    }


    public boolean addPlaylist(Playlist pl) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Playlists WHERE playlistName = ?;");
            ps.setString(1, pl.playlistName);
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
                Playlist p = generatePlaylist(resultSet);
                resultSet.close();
                return false;
            }

            ps = conn.prepareStatement("INSERT INTO Playlists (playlistID,playlistName) values(?,?);");
            ps.setString(1, pl.playlistID);
            ps.setString(2, pl.playlistName);
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to add playlist: " + e.getMessage());
        }
    }

    public List<Playlist> getAllPlaylists() throws Exception {
        
        List<Playlist> allPlaylists = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM Playlists";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Playlist p = generatePlaylist(resultSet);
                allPlaylists.add(p);
            }
            resultSet.close();
            statement.close();
            return allPlaylists;

        } catch (Exception e) {
            throw new Exception("Failed in getting playlists: " + e.getMessage());
        }
    }
    
public List<Video> getPlaylistVideos(String playlistName) throws Exception {
        
        List<Video> allVideos = new ArrayList<>();
        try {
        	PreparedStatement ps = conn.prepareStatement("SELECT * FROM PlaylistVideos WHERE playlistName=?;");
            ps.setString(1,  playlistName);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Video v = generateVideo(resultSet);
                allVideos.add(v);
            }
            resultSet.close();
            ps.close();
            return allVideos;

        } catch (Exception e) {
            throw new Exception("Failed in getting videos from playlist: " + e.getMessage());
        }
    }
    
    private Playlist generatePlaylist(ResultSet resultSet) throws Exception {
        String playlistID  = resultSet.getString("playlistID");
        String playlistName = resultSet.getString("playlistName");
        return new Playlist(playlistID, playlistName);
    }
    
    private Video generateVideo(ResultSet resultSet) throws Exception {
        String videoID  = resultSet.getString("videoID");
        String url = resultSet.getString("url");
        return new Video(videoID, url);
    }
}
