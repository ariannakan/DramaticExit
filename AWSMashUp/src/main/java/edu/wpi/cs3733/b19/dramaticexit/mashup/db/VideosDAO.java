package edu.wpi.cs3733.b19.dramaticexit.mashup.db;

import java.sql.*;
import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

import java.util.*;

import edu.wpi.cs3733.b19.dramaticexit.mashup.db.DatabaseUtil;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Video;

/**
 * Note that CAPITALIZATION matters regarding the table name. If you create with 
 * a capital "Videos" then it must be "Videos" in the SQL queries.
 * 
 * @author b193733 DramaticExit
 *
 */

public class VideosDAO {

	java.sql.Connection conn;
	
	LambdaLogger logger;

    public VideosDAO() {
//    	System.out.println("\n trying to connect");
    	try  {
    		logger.log("trying to connect");
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    		logger.log("null connection");
//    		e.printStackTrace();
    	}
    }

    public Video getVideo(String videoID) throws Exception {
    	logger.log("in getVideo");
        try {
            Video video = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Videos WHERE videoID=?;");
            ps.setString(1, videoID);		//videoID is 1st index in database
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                video = generateVideo(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return video;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting video: " + e.getMessage());
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
    public boolean deleteVideo(String videoID) throws Exception {
        try {
        	logger.log("in deleteVideo DAO");
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Videos WHERE videoID = ?;");
            ps.setString(1, videoID);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to delete video: " + e.getMessage());
        }
    }


    public boolean addVideo(Video video) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Videos WHERE videoID = ?;");
            ps.setString(1, video.videoID);
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
                Video v = generateVideo(resultSet);
                resultSet.close();
                return false;
            }

            ps = conn.prepareStatement("INSERT INTO Videos (videoID,characterName,sentence,availability,url) values(?,?,?,?,?);");
            ps.setString(1,  video.videoID);
            ps.setString(2, video.characterName);
            ps.setString(3, video.sentence);
            ps.setBoolean(4,  video.availability);
            ps.setString(5,  video.url);
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to add video: " + e.getMessage());
        }
    }

    public void testFunction() {
    	System.out.println("\n test function entered");
    }
    
    public List<Video> getAllVideos() throws Exception {
        
        List<Video> allVideos = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM Videos";
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                Video v = generateVideo(resultSet);
                allVideos.add(v);
            }
            resultSet.close();
            statement.close();
            return allVideos;

        } catch (Exception e) {
            throw new Exception("Failed in getting videos: " + e);
        }
    }
    
    private Video generateVideo(ResultSet resultSet) throws Exception {
        String videoID  = resultSet.getString("videoID");
        String characterName = resultSet.getString("characterName");
        String sentence = resultSet.getString("sentence");
        boolean availability = resultSet.getBoolean("availability");
        String url = resultSet.getString("url");
        return new Video(videoID, characterName, sentence, availability, url);
    }
}
