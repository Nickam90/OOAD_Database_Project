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
import exceptions.DALException;

import dao.TournamentDAO;
import daoimpl.TournamentDAOImpl;
import dto.TournamentDTO;
import exceptions.DALException;


/**
 * Servlet implementation class TournamentManager
 */
@WebServlet("/TournamentManager")
public class TournamentManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TournamentDAO tourDAO;
	private TournamentDTO tourDTO;
	private String teamName;
	private TeamInfoDAO teamIDAO;
	private TeamDAO teamDAO;
	private RoleDAO roleDAO;
	private int teamId;
	private List<TeamDTO> teamMemberList;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TournamentManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TournamentDTO tDTO = (TournamentDTO) request.getSession().getAttribute("Tournament");
		int tId = tDTO.getId();
		String action = request.getParameter("action");

		try {
			teamIDAO = new TeamInfoDAOImpl();
			teamDAO = new TeamDAOImpl();
			roleDAO = new RoleDAOImpl();
		}

		catch(DALException e){

		}

		int id = Integer.parseInt(request.getParameter("id"));


		System.out.println("Action = "+action);

		switch (action) {

		case "StartFinish":


			try {
				tourDAO = new TournamentDAOImpl();
				tourDTO = tourDAO.getTournament(tId);
				if(tourDAO.getTournament(tId).getStatus()==0){
					tourDAO.updateTournament(new TournamentDTO(tourDTO.getTournamentName(), tourDTO.getSport(), tourDTO.getMaxParticipants(), 1, tourDTO.getStartDate(), tourDTO.getInfo(), tourDTO.getTournament_type(), tourDTO.getTournament_format()));
				}
				else if(tourDAO.getTournament(tId).getStatus()==1){
					tourDAO.updateTournament(new TournamentDTO(tourDTO.getTournamentName(), tourDTO.getSport(), tourDTO.getMaxParticipants(), 2, tourDTO.getStartDate(), tourDTO.getInfo(), tourDTO.getTournament_type(), tourDTO.getTournament_format()));
				}
				else{
					System.out.println("Turnering allerede afsluttet");
				}
			} catch (DALException e) {
				ValidateInput validate = new ValidateInput();
				ErrorService error = validate.createError();
				error.setError("<div class=\"alert alert-danger\">"+e+"</div>");
				request.setAttribute("error", error);
			}

			System.out.println("missing logic");
			response.sendRedirect(request.getContextPath() + "/TournamentSelector?id=" + tId);

			this.getServletContext().getRequestDispatcher("/WEB-INF/user/EditTournament.jsp").forward(request, response);


			break;

		case "add team":

			teamName = request.getParameter("TeamName");
			int teamId;

			try {
				teamId = teamIDAO.getTeamId(teamName);

				List<TeamDTO> teamMemberList = teamDAO.getTeamList(teamId);

				for(TeamDTO teamMember: teamMemberList){
					roleDAO.createRole(new RoleDTO(teamMember.getUserId(), tId, 1));
				}
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

			response.sendRedirect(request.getContextPath() + "/TournamentSelector?id=" + tId);

			break;

		case "delete team":


			teamName = request.getParameter("TeamName");
			try{
				teamId = teamIDAO.getTeamId(teamName);	
				teamMemberList = teamDAO.getTeamList(teamId);

				for(TeamDTO teamMember: teamMemberList){
					roleDAO.disableRole(new RoleDTO(teamMember.getUserId(), tId, 1));

				}
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

			response.sendRedirect(request.getContextPath() + "/TournamentSelector?id=" + tId);


			System.out.println("missing logic");
			this.getServletContext().getRequestDispatcher("/WEB-INF/user/EditTournament.jsp").forward(request, response);

			break;

		case "edit":


			System.out.println("Logik mangler");
			response.sendRedirect(request.getContextPath() + "/TournamentSelector?id=" + tId);



			System.out.println("missing logic");
			this.getServletContext().getRequestDispatcher("/WEB-INF/user/EditTournament.jsp").forward(request, response);

			break;

		case "delete":


			try {
				tourDAO.disableTournament(tDTO);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/user/TournamentOverview.jsp").forward(request, response);


			System.out.println("missing logic");
			this.getServletContext().getRequestDispatcher("/WEB-INF/user/Main.jsp").forward(request, response);
			break;


		default:

			System.out.println("missing logic");
			response.sendRedirect(request.getContextPath() + "/TournamentSelector?id=" + tId);

			this.getServletContext().getRequestDispatcher("/WEB-INF/user/EditTournament.jsp").forward(request, response);			

			break;
		}

	}

}
