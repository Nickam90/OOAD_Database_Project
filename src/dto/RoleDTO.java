package dto;

public class RoleDTO {
	
	private int userId;
	private int tournamentId;
	private int role;	//	eller skal det være en int?

	public RoleDTO(int userId, int tournementId, int role){
		this.userId=userId;
		this.tournamentId=tournementId;
		this.role=role;
	}
 
	
	public int getUserId() {return userId;}
	public void setUserId(int userId) {this.userId = userId;}
	public int getTournamentId() {return tournamentId;}
	public void setTournamentId(int tournamentId) {this.tournamentId = tournamentId;}
	public int getRole() {return role;}
	public void setRole(int role) {this.role = role;}
	
}
