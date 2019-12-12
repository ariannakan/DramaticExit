package edu.wpi.cs3733.b19.dramaticexit.mashup.db;

import java.sql.*;
import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

import java.util.*;

import edu.wpi.cs3733.b19.dramaticexit.mashup.db.DatabaseUtil;
import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Segment;
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
    		System.out.println("trying to connect");
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    		System.out.println("null connection");
//    		e.printStackTrace();
    	}
    }
    //ListVideos
    public Video getVideo(String videoID) throws Exception {
    	System.out.println("in getVideo"); 
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
        	System.out.println("unable to get video in VideosDAO");
            throw new Exception("Failed in getting video: " + e.getMessage());
        }
    }
    
    public Video getVideoByURL(String url) throws Exception {
    	System.out.println("in getVideobyURL");
        try {
            Video video = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Videos WHERE url=?;");
            ps.setString(1, url);		//videoID is 1st index in database
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                video = generateVideo(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return video;

        } catch (Exception e) {
        	System.out.println("unable to get video in VideosDAO");
            throw new Exception("Failed in getting video: " + e.getMessage());
        }
    }
    
    public Video getRemoteVideoByID(String videoID) throws Exception {
    	System.out.println("in getVideobyID");
        try {
            Video video = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM RemoteVideos WHERE videoID=?;");
            ps.setString(1, videoID);		//videoID is 1st index in database
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                video = generateVideo(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return video;

        } catch (Exception e) {
        	System.out.println("unable to get video in VideosDAO");
            throw new Exception("Failed in getting video by ID: " + e.getMessage());
        }
    }
    
    public Video getRemoteVideoByURL(String url) throws Exception {
    	System.out.println("in getVideobyURL");
        try {
            Video video = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM RemoteVideos WHERE url=?;");
            ps.setString(1, url);		//videoID is 1st index in database
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                video = generateVideo(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return video;

        } catch (Exception e) {
        	System.out.println("unable to get video in VideosDAO");
            throw new Exception("Failed in getting video by URL: " + e.getMessage());
        }
    }

   
    public boolean updateVideo(String videoID, boolean availability) throws Exception {
        try {
        	String query = "UPDATE Videos SET availability=? WHERE videoID=?;";
        	PreparedStatement ps = conn.prepareStatement(query);
            ps.setBoolean(1, availability);
            ps.setString(2, videoID);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);
        } catch (Exception e) {
            throw new Exception("Failed to update video: " + e.getMessage());
        }
    }

    public boolean deleteVideo(String videoID) throws Exception {
        try {
        	System.out.println("in deleteVideo DAO");
        	//deleting from playlistVideos database
        	PreparedStatement ps2 = conn.prepareStatement("DELETE FROM PlaylistVideos WHERE videoID = ?;");
            ps2.setString(1, videoID);
            ps2.executeUpdate();
            
            //deleting from videos database
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
            System.out.println("successfully added video");
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
            throw new Exception("Failed in getting videos: " + e);
        }
    }
    
    
    public List<Video> getRemoteVideos() throws Exception {
        
        List<Video> allRemoteVideos = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM RemoteVideos";
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
            	Video v = generateRemoteVideo(resultSet);
            	allRemoteVideos.add(v);
            }
            resultSet.close();
            statement.close();
            return allRemoteVideos;

        } catch (Exception e) {
            throw new Exception("Failed in getting Remote videos: " + e);
        }
    }
    //search by character // sentence
    public List<Video> search(String keywordname, String keywordsentence)throws Exception{
    	List<Video> searchvid = new ArrayList<>();
    	try {
        	//search from videos
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM Videos";
            ResultSet resultSet = statement.executeQuery(query);
            //add videos
            while (resultSet.next()) {
        		Video v = generateVideo(resultSet);
            	if (v.characterName.toLowerCase().contains(keywordname.toLowerCase())&&
            		v.sentence.toLowerCase().contains(keywordsentence.toLowerCase())) {//matched character name 
                	searchvid.add(v);
            	}
            }
            resultSet.close();
            statement.close();
            
            //search from remote videos
            Statement statement2 = conn.createStatement();
            String query2 = "SELECT * FROM RemoteVideos";
            ResultSet resultSet2 = statement2.executeQuery(query2);
            //add videos
            while (resultSet2.next()) {
        		Video v2 = generateRemoteVideo(resultSet2);
            	if (v2.characterName.toLowerCase().contains(keywordname.toLowerCase())&&
            		v2.sentence.toLowerCase().contains(keywordsentence.toLowerCase())) {//matched character name 
                	searchvid.add(v2);
            	}
            }
            resultSet2.close();
            statement2.close();
         
            return searchvid;

        } catch (Exception e) {
            throw new Exception("Failed in searching videos: " + e);
        }
    }
    
  //return remotely available videos and return in segments 
    public List<Segment> AvailableForRemote()throws Exception{
    	List<Segment> seg = new ArrayList<>();
    	try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM Videos";
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
        		Video v = generateVideo(resultSet);
            	if (v.availability) {//filter availability
                	seg.add(new Segment(v.url, v.characterName, v.sentence));
            	}
            }
            resultSet.close();
            statement.close();
            return seg;

        } catch (Exception e) {
            throw new Exception("Failed in searching videos: " + e);
        }
    }
    
    //add remote video into remotevideos database 
    public boolean addRemoteVideo(Video video, String apikey) throws Exception {
    	try {

            PreparedStatement ps = conn.prepareStatement("INSERT INTO RemoteVideos (apikey,videoID,url,characterName,sentence) values(?,?,?,?,?);");
            ps.setString(1,  apikey);
            ps.setString(2,  video.videoID);
            ps.setString(3, video.url);
            ps.setString(4, video.characterName);
            ps.setString(5,  video.sentence);
            ps.execute();
            System.out.println("successfully added video");
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to add video: " + e.getMessage());
        }
    }
    
  //remove remote video into remotevideos database 
    public boolean remRemoteVideos(String apikey) throws Exception {
    	try {
        	System.out.println("in deleteRemoteVideo DAO");
            
            //deleting from videos database
            PreparedStatement ps = conn.prepareStatement("DELETE FROM RemoteVideos WHERE apikey = ?;");
            ps.setString(1, apikey);

            int numAffected = ps.executeUpdate();
            
            ps.close();
            
            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to delete video: " + e.getMessage());
        }
    }
  
  //generate videos from videos database
    private Video generateVideo(ResultSet resultSet) throws Exception {
        String videoID  = resultSet.getString("videoID");
        String characterName = resultSet.getString("characterName");
        String sentence = resultSet.getString("sentence");
        boolean availability = resultSet.getBoolean("availability");
        String url = resultSet.getString("url");
        return new Video(videoID, characterName, sentence, availability, url);
    }
    
  //generate videos from remote videos
    private Video generateRemoteVideo(ResultSet resultSet) throws Exception {
        String characterName = resultSet.getString("characterName");
        String sentence = resultSet.getString("sentence");
        String url = resultSet.getString("url");
        String videoID = resultSet.getString("videoID");

        return new Video(videoID, characterName, sentence, url);
    }
}


