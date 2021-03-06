package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TeamDAO;
import dao.TeamInfoDAO;
import dao.UserDAO;
import daoimpl.TeamDAOImpl;
import daoimpl.TeamInfoDAOImpl;
import daoimpl.UserDAOImpl;
import dto.TeamDTO;
import dto.TeamInfoDTO;
import dto.UserDTO;
import exceptions.DALException;

/**
 * Servlet implementation class TeamManager
 */
@WebServlet("/TeamManager")
public class TeamManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeamManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			
		try {
			String action = request.getParameter("action");
			int tId = Integer.parseInt(request.getParameter("id"));
			System.out.println(tId);
			TeamInfoDAO tDAO = new TeamInfoDAOImpl();
			TeamInfoDTO tDTO = tDAO.getTeam(tId);
			
			UserDAO uDAO = new UserDAOImpl();
			UserDTO teamLeader = uDAO.getUser(tDTO.getUserId());
			String teamLeaderUsername = teamLeader.getUsername();
			
			TeamDAO teamDAO = new TeamDAOImpl();
			List<TeamDTO> teamDTOList;
			teamDTOList = teamDAO.getMemberList(tId);
			
			String memberList = "";
			
			for (TeamDTO teamDTO: teamDTOList){
				memberList += "<a href= \"#\" class=\" list-group-item\">" + uDAO.getUser(teamDTO.getUserId()).getUsername() + "</a>\n";
			}
			
			request.setAttribute("memberList", memberList);
			request.setAttribute("team", tDTO);
			request.setAttribute("teamLeader", teamLeaderUsername);
			switch (action) {
			case "add member":
				
				System.out.println("missing logic");
				request.setAttribute("memberList", memberList);
				request.setAttribute("teamName", tDTO.getTeamName());
				request.setAttribute("teamLeader", teamLeaderUsername);
				request.setAttribute("teamSport", tDTO.getSport());
				this.getServletContext().getRequestDispatcher("/WEB-INF/user/EditTeam.jsp").forward(request, response);

				break;
				
			case "delete member":
				
				System.out.println("missing logic");
				request.setAttribute("memberList", memberList);
				request.setAttribute("teamName", tDTO.getTeamName());
				request.setAttribute("teamLeader", teamLeaderUsername);
				request.setAttribute("teamSport", tDTO.getSport());
				this.getServletContext().getRequestDispatcher("/WEB-INF/user/EditTeam.jsp").forward(request, response);

				break;
				
			case "edit":
				
				System.out.println("missing logic");
				request.setAttribute("memberList", memberList);
				request.setAttribute("teamName", tDTO.getTeamName());
				request.setAttribute("teamLeader", teamLeaderUsername);
				request.setAttribute("teamSport", tDTO.getSport());
				this.getServletContext().getRequestDispatcher("/WEB-INF/user/EditTeam.jsp").forward(request, response);

				break;

			case "delete":
				
				System.out.println("missing logic");
				request.setAttribute("memberList", memberList);
				request.setAttribute("teamName", tDTO.getTeamName());
				request.setAttribute("teamLeader", teamLeaderUsername);
				request.setAttribute("teamSport", tDTO.getSport());
				this.getServletContext().getRequestDispatcher("/WEB-INF/user/EditTeam.jsp").forward(request, response);

				break;

			default:
				
				System.out.println("missing logic");
				request.setAttribute("memberList", memberList);
				request.setAttribute("teamName", tDTO.getTeamName());
				request.setAttribute("teamLeader", teamLeaderUsername);
				request.setAttribute("teamSport", tDTO.getSport());
				this.getServletContext().getRequestDispatcher("/WEB-INF/user/EditTeam.jsp").forward(request, response);
				
				break;
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
