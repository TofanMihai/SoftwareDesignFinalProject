package database.objects;

public class RecordHistory 
{
	private int idRecordHistory;
	private String username;
	private int score;
	private int level;
	
	public RecordHistory()
	{
		
	}

	public RecordHistory(String username, int score, int level) {
		super();
		this.username = username;
		this.score = score;
		this.level = level;
	}

	public int getIdRecordHistory() {
		return idRecordHistory;
	}

	public void setIdRecordHistory(int idRecordHistory) {
		this.idRecordHistory = idRecordHistory;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "RecordHistory [" +"username=" + username + ", score=" + score
				+ ", level=" + level + "]";
	}
	
	
	
	
}
