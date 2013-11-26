package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.BracketsDAO;
import database.MySQLConnector;
import dto.BracketsDTO;
import dto.ParticipantListDTO;
import exceptions.DALException;

public class BracketsDAOImpl implements BracketsDAO {

	private MySQLConnector conn = null;

	public BracketsDAOImpl() throws DALException {
		conn = new MySQLConnector();
	}

	@Override
	public void createBracket(BracketsDTO bracket) throws DALException {
			conn.doUpdate("INSERT INTO Brackets(tournement_id, JSON) VALUES"
					+ "("
					+ bracket.getTournamentId()
					+ ",'"
					+ bracket.getJSON()
					+ "')");

	}

	@Override
	public BracketsDTO getBracket(int tournamentId) throws DALException {
		ResultSet rs = conn.doQuery("SELECT * FROM Brackets WHERE tournament_id = " + tournamentId);
		try {
			if (!rs.first())
				throw new DALException("The bracket for tournament " + tournamentId + " dont exist");
			return new BracketsDTO(rs.getInt(1), rs.getString(2));
		} catch (Exception e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<BracketsDTO> getBracketList() throws DALException {
		List<BracketsDTO> list = new ArrayList<BracketsDTO>();
		ResultSet rs = conn.doQuery("SELECT*FROM Brackets");
		try {
			while (rs.next()) {
				list.add(new BracketsDTO(rs.getInt(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public void updateBracket(BracketsDTO bracket) throws DALException {
		conn.doUpdate("UPDATE Brackets SET tournement_id = '"
				+ bracket.getTournamentId() + "', JSON = '"
				+ bracket.getJSON() + "' WHERE tournement_id = "
				+ bracket.getTournamentId());
		
	}

	@Override
	public void disableBracket(BracketsDTO bracket) throws DALException {
		// TODO Auto-generated method stub
		
	}

}