package dto;

public class TournamentDTO {

	private int status, tournament_format, maxParticipants;
	private String tournamentName, sport, info, startDate;
	private int tournament_type;
	private int id;

	public TournamentDTO(int id, String tournamentName, String sport, int maxParticipants, int status, String startDate, String info, int tournament_type, int tournament_format){

		this.id=id;
		this.tournamentName=tournamentName;
		this.sport=sport;
		this.maxParticipants=maxParticipants;
		this.status = status;
		this.startDate=startDate;
		this.info=info;
		this.tournament_type=tournament_type;
		this.tournament_format=tournament_format;

	}

	public TournamentDTO(String tournamentName, String sport, int maxParticipants, int status, String startDate, String info, int tournament_type, int tournament_format){

		this.tournamentName=tournamentName;
		this.sport=sport;
		this.maxParticipants=maxParticipants;
		this.status = status;
		this.startDate=startDate;
		this.info=info;
		this.tournament_type=tournament_type;
		this.tournament_format=tournament_format;

	}


	public String getTournamentName() {return tournamentName;}
	public void setTournamentName(String tournamentName) {this.tournamentName = tournamentName;}
	public String getSport() {return sport;}
	public void setSport(String sport) {this.sport = sport;}
	public int getMaxParticipants() {return maxParticipants;}
	public void setMaxParticipants(int maxParticipants) {this.maxParticipants = maxParticipants;}
	public String getStartDate() {return startDate;}
	public void setStartDate(String startDate) {this.startDate = startDate;}
	public String getInfo() {return info;}
	public void setInfo(String info) {this.info = info;}
	public int getType() {return tournament_type;}
	public int getStatus() {return status;}
	public void setStatus(int status) {this.status = status;}
	public int getFormat() {return tournament_format;}


	public int getTournament_format() {
		return tournament_format;
	}

	public void setTournament_format(int tournament_format) {
		this.tournament_format = tournament_format;
	}

	public int getTournament_type() {
		return tournament_type;
	}

	public void setTournament_type(int tournament_type) {
		this.tournament_type = tournament_type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
