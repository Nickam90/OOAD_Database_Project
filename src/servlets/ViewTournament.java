package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ErrorService;
import service.ValidateInput;

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

	private UserDTO udto;
	private int tId;
	private TeamDAO teamDAO;
	private TeamInfoDAO teamIDAO;
	private RoleDAO roleDAO;
	private List<TeamInfoDTO> teamIList;
	private List<TeamDTO> teamList;
	private TournamentDAO tourDAO;
	private TournamentDTO tourDTO;
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


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		udto = (UserDTO) request.getSession().getAttribute("userObject");
		TournamentDTO tDTO = (TournamentDTO) request.getSession().getAttribute("Tournament");
		tId = tDTO.getId();

		if(tDTO.getStatus()==0){

			if (request.getParameter("joinButton") != null) {
				System.out.println("Joining Tour");
				joinTour(tId,request,response);
			}
			else if(request.getParameter("leaveButton") != null){
				leaveTour(tId,request,response);
			}
		}
		else{
			ValidateInput validate = new ValidateInput();
			ErrorService error = validate.createError();
			error.setError("<div class=\"alert alert-danger\">Cant join or leave a tournament in progress</div>");
			request.setAttribute("error", error);
			this.getServletContext().getRequestDispatcher("/LogIn.jsp").forward(request, response);
		}

	}
	private void leaveTour(int tournamentId, HttpServletRequest request, HttpServletResponse response) {

		System.out.println("Leave pressed");

		try{
			teamDAO = new TeamDAOImpl();
			teamIDAO = new TeamInfoDAOImpl();
			roleDAO = new RoleDAOImpl();
			teamIList = teamIDAO.getTeamList();
			teamList = teamDAO.getTeamPlayerList();	
			tourDAO = new TournamentDAOImpl();
			tourDTO = tourDAO.getTournament(tournamentId);

			for(TeamInfoDTO teamIDTO : teamIList){
				if(teamIDTO.getUserId()==udto.getId()&&teamIDAO.getTeam(teamIDTO.getTeamId()).getSport().equals(tourDTO.getSport())){
					for(TeamDTO teamDTO: teamList){
						if(teamDTO.getTeamId()==teamIDAO.getTeam(teamIDTO.getTeamId()).getTeamId()&&roleDAO.getRole(teamDTO.getUserId(), tId).getRole()!=1){
							roleDAO.disableRole(new RoleDTO(teamDTO.getUserId(), tournamentId, 2));
							System.out.println(teamDTO.getUserId() + " leaved");
						}
					}
				}
			}


		}
		catch (DALException e) {
			ValidateInput validate = new ValidateInput();
			ErrorService error = validate.createError();
			error.setError("<div class=\"alert alert-danger\">"+e+"</div>");
			request.setAttribute("error", error);
		}

	}

	public void joinTour(int tournamentId, HttpServletRequest request, HttpServletResponse response){

		try {

			System.out.println("Join pressed");
			teamDAO = new TeamDAOImpl();
			teamIDAO = new TeamInfoDAOImpl();
			roleDAO = new RoleDAOImpl();
			teamIList = teamIDAO.getTeamList();
			teamList = teamDAO.getTeamPlayerList();
			tourDAO = new TournamentDAOImpl();
			tourDTO = tourDAO.getTournament(tournamentId);

			for(TeamInfoDTO teamIDTO : teamIList){
				if(teamIDTO.getUserId()==udto.getId()&&teamIDAO.getTeam(teamIDTO.getTeamId()).getSport().equals(tourDTO.getSport())){
					for(TeamDTO teamDTO: teamList){
						if(teamDTO.getTeamId()==teamIDAO.getTeam(teamIDTO.getTeamId()).getTeamId()){
							roleDAO.createRole(new RoleDTO(teamDTO.getUserId(), tournamentId, 2));
							System.out.println(teamDTO.getUserId() + "Joined");
						}
					}
				}
				else{
					System.out.println("Fejl bruger er ikke teamleader for et hold til turneringens sport");
				}
			}

		} catch (DALException e) {

			ValidateInput validate = new ValidateInput();
			ErrorService error = validate.createError();
			error.setError("<div class=\"alert alert-danger\">"+e+"</div>");
			request.setAttribute("error", error);
		}

	}
}


