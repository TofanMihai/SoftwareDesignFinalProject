package bll;

import java.util.List;

import dao.LeaderboardDAO;
import database.objects.Leaderboard;


public class LeaderboardBLL
{
	public LeaderboardDAO leaderboardDAO;
	
	public LeaderboardBLL()
	{
		this.leaderboardDAO = new LeaderboardDAO();
	}
	
	public void insertLeaderboard(Leaderboard leaderboard)
	{	
		List<Leaderboard> leaderboardsList = this.leaderboardDAO.report();
		boolean alreadyRegistered = false;
		
		
		for(Leaderboard l : leaderboardsList)
		{
			if(l.getUsername().equals(leaderboard.getUsername()))
				alreadyRegistered = true;
			
		}
		if(alreadyRegistered == false)
			this.leaderboardDAO.insert(leaderboard);
	}
	
	public void updateLeaderboard(Leaderboard leaderboard, int newScore, int newLevel)
	{
		List<Leaderboard> leaderboardsList = this.leaderboardDAO.report();
		boolean updatable = true;
		
		
		for(Leaderboard l : leaderboardsList)
		{
			if(l.getUsername().equals(leaderboard.getUsername()) &&
			   l.getHighestScore() >= newScore)
				updatable = false;
			else
			if(l.getUsername().equals(leaderboard.getUsername()) &&
			   l.getHighestScore() < newScore)
			{
				updatable = true;
			}
			
		}
		
		System.out.println(updatable);
		if(updatable)
			this.leaderboardDAO.update(leaderboard, newScore, newLevel);
	}
	
	public List<Leaderboard> selectLeaderboard()
	{
		return this.getDAO().select();
	}
	
	
	public LeaderboardDAO getDAO()
	{
		return this.leaderboardDAO;
	}
}
