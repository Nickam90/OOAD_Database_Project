package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RoleDAO;
import dao.TeamDAO;
import dao.TeamInfoDAO;
import dao.TournamentDAO;
import daoimpl.RoleDAOImpl;
import daoimpl.TeamDAOImpl;
import daoimpl.TeamInfoDAOImpl;
import daoimpl.TournamentDAOImpl;
import dto.RoleDTO;
import dto.TeamDTO;
import dto.TeamInfoDTO;
import dto.TournamentDTO;
import dto.UserDTO;
import exceptions.DALException;

/**
 * Servlet implementation class ViewTournament
 */
@WebServlet("/ViewTournament")
public class ViewTournament extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserDTO udto;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewTournament() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDTO udto;
		udto = (UserDTO) request.getSession().getAttribute("userObject");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	}
	public void joinTour(int tournamentId){

		try {

			TeamDAO teamDAO = new TeamDAOImpl();
			TeamInfoDAO teamIDAO = new TeamInfoDAOImpl();
			RoleDAO roleDAO = new RoleDAOImpl();
			List<TeamInfoDTO> teamIList = teamIDAO.getTeamList();
			List<TeamDTO> teamList = teamDAO.getTeamPlayerList();

			TournamentDAO tourDAO = new TournamentDAOImpl();
			TournamentDTO tourDTO = tourDAO.getTournament(tournamentId);

			for(TeamInfoDTO teamIDTO : teamIList){
				if(teamIDTO.getUserId()==udto.getId()&&teamIDAO.getTeam(teamIDTO.getTeamId()).getSport().equals(tourDTO.getSport())){
					for(TeamDTO teamDTO: teamList){
						if(teamDTO.getTeamId()==teamIDAO.getTeam(teamIDTO.getTeamId()).getTeamId()){
							roleDAO.createRole(new RoleDTO(teamDTO.getUserId(), tournamentId, 2));
						}
					}
				}
				else{
					System.out.println("Fejl bruger er ikke teamleader for et hold til turneringens sport");
				}
			}

		} catch (DALException e) {

			e.printStackTrace();
		}

	}
}


