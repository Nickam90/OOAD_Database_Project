package test;

import dao.RoleDAO;
import dao.TournamentDAO;
import dao.UserDAO;
import daoimpl.RoleDAOImpl;
import daoimpl.TournamentDAOImpl;
import daoimpl.UserDAOImpl;
import database.MySQLConnector;
import dto.TournamentDTO;
import exceptions.DALException;

public class testRole {

	public static void main(String[] args) {

		try {
			
			UserDAO user = new UserDAOImpl();
			TournamentDAO tour = new TournamentDAOImpl();
			RoleDAO role = new RoleDAOImpl();
			
			int userId = user.getUserId("figu");
						
			System.out.println(role.getRole(userId, 1).getUserId() + " " + role.getRole(userId, 1).getRole() + " " + role.getRole(userId, 1).getTournamentId());
			
			
			
			
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
