package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.connection.ConnectionFactory;
import database.objects.Leaderboard;

public class LeaderboardDAO extends AbstractDAO<Leaderboard>
{
	private String updateLeaderboardQuery = "UPDATE finalprojectsd.leaderboard SET highestScore = ?, highestLevel = ? WHERE username = ?";
	private String selectLeaderboardQuery = "SELECT * FROM finalprojectsd.leaderboard ORDER BY highestScore DESC LIMIT 10";
	
	public void update(Leaderboard leaderboard, int newHighScore, int newHighLevel)
	{
		PreparedStatement st = null;
		
		Connection connect = ConnectionFactory.getConnection();
		try 
		{
			st = connect.prepareStatement(updateLeaderboardQuery);
			st.setInt(1, newHighScore);
			st.setInt(2, newHighLevel);
			st.setString(3, leaderboard.getUsername());
				
			st.executeUpdate();
		    
		}
		catch (SQLException e)
		{
			
			e.printStackTrace();
			
		}
	}
	
	public List<Leaderboard> select()
	{
		List<Leaderboard> reportedList = new ArrayList<Leaderboard>();
    	PreparedStatement st = null;
    	ResultSet results = null;
    	
    	Connection connect = ConnectionFactory.getConnection();
    	try 
    	{
			st = connect.prepareStatement(selectLeaderboardQuery);
			
			results = st.executeQuery();

			while(results.next())
			{
				String username = results.getString("username");
				int highestScore = results.getInt("highestScore");
				int highestLevel = results.getInt("highestLevel");
				
				Leaderboard leaderboard = new Leaderboard(username, highestScore, highestLevel);
				reportedList.add(leaderboard);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return reportedList;
	}
	





}
