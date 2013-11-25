package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.TeamLeaderDAO;
import database.MySQLConnector;
import dto.TeamLeaderDTO;
import exceptions.DALException;

public class TeamLeaderDAOImpl implements TeamLeaderDAO {

	private MySQLConnector conn = null;

	public TeamLeaderDAOImpl() throws DALException{
		conn = new MySQLConnector();
	}

	@Override
	public void createTeamLeader(TeamLeaderDTO teamLeader) throws DALException {
		conn.doUpdate("INSERT INTO TeamLeader(team_id, user_id) VALUES" + "("
				+ teamLeader.getTeamId() + ",'" + teamLeader.getUserId() + "')");
	}

	@Override
	public TeamLeaderDTO getTeamLeader(int teamId) throws DALException {
		ResultSet rs = conn.doQuery("SELECT * FROM TeamLeader WHERE team_id = "
				+ teamId);
		try {
			if (!rs.first())
				throw new DALException("The team leader for team " + teamId
						+ " dont exist");
			return new TeamLeaderDTO(rs.getInt(1), rs.getInt(2));
		} catch (Exception e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<TeamLeaderDTO> getTeamLeaderList() throws DALException {
		List<TeamLeaderDTO> list = new ArrayList<TeamLeaderDTO>();
		ResultSet rs = conn.doQuery("SELECT*FROM TeamLeader");
		try {
			while (rs.next()) {
				list.add(new TeamLeaderDTO(rs.getInt(1), rs.getInt(2)));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public void updateTeamLeader(TeamLeaderDTO teamLeader) throws DALException {
		conn.doUpdate("UPDATE TeamLeader SET team_id = '"
				+ teamLeader.getTeamId() + "', user_id = '"
				+ teamLeader.getUserId() + "'  WHERE team_id = "
				+ teamLeader.getTeamId());

	}

	@Override
	public void disableTeamLeader(TeamLeaderDTO role) throws DALException {
		// TODO Auto-generated method stub

	}

}
