package test;

import java.util.List;

import daoimpl.TournamentDAOImpl;
import daoimpl.UserDAOImpl;
import dto.TournamentDTO;
import dto.UserDTO;
import exceptions.DALException;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		try {
			
			System.out.println("Bruger test!!: ");
			UserDAOImpl u = new UserDAOImpl();
			System.out.println(u.getUserId("figu"));
			System.out.println(u.getUser(u.getUserId("figu")));
			
			List<UserDTO> userlist = u.getUserList();
			
			for (int i = 0; i < userlist.size(); i++) {
				if (i == 0)
					System.out.println("Liste over brugere: \n");
				System.out.println(userlist.get(i).getUsername());
			}
			
			
			System.out.println();
			System.out.println("Tournament test!!: ");
			TournamentDAOImpl t = new TournamentDAOImpl();
			
			List<TournamentDTO> tournlist = t.getTournamentList();
			
			for (int i = 0; i < tournlist.size(); i++) {
				if (i == 0)
					System.out.println("Liste over turneringer: \n");
				System.out.println(tournlist.get(i).getTournamentName());
			}
			
			
			
			
		} catch (DALException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

}
