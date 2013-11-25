package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.PlacementListDAO;
import database.MySQLConnector;
import dto.PlacementListDTO;
import exceptions.DALException;

public class PlacementListDAOImpl implements PlacementListDAO {

	private MySQLConnector conn = null;
	

	public PlacementListDAOImpl() throws DALException {
		conn = new MySQLConnector();
	}

	public void createPlacementList(PlacementListDTO placementList)
			throws DALException {
		conn.doUpdate("INSERT INTO PlacementList(tournement_id, team_id, rank_no) VALUES"
				+ "("
				+ placementList.getTournamentId()
				+ ",'"
				+ placementList.getTeamId()
				+ "','"
				+ placementList.getPlacement() + "')");

	}

	public List<PlacementListDTO> getPlacementList(int placementListId)
			throws DALException {
		List<PlacementListDTO> list = new ArrayList<PlacementListDTO>();
		ResultSet rs = conn.doQuery("SELECT*FROM PlacementList");
		try {
			while (rs.next()) {
				list.add(new PlacementListDTO(rs.getInt(1), rs.getInt(2), rs
						.getInt(3)));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	public void updatePlacementList(PlacementListDTO placementList)
			throws DALException {
		conn.doUpdate("UPDATE PlacementList SET tournement_id = '"
				+ placementList.getTournamentId() + "', team_id = '"
				+ placementList.getTeamId() + "', rank_no = '"
				+ placementList.getPlacement() + "'  WHERE tournement_id = "
				+ placementList.getTournamentId());

	}

	public void disablePlacementList(PlacementListDTO placementList)
			throws DALException {
		// TODO Auto-generated method stub

	}

}
