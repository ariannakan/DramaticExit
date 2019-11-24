package mua.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import mua.db.DatabaseUtil;
import mua.model.Video;

/**
 * Note that CAPITALIZATION matters regarding the table name. If you create with 
 * a capital "Constants" then it must be "Constants" in the SQL queries.
 * 
 * @author b193733 DramaticExit
 *
 */

public class VideosDAO {

	java.sql.Connection conn;

    public VideosDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }

    public Video getVideo(String name) throws Exception {
        
        try {
            Video constant = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Constants WHERE name=?;");
            ps.setString(1,  name);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                constant = generateConstant(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return constant;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting constant: " + e.getMessage());
        }
    }
    
    public boolean updateVideo(Video video) throws Exception {
        try {
        	String query = "UPDATE Constants SET value=? WHERE name=?;";
        	PreparedStatement ps = conn.prepareStatement(query);
            ps.setDouble(1, constant.value);
            ps.setString(2, constant.name);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);
        } catch (Exception e) {
            throw new Exception("Failed to update report: " + e.getMessage());
        }
    }
    
    public boolean deleteVideo(Video video) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Constants WHERE name = ?;");
            ps.setString(1, video.name);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }


    public boolean addVideo(Video constant) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Constants WHERE name = ?;");
            ps.setString(1, constant.name);
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
                Constant c = generateConstant(resultSet);
                resultSet.close();
                return false;
            }

            ps = conn.prepareStatement("INSERT INTO Constants (name,value) values(?,?);");
            ps.setString(1,  constant.name);
            ps.setDouble(2,  constant.value);
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }

    public List<Video> getAllConstants() throws Exception {
        
        List<Video> allConstants = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM Constants";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Video c = generateConstant(resultSet);
                allConstants.add(c);
            }
            resultSet.close();
            statement.close();
            return allConstants;

        } catch (Exception e) {
            throw new Exception("Failed in getting books: " + e.getMessage());
        }
    }
    
    private Video generateConstant(ResultSet resultSet) throws Exception {
        String name  = resultSet.getString("name");
        Double value = resultSet.getDouble("value");
        return new Constant (name, value);
    }
}
