package bll;

import java.util.List;

import dao.RecordHistoryDAO;
import database.objects.RecordHistory;


public class RecordHistoryBLL
{
	public RecordHistoryDAO recordHistoryDAO;

	
	public RecordHistoryBLL()
	{
		this.recordHistoryDAO = new RecordHistoryDAO();
	}
	
	public void insertRecordHistory(RecordHistory recordHistory)
	{	
		this.recordHistoryDAO.insert(recordHistory);
	}
	
	public List<RecordHistory> selectRecordHistory(String username)
	{
		return this.recordHistoryDAO.select(username);	
	}
	
	
	public RecordHistoryDAO getDAO()
	{
		return this.recordHistoryDAO;
	}
}
