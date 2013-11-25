package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.UserDAO;
import database.MySQLConnector;
import dto.UserDTO;
import exceptions.DALException;

public class UserDAOImpl implements UserDAO {

	private MySQLConnector conn = null;

	public UserDAOImpl() throws DALException {
		conn = new MySQLConnector();
	}

	@Override
	public void createUser(UserDTO user) throws DALException {
		conn.doUpdate("INSERT INTO User(username, password, email) VALUES"
				+ "('"
				+ user.getUsername()
				+ "','"
				+ user.getPassword()
				+ "','"
				+ user.getEmail() + "')");
	}

	@Override
	public UserDTO getUser(int userId) throws DALException {
		ResultSet rs = conn.doQuery("SELECT * FROM User WHERE user_id = "
				+ userId);
		try {
			if (!rs.first())
				throw new DALException("The user " + userId + " dont exist");
			return new UserDTO(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4));
		} catch (Exception e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<UserDTO> getUserList() throws DALException {
		List<UserDTO> list = new ArrayList<UserDTO>();
		ResultSet rs = conn.doQuery("SELECT*FROM User");
		try {
			while (rs.next()) {
				list.add(new UserDTO(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4)));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public void updateUser(UserDTO user) throws DALException {
		conn.doUpdate("UPDATE User SET username = '" + user.getUsername() + "', password = '"
				+ user.getPassword() + "', email = '" + user.getEmail()
				 + "'  WHERE user_id = "
				+ user.getId());

	}

	@Override
	public void disableUser(UserDTO user) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getUserId(String username) throws DALException {
		ResultSet rs = conn.doQuery("SELECT * FROM User WHERE username = '"
				+ username + "'");
		try {
			if (!rs.first())
				throw new DALException("The user " + username + " dont exist");
			return (rs.getInt(1));
		} catch (Exception e) {
			throw new DALException(e);
		}
	}

}
