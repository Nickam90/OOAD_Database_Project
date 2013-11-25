package dto;

public class TeamDTO {
	
	private int teamId;
	private String teamName;
	private String sport;
	private int userId;
	
	
	public TeamDTO(int teamId, String teamName, String sport, int userId){
		this.teamId=teamId;
		this.teamName=teamName;
		this.sport=sport;
		this.userId=userId;
		
	}

	public int getTeamId() {return teamId;}
	public void setTeamId(int teamId) {this.teamId = teamId;}
	public String getTeamName() {return teamName;}
	public void setTeamName(String teamName) {this.teamName = teamName;}
	public String getSport() {return sport;}
	public void setSport(String sport) {this.sport = sport;}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
