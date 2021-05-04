package database.objects;

public class Leaderboard 
{
	private int idLeaderboard;
	private String username;
	private int highestScore;
	private int highestLevel;
	
	
	
	public Leaderboard()
	{
		
	}
	
	public Leaderboard(String username, int highestScore, int highestLevel) 
	{
		super();
		this.username = username;
		this.highestScore = highestScore;
		this.highestLevel = highestLevel;
	}
	public int getIdLeaderboard() {
		return idLeaderboard;
	}
	public void setIdLeaderboard(int idLeaderboard) {
		this.idLeaderboard = idLeaderboard;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getHighestScore() {
		return highestScore;
	}
	public void setHighestScore(int highestScore) {
		this.highestScore = highestScore;
	}
	public int getHighestLevel() {
		return highestLevel;
	}
	public void setHighestLevel(int highestLevel) {
		this.highestLevel = highestLevel;
	}
	
	@Override
	public String toString() {
		return "Leaderboard [" + "username=" + username + ", highestScore="
				+ highestScore + ", highestLevel=" + highestLevel + "]";
	}
}
