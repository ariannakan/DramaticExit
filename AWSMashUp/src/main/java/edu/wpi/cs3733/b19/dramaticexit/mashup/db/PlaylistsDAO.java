package edu.wpi.cs3733.b19.dramaticexit.mashup.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.*;

import edu.wpi.cs3733.b19.dramaticexit.mashup.db.DatabaseUtil;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Video;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Playlist;

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
        	String query = "UPDATE Playlists SET videoID=? WHERE playlistID=?;";
        	PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(3, video.videoID);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);
        } catch (Exception e) {
            throw new Exception("Failed to append video: " + e.getMessage());
        }
    }
    
    public boolean deleteVideo(Playlist playlist, Video video) throws Exception {
    	try {
    		PreparedStatement ps = conn.prepareStatement("DELETE WHERE videoID = ? FROM Playlists WHERE playlistID = ? ;");
    		ps.setString(1, video.videoID);
    		ps.setString(2, playlist.playlistID);
    		int numAffected = ps.executeUpdate();
    		ps.close();
    		
    		return (numAffected == 1);
    	}
    	catch (Exception e){
    		throw new Exception("Failed to delete video from playlist " + e.getMessage());
    	}
    }
    
    public boolean deletePlaylist(Playlist playlist) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Playlists WHERE playlistID = ?;");
            ps.setString(1, playlist.playlistID);
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
    
    private Playlist generatePlaylist(ResultSet resultSet) throws Exception {
        String playlistID  = resultSet.getString("playlistID");
        String playlistName = resultSet.getString("playlistName");
        return new Playlist(playlistID, playlistName);
    }
}
