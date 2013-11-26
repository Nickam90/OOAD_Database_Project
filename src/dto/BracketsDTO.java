package dto;

public class BracketsDTO {

	private int tournamentId;
	private String JSON;

	public BracketsDTO(int tournamentId, String JSON){
		this.JSON=JSON;
		this.tournamentId=tournamentId;

	}

	public int getTournamentId() {return tournamentId;}
	public void setTournamentId(int tournamentId) {this.tournamentId = tournamentId;}
	public String getJSON() {return JSON;}
	public void setJSON(String JSON) {this.JSON = JSON;}


}