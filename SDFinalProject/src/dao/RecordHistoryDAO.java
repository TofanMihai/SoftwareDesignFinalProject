package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.connection.ConnectionFactory;
import database.objects.RecordHistory;

public class RecordHistoryDAO extends AbstractDAO<RecordHistory>
{
	private String selectRecordsQuery = "SELECT * FROM finalprojectsd.recordhistory WHERE username = ? ORDER BY idRecordHistory DESC LIMIT 10";

	public List<RecordHistory> select(String usernameSearched)
	{
		List<RecordHistory> reportedList = new ArrayList<RecordHistory>();
    	PreparedStatement st = null;
    	ResultSet results = null;
    	
    	Connection connect = ConnectionFactory.getConnection();
    	try 
    	{
			st = connect.prepareStatement(selectRecordsQuery);
			st.setString(1, usernameSearched);
			
			results = st.executeQuery();

			while(results.next())
			{
				String username = results.getString("username");
				int score = results.getInt("score");
				int level = results.getInt("level");
				
				RecordHistory newRecord = new RecordHistory(username, score, level);
				reportedList.add(newRecord);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return reportedList;
	}
	





}
