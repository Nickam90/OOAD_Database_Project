package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class Navigation
 */
@WebServlet("/Navigation")
public class Navigation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Navigation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{

			String page = request.getParameter("page");
			UserDTO udto = (UserDTO) request.getSession().getAttribute("userObject");
			RoleDAO rDAO = new RoleDAOImpl();
			TournamentDAO tDAO = new TournamentDAOImpl();
			TeamDAO team = new TeamDAOImpl();
			TeamInfoDAO teamInfo = new TeamInfoDAOImpl();
			
			


			// list of tournaments where the user´s the role is organizer
			List<RoleDTO> roleListOrg = rDAO.getTournamentRoleList(
					udto.getId(), 1);
			// list of tournaments where the user´s role is participant
			List<RoleDTO> roleListPart = rDAO.getTournamentRoleList(
					udto.getId(), 2);

			String listPartTourn = "";
			String listOrgTourn = "";
			String listAllTourn = "";
			String listTeams = "";

			TournamentDAO tournament = new TournamentDAOImpl();
			List<TournamentDTO> tournList;
			tournList = tournament.getTournamentList();
			List<TeamDTO>teamList = team.getTeamPlayerList();

			for (TournamentDTO tourney: tournList){
				listAllTourn += "<a href=\"TournamentSelector?id="+tourney.getId()+"\" class=\" list-group-item\">" +
						tourney.getTournamentName() + "</a>\n";
			}

			for (RoleDTO roles : roleListOrg) {
				TournamentDTO tDTO = tDAO.getTournament(roles
						.getTournamentId());
				listOrgTourn += "<a href=\"TournamentSelector?id="+tDTO.getId()+"\" class=\" list-group-item\">"
						+ tDTO.getTournamentName() + "</a>\n";
			}
			request.setAttribute("OrganizerOfList", listOrgTourn);

			for (RoleDTO roles : roleListPart) {
				TournamentDTO tourn = tDAO.getTournament(roles
						.getTournamentId());
				listPartTourn += "<a href=\"TournamentSelector?id="+tourn.getId()+"\" class=\" list-group-item\">"
						+ tourn.getTournamentName() + "</a>\n";
			}
			
			for(TeamDTO teams: teamList){
				if(teams.getUserId() == udto.getId()){
					listTeams += "<a href=\"TeamSelector?id="+teams.getTeamId()+"\" class=\" list-group-item\">" + teamInfo.getTeam(teams.getTeamId()).getTeamName() + "</a>\n";
				}
			}

			request.getSession().setAttribute("partInList", listPartTourn);
			request.getSession().setAttribute("orgOfList", listOrgTourn);
			request.getSession().setAttribute("tournList", listAllTourn);
			request.getSession().setAttribute("teamList", listTeams);

			if (udto != null){
				String destination = "/WEB-INF/user/"+page+".jsp";
				RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
				rd.forward(request, response);
			}
			else{
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
			}

		}
		catch(DALException e){

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
