package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.RoleDAO;
import database.MySQLConnector;
import dto.RoleDTO;
import exceptions.DALException;

public class RoleDAOImpl implements RoleDAO {

	public MySQLConnector conn = null;
	

	public RoleDAOImpl() throws DALException{
		conn = new MySQLConnector();
	}

	@Override
	public void createRole(RoleDTO role) throws DALException {
		conn.doUpdate("INSERT INTO Role(user_id, tournament_id, role) VALUES"
				+ "(" + role.getUserId() + ",'" + role.getTournamentId()
				+ "','" + role.getRole() + "')");
	}

	@Override
	public RoleDTO getRole(int userId, int tournamentId) throws DALException {
		ResultSet rs = conn.doQuery("SELECT * FROM Role WHERE user_id = "
				+ userId + " AND " + "tournament_id = "+ tournamentId);
		try {
			if (!rs.first())
				throw new DALException("The user " + userId
						+ " role dont exist");
			return new RoleDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3));
		} catch (Exception e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<RoleDTO> getRoleList() throws DALException {
		List<RoleDTO> list = new ArrayList<RoleDTO>();
		ResultSet rs = conn.doQuery("SELECT*FROM Role");
		try {
			while (rs.next()) {
				list.add(new RoleDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}
	
	public List<RoleDTO> getTournamentRoleList(int userId, int role) throws DALException {
		List<RoleDTO> list = new ArrayList<RoleDTO>();
		ResultSet rs = conn.doQuery("SELECT*FROM Role WHERE user_id = "
				+ userId + " AND " + "role = "+ role);
		try {
			while (rs.next()) {
				list.add(new RoleDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public void updateRole(RoleDTO role) throws DALException {
		conn.doUpdate("UPDATE Role SET user_id = '" + role.getUserId()
				+ "', tournement_id = '" + role.getTournamentId()
				+ "', role = '" + role.getRole() + "'  WHERE role = "
				+ role.getRole());
	}

	@Override
	public void disableRole(RoleDTO role) throws DALException {
		conn.doUpdate("DELETE FROM Role WHERE user_id = "
				+ role.getUserId() + " AND " + "role = "+ role.getRole());

	}

}
