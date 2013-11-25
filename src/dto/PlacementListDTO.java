package dto;

public class PlacementListDTO {
	
	private int tournamentId;
	private int teamId;
	private int placement;
	
	public PlacementListDTO(int tournementId, int teamId, int placement){
		this.tournamentId=tournementId;
		this.teamId=teamId;
		this.placement=placement;
	}

	public int getTournamentId() {return tournamentId;}
	public void setTournamentId(int tournamentId) {this.tournamentId = tournamentId;}
	public int getTeamId() {return teamId;}
	public void setTeamId(int teamId) {this.teamId = teamId;}
	public int getPlacement() {return placement;}
	public void setPlacement(int placement) {this.placement = placement;}
	
}
