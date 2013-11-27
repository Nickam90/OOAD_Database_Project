package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RoleDAO;
import dao.TeamDAO;
import dao.TeamInfoDAO;
import dao.UserDAO;
import daoimpl.RoleDAOImpl;
import daoimpl.TeamDAOImpl;
import daoimpl.TeamInfoDAOImpl;
import daoimpl.UserDAOImpl;
import dto.RoleDTO;
import dto.TeamDTO;
import dto.TeamInfoDTO;
import dto.TournamentDTO;
import dto.UserDTO;
import exceptions.DALException;

/**
 * Servlet implementation class TeamSelector
 */
@WebServlet("/TeamSelector")
public class TeamSelector extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeamSelector() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int tId = Integer.parseInt(request.getParameter("id"));
		UserDTO uDTO = (UserDTO) request.getSession().getAttribute("userObject");
		
		try {
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
			request.setAttribute("teamName", tDTO.getTeamName());
			request.setAttribute("teamLeader", teamLeaderUsername);
			request.setAttribute("teamSport", tDTO.getSport());
			
			//user is organizer
			if((tDTO.getUserId()) == uDTO.getId()){
				this.getServletContext().getRequestDispatcher("/WEB-INF/user/EditTeam.jsp").forward(request, response);			
			}
			//user is participant
			else{
				this.getServletContext().getRequestDispatcher("/WEB-INF/user/ViewTeam.jsp").forward(request, response);				
			}
		} 
		// user is neither a participant nor a organizer so he is forwarded to view tournament
		catch (DALException e) {
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/user/TeamOverview.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private void getMemberList () {
		
		
	}

}
