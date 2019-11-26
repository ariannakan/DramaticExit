package edu.wpi.cs3733.b19.dramaticexit.mashup.db;

import java.sql.*;
import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

import java.util.*;

import edu.wpi.cs3733.b19.dramaticexit.mashup.db.DatabaseUtil;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Video;

/**
 * Note that CAPITALIZATION matters regarding the table name. If you create with 
 * a capital "Constants" then it must be "Constants" in the SQL queries.
 * 
 * @author b193733 DramaticExit
 *
 */

public class VideosDAO {

	java.sql.Connection conn;
	
	LambdaLogger logger;

    public VideosDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }

    public Video getVideo(String id) throws Exception {
        
        try {
            Video video = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Videos WHERE videoID=?;");
            ps.setString(1,  id);		//videoID is 1st index in database
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
    public boolean deleteVideo(Video video) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Videos WHERE videoID = ?;");
            ps.setString(1, video.videoID);
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

    public List<Video> getAllVideos() throws Exception {
        
        List<Video> allVideos = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM Videos";
            ResultSet resultSet = statement.executeQuery(query);
            
            logger.log("recieved resultSet");
            while (resultSet.next()) {
                Video v = generateVideo(resultSet);
                allVideos.add(v);
            }
            resultSet.close();
            statement.close();
            return allVideos;

        } catch (Exception e) {
            throw new Exception("Failed in getting videos: " + e.getMessage());
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
