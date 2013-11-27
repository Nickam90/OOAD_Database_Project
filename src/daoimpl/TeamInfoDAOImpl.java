package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.TeamInfoDAO;
import database.MySQLConnector;
import dto.TeamInfoDTO;
import exceptions.DALException;

public class TeamInfoDAOImpl implements TeamInfoDAO {

	public MySQLConnector conn = null;
	

	public TeamInfoDAOImpl() throws DALException {
		conn = new MySQLConnector();
	}

	@Override
	public void createTeam(TeamInfoDTO team) throws DALException {
		conn.doUpdate("INSERT INTO TeamInfo(team_name, sport, user_id) VALUES"
				+ "('"
				+ team.getTeamName()
				+ "','"
				+ team.getSport() 
				+ "','"
				+ team.getUserId() +"')");
	}

	@Override
	public TeamInfoDTO getTeam(int teamId) throws DALException {
		ResultSet rs = conn.doQuery("SELECT * FROM TeamInfo WHERE team_id = "
				+ teamId);
		try {
			if (!rs.first())
				throw new DALException("The team " + teamId + " dont exist");
			return new TeamInfoDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
		} catch (Exception e) {
			throw new DALException(e);
		}
	}
	
	@Override
	public int getTeamId(String teamname) throws DALException {
		ResultSet rs = conn.doQuery("SELECT * FROM TeamInfo WHERE team_name = '"
				+ teamname +"'");
		try {
			if (!rs.first())
				throw new DALException("The team " + teamname + " dont exist");
			return rs.getInt(1);
		} catch (Exception e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<TeamInfoDTO> getTeamList() throws DALException {
		List<TeamInfoDTO> list = new ArrayList<TeamInfoDTO>();
		ResultSet rs = conn.doQuery("SELECT*FROM TeamInfo");
		try {
			while (rs.next()) {
				list.add(new TeamInfoDTO(rs.getInt(1), rs.getString(2), rs
						.getString(3), rs.getInt(4)));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public void updateTeam(TeamInfoDTO team) throws DALException {
		conn.doUpdate("UPDATE TeamInfo SET team_id = '" + team.getTeamId()
				+ "', team_name = '" + team.getTeamName() + "', sport = '"
				+ team.getSport() + "', user_id = '"
						+ team.getUserId()
				+ "'  WHERE team_id = " + team.getTeamId());
	}
	
	@Override
	public String getTeamLeader(int teamId) throws DALException {
		ResultSet rs = conn.doQuery("SELECT * FROM TeamInfo WHERE team_id = '"
				+ teamId +"'");
		try {
			if (!rs.first())
				throw new DALException("The team leader " + teamId + " dont exist");
			return rs.getString(4);
		} catch (Exception e) {
			throw new DALException(e);
		}
	}

	@Override
	public void disableTeam(TeamInfoDTO team) throws DALException {
		// TODO Auto-generated method stub

	}

}
