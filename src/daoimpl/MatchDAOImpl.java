package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.MatchDAO;
import database.MySQLConnector;
import dto.MatchDTO;
import exceptions.DALException;

public class MatchDAOImpl implements MatchDAO {

	private MySQLConnector conn = null;
	

	public MatchDAOImpl() throws DALException {
		conn = new MySQLConnector();
	}

	public void createMatch(MatchDTO match) throws DALException {

		conn.doUpdate("INSERT INTO MatchInfo(team_a, team_b, tournament_id, match_result) VALUES "
				+ "('"
				+ match.getTeamA()
				+ "','"
				+ match.getTeamB()
				+ "','"
				+ match.getTournamentId()
				+ "','"
				+ match.getResult() + "')");

	}

	public MatchDTO getMatch(int matchId) throws DALException {
		ResultSet rs = conn.doQuery("SELECT * FROM Matchinfo WHERE match_id = "
				+ matchId);
		try {
			if (!rs.first())
				throw new DALException("The match " + matchId + " dont exist");
			return new MatchDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3),
					rs.getInt(4), rs.getString(5));
		} catch (Exception e) {
			throw new DALException(e);
		}

	}

	public List<MatchDTO> getMatchList() throws DALException {
		List<MatchDTO> list = new ArrayList<MatchDTO>();
		ResultSet rs = conn.doQuery("SELECT*FROM Matchinfo");
		try {
			while (rs.next()) {
				list.add(new MatchDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3),
						rs.getInt(4), rs.getString(5)));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	public void updateMatch(MatchDTO match) throws DALException {

		conn.doUpdate("UPDATE MatchInfo SET team_a = '" + match.getTeamA() + "', team_b = '"
				+ match.getTeamB() + "', tournament_id = '"
				+ match.getTournamentId() + "' match_result = '"
				+ match.getResult() + "'  WHERE match_id = "
				+ match.getMatchId());

	}

	@Override
	public void disableMatch(MatchDTO match) throws DALException {
		// TODO Auto-generated method stub
		// Bruges???

	}

}
