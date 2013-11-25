package dao;

import java.util.List;
import dto.TournamentDTO;
import exceptions.DALException;

public interface TournamentDAO {
	
	void createTournament(TournamentDTO tournament) throws DALException;
	TournamentDTO getTournament(int tournamentId) throws DALException;
	int getTournamentId(String tournamentName) throws DALException;
	List<TournamentDTO> getTournamentList() throws DALException;
	void updateTournament(TournamentDTO tournament) throws DALException;
	void disableTournament(TournamentDTO tournament) throws DALException;

}
