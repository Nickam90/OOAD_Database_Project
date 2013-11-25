package test;

import java.util.List;

import dao.RoleDAO;
import dao.TournamentDAO;
import dao.UserDAO;
import daoimpl.RoleDAOImpl;
import daoimpl.TournamentDAOImpl;
import daoimpl.UserDAOImpl;
import dto.RoleDTO;
import dto.TournamentDTO;
import exceptions.DALException;

public class TestListe {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try{

			UserDAO user = new UserDAOImpl();
			String listElementP ="";
			String listElementO ="";

			TournamentDAO tournament = new TournamentDAOImpl();	
			RoleDAO role = new RoleDAOImpl();

			List<RoleDTO> roleListO = role.getTournamentRoleList(5, 1);

			for(RoleDTO roles: roleListO){
				TournamentDTO tourn = tournament.getTournament(roles.getTournamentId());
				listElementO += tourn.getTournamentName();
			}
			
			System.out.println(listElementO);


			List<RoleDTO> roleListP = role.getTournamentRoleList(5, 2);

			for(RoleDTO roles: roleListP){
				TournamentDTO tourn = tournament.getTournament(roles.getTournamentId());
				listElementP += tourn.getTournamentName();
			}
			
			System.out.println(listElementP);

		}
		catch(DALException e){

		}

	}

}
