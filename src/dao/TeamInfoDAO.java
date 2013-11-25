package dao;

import java.util.List;
import dto.TeamInfoDTO;
import exceptions.DALException;

public interface TeamInfoDAO {
	
	void createTeam(TeamInfoDTO team) throws DALException;
	TeamInfoDTO getTeam(int teamId) throws DALException;
	TeamInfoDTO getTeamId(String teamname) throws DALException;
	List<TeamInfoDTO> getTeamList() throws DALException;
	void updateTeam(TeamInfoDTO team) throws DALException;
	void disableTeam(TeamInfoDTO team) throws DALException;

}
