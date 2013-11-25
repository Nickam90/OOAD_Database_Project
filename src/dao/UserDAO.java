package dao;

import java.util.List;
import exceptions.DALException;
import dto.UserDTO;

public interface UserDAO {
	
	void createUser(UserDTO user) throws DALException;
	UserDTO getUser(int userId) throws DALException;
	int getUserId(String username) throws DALException;
	List<UserDTO> getUserList() throws DALException;
	void updateUser(UserDTO user) throws DALException;
	void disableUser(UserDTO user) throws DALException;
	
}
