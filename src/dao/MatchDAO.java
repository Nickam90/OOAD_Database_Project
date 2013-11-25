package dao;

import java.util.List;
import dto.MatchDTO;
import exceptions.DALException;

public interface MatchDAO {
	
	void createMatch(MatchDTO match) throws DALException;
	MatchDTO getMatch(int matchId) throws DALException;
	List<MatchDTO> getMatchList() throws DALException;
	void updateMatch(MatchDTO match) throws DALException;
	void disableMatch(MatchDTO match) throws DALException;

}
