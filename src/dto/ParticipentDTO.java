package dto;

public class ParticipentDTO {

	private int tournamentId;
	private int teamId;

	public ParticipentDTO(int teamId, int tournamentId){
		this.teamId=teamId;
		this.tournamentId=tournamentId;

	}

	public int getTournamentId() {return tournamentId;}
	public void setTournamentId(int tournamentId) {this.tournamentId = tournamentId;}
	public int getTeamId() {return teamId;}
	public void setTeamId(int teamId) {this.teamId = teamId;}


}
