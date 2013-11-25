package dao;

import java.util.List;
import dto.TeamDTO;
import exceptions.DALException;

public interface TeamDAO {
	
	void createTeam(TeamDTO team) throws DALException;
	TeamDTO getTeam(int teamId) throws DALException;
	List<TeamDTO> getTeamList() throws DALException;
	void updateTeam(TeamDTO team) throws DALException;
	void disableTeam(TeamDTO team) throws DALException;

}
