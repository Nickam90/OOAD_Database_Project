package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.TeamDAO;
import database.MySQLConnector;
import dto.TeamDTO;
import exceptions.DALException;

public class TeamDAOImpl implements TeamDAO {

	private MySQLConnector conn = null;

	public TeamDAOImpl() throws DALException{
		conn = new MySQLConnector();
	}

	@Override
	public void createTeamPlayer(TeamDTO team) throws DALException {
		conn.doUpdate("INSERT INTO Team(team_id, user_id) VALUES" + "("
				+ team.getTeamId() + ",'" + team.getUserId() + "')");
	}

	@Override
	public List<TeamDTO> getTeamPlayerList() throws DALException {
		List<TeamDTO> list = new ArrayList<TeamDTO>();
		ResultSet rs = conn.doQuery("SELECT*FROM Team");
		try {
			while (rs.next()) {
				list.add(new TeamDTO(rs.getInt(1), rs.getInt(2)));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}
	
	@Override
	public List<TeamDTO> getMemberList(int teamId) throws DALException {
		List<TeamDTO> list = new ArrayList<TeamDTO>();
		ResultSet rs = conn.doQuery("SELECT*FROM Team WHERE team_id = " + teamId);
		try {
			while (rs.next()) {
				list.add(new TeamDTO(rs.getInt(1), rs.getInt(2)));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public void updateTeamPlayer(TeamDTO team) throws DALException {
		conn.doUpdate("UPDATE Team SET team_id = '"
				+ team.getTeamId() + "', user_id = '"
				+ team.getUserId() + "'  WHERE team_id = "
				+ team.getTeamId());

	}

	@Override
	public void disableTeamLeader(TeamDTO role) throws DALException {
		// TODO Auto-generated method stub

	}

}
