package dto;

public class MatchDTO {

	private int matchId;
	private int teamA;
	private int teamB;
	private int tournamentId;
	private String result;

	public MatchDTO(int matchId, int teamA, int teamB, int tournamentId, String result){
		this.matchId=matchId;
		this.teamA=teamA;
		this.teamB=teamB;
		this.tournamentId=tournamentId;
		this.result=result;
	}
	
	public MatchDTO(int teamA, int teamB, int tournamentId, String result){
		this.teamA=teamA;
		this.teamB=teamB;
		this.tournamentId=tournamentId;
		this.result=result;
	}


	public int getMatchId() {return matchId;}
	public void setMatchId(int matchId) {this.matchId = matchId;}
	public int getTeamA() {return teamA;}
	public void setTeamA(int teamA) {this.teamA = teamA;}
	public int getTeamB() {return teamB;}
	public void setTeamB(int teamB) {this.teamB = teamB;}
	public int getTournamentId() {return tournamentId;}
	public void setTournamentId(int tournamentId) {this.tournamentId = tournamentId;}
	public String getResult() {return result;}
	public void setResult(String result) {this.result = result;}
	public String toString(){
		return "Match: " + matchId + "\r" + "Team A: " + teamA + "\r" + "Team B: "
				+ teamB + "\r" + "Tournament ID: " + tournamentId+ "\r" + "Result: "
				+ result;} 

}


