package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ParticipentListDAO;
import database.MySQLConnector;
import dto.ParticipentListDTO;
import dto.PlacementListDTO;
import exceptions.DALException;

public class ParticipentListDAOImpl implements ParticipentListDAO {

private MySQLConnector conn = null;
	

	public ParticipentListDAOImpl() throws DALException {
		conn = new MySQLConnector();
	}

	public void createParticipent(ParticipentListDTO placementList)
			throws DALException {
		conn.doUpdate("INSERT INTO PlacementList(tournement_id, team_id, rank_no) VALUES"
				+ "("
				+ placementList.getTournamentId()
				+ ",'"
				+ placementList.getTeamId()
			 + "')");

	}

	public List<ParticipentListDTO> getParticipentList(int participentId)
			throws DALException {
		List<ParticipentListDTO> list = new ArrayList<ParticipentListDTO>();
		ResultSet rs = conn.doQuery("SELECT*FROM PlacementList");
		try {
			while (rs.next()) {
				list.add(new ParticipentListDTO(rs.getInt(1), rs.getInt(2)));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	public void updateParticipent(ParticipentListDTO participentList)
			throws DALException {
		conn.doUpdate("UPDATE PlacementList SET tournement_id = '"
				+ participentList.getTournamentId() + "', team_id = '"
				+ participentList.getTeamId() + "' WHERE tournement_id = "
				+ participentList.getTournamentId());

	}

	public void disableParticipent(ParticipentListDTO placementList)
			throws DALException {
		// TODO Auto-generated method stub

	}


}
