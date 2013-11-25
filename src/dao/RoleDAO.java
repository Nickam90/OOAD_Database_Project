package dao;

import java.util.List;
import dto.RoleDTO;
import exceptions.DALException;

public interface RoleDAO {

	void createRole(RoleDTO role) throws DALException;
	RoleDTO getRole(int userId, int tournamentId) throws DALException;
	List<RoleDTO> getRoleList() throws DALException;
	List<RoleDTO> getTournamentRoleList(int userId, int role) throws DALException ;
	void updateRole(RoleDTO role) throws DALException;
	void disableRole(RoleDTO role) throws DALException;
	
}
