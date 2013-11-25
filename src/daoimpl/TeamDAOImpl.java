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
	

	public TeamDAOImpl() throws DALException {
		conn = new MySQLConnector();
	}

	@Override
	public void createTeam(TeamDTO team) throws DALException {
		conn.doUpdate("INSERT INTO Teaminfo(team_id, teamname, sport, user_id) VALUES"
				+ "("
				+ team.getTeamId()
				+ ",'"
				+ team.getTeamName()
				+ "','"
				+ team.getSport() 
				+ "','"
				+ team.getUserId() +"')");
	}

	@Override
	public TeamDTO getTeam(int teamId) throws DALException {
		ResultSet rs = conn.doQuery("SELECT * FROM Teaminfo WHERE team_id = "
				+ teamId);
		try {
			if (!rs.first())
				throw new DALException("The team " + teamId + " dont exist");
			return new TeamDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
		} catch (Exception e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<TeamDTO> getTeamList() throws DALException {
		List<TeamDTO> list = new ArrayList<TeamDTO>();
		ResultSet rs = conn.doQuery("SELECT*FROM Teaminfo");
		try {
			while (rs.next()) {
				list.add(new TeamDTO(rs.getInt(1), rs.getString(2), rs
						.getString(3), rs.getInt(4)));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public void updateTeam(TeamDTO team) throws DALException {
		conn.doUpdate("UPDATE Teaminfo SET team_id = '" + team.getTeamId()
				+ "', teamname = '" + team.getTeamName() + "', sport = '"
				+ team.getSport() + "', user_id = '"
						+ team.getUserId()
				+ "'  WHERE team_id = " + team.getTeamId());
	}

	@Override
	public void disableTeam(TeamDTO team) throws DALException {
		// TODO Auto-generated method stub

	}

}
