package dao;

import java.util.List;

import dto.TeamDTO;
import exceptions.DALException;

public interface TeamDAO {

	void createTeamPlayer(TeamDTO teamPlayer) throws DALException;
	List<TeamDTO> getTeamPlayerList() throws DALException;
	void updateTeamPlayer(TeamDTO team) throws DALException;
	void disableTeamLeader(TeamDTO role) throws DALException;
	
}