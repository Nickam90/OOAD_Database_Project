package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.TournamentDAO;
import database.MySQLConnector;
import dto.TournamentDTO;
import exceptions.DALException;

public class TournamentDAOImpl implements TournamentDAO {

	private MySQLConnector conn = null;

	public TournamentDAOImpl() throws DALException {
		conn = new MySQLConnector();
	}

	@Override
	public void createTournament(TournamentDTO tournament) throws DALException {
		conn.doUpdate("INSERT INTO Tournament(name, sport, tournament_size, status, startdate, info, tournament_type, tournament_format) VALUES"
				+ "('"
				+ tournament.getTournamentName()
				+ "','"
				+ tournament.getSport()
				+ "','"
				+ tournament.getMaxParticipants()
				+ "','"
				+ tournament.getStatus()
				+ "','"
				+ tournament.getStartDate()
				+ "','"
				+ tournament.getInfo()
				+ "','"
				+ tournament.getType()
				+ "','"
				+ tournament.getFormat()
				+ "')");
	}

	@Override
	public TournamentDTO getTournament(int tournamentId) throws DALException {
		ResultSet rs = conn
				.doQuery("SELECT * FROM Tournament WHERE tournament_id = "
						+ tournamentId);
		try {
			if (!rs.first())
				throw new DALException("The tournament " + tournamentId
						+ " dont exist");
			return new TournamentDTO(rs.getInt(1), rs.getString(2),
					rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6),
					rs.getString(7), rs.getInt(8), rs.getInt(9));
		} catch (Exception e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<TournamentDTO> getTournamentList() throws DALException {
		List<TournamentDTO> list = new ArrayList<TournamentDTO>();
		ResultSet rs = conn.doQuery("SELECT*FROM Tournament");
		try {
			while (rs.next()) {
				list.add(new TournamentDTO(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6),
						rs.getString(7), rs.getInt(8), rs.getInt(9)));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}
	
	

	@Override
	public void updateTournament(TournamentDTO tournament) throws DALException {
		conn.doUpdate("UPDATE Tournament SET name = '"
				+ tournament.getTournamentName() + "', sport = '"
				+ tournament.getSport() + "', tournament_size = '"
				+ tournament.getMaxParticipants() + "', status = '"
				+ tournament.getStatus() + "', startdate = '"
				+ tournament.getStartDate() + "', info = '"
				+ tournament.getInfo() + "', tournament_type = '" 
				+ tournament.getType()+ "', tournament_format = '" 
				+ tournament.getFormat()
				+ "'  WHERE tournament_id = " + tournament.getId());
	}
	

	@Override
	public void disableTournament(TournamentDTO tournament) throws DALException {
		// TODO Auto-generated method stub

	}
	
	public int getTournamentId(String tournamentName) throws DALException {
		ResultSet rs = conn.doQuery("SELECT * FROM Tournament WHERE name = '"
				+ tournamentName + "'");
		try {
			if (!rs.first())
				throw new DALException("The user " + tournamentName + " dont exist");
			return (rs.getInt(1));
		} catch (Exception e) {
			throw new DALException(e);
		}
	}

}
