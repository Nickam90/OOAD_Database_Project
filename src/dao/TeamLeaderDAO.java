package dao;

import java.util.List;

import dto.TeamLeaderDTO;
import exceptions.DALException;

public interface TeamLeaderDAO {

	void createTeamLeader(TeamLeaderDTO teamLeader) throws DALException;
	TeamLeaderDTO getTeamLeader(int teamId) throws DALException;
	List<TeamLeaderDTO> getTeamLeaderList() throws DALException;
	void updateTeamLeader(TeamLeaderDTO teamLeader) throws DALException;
	void disableTeamLeader(TeamLeaderDTO role) throws DALException;
	
}
