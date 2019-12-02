package edu.wpi.cs3733.b19.dramaticexit.mashup.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs3733.b19.dramaticexit.mashup.model.Site;

public class SitesDAO {

	java.sql.Connection conn;

    public SitesDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }

    public Site getSiteURL(String url) throws Exception {
      
        try {
            Site site = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Sites WHERE url=?;");
            ps.setString(1, url);		//videoID is 1st index in database
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                site = generateSite(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return site;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting site url: " + e.getMessage());
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
    public boolean deleteSite(Site site) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Sites WHERE timestampID = ?;");
            ps.setString(1, site.siteID);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to delete site: " + e.getMessage());
        }
    }


    public boolean addSite(Site site) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Sites WHERE url = ?;");
            ps.setString(1, site.url);
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
                Site s = generateSite(resultSet);
                resultSet.close();
                return false;
            }

            ps = conn.prepareStatement("INSERT INTO Sites (siteID,url) values(?,?);");
            ps.setString(1,  site.siteID);
            ps.setString(2, site.url);
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to add site: " + e.getMessage());
        }
    }

    public List<Site> getAllSites() throws Exception {
        
        List<Site> allSites = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM Sites";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Site s = generateSite(resultSet);
                allSites.add(s);
            }
            resultSet.close();
            statement.close();
            return allSites;

        } catch (Exception e) {
            throw new Exception("Failed in getting sites: " + e.getMessage());
        }
    }
    
    private Site generateSite(ResultSet resultSet) throws Exception {
        String siteID  = resultSet.getString("timestampID");
        String url = resultSet.getString("url");
        return new Site(siteID, url);
    }

}
