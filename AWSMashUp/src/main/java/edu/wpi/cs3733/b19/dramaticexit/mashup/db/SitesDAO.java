package edu.wpi.cs3733.b19.dramaticexit.mashup.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mua.model.Site;

public class SitesDAO {

	java.sql.Connection conn;

    public SitesDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }

    public Site getSite(String url) throws Exception {
        
        try {
            Site site = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Sites WHERE url=?;");
            ps.setString(1,  url);		//videoID is 1st index in database
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                site = generateSite(resultSet);
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

            ps = conn.prepareStatement("INSERT INTO Videos (videoID,characterName,sentence,availability) values(?,?,?,?);");
            ps.setString(1,  video.videoID);
            ps.setString(2, video.characterName);
            ps.setString(3, video.sentence);
            ps.setBoolean(4,  video.availability);
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
        return new Video(videoID, characterName, sentence, availability);
    }
}
