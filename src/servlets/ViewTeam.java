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
import daoimpl.TeamDAOImpl;
import daoimpl.TeamInfoDAOImpl;
import dto.TeamDTO;
import dto.UserDTO;
import exceptions.DALException;

/**
 * Servlet implementation class ViewTeam
 */
@WebServlet("/ViewTeam")
public class ViewTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewTeam() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			TeamDAO team = new TeamDAOImpl();
			TeamInfoDAO teamInfo = new TeamInfoDAOImpl();
			String listElementTeam = "";
			UserDTO udto = (UserDTO) request.getSession().getAttribute("userObject");

			List<TeamDTO>teamList = team.getTeamPlayerList();

			for(TeamDTO teams: teamList){
				if(teams.getUserId() == udto.getId()){
					String url = request.getContextPath() + "/TeamServlet/Team?id="+teams.getTeamId();
					listElementTeam += "<a href=\""+url+"\" class=\" list-group-item\">" + teamInfo.getTeam(teams.getTeamId()).getTeamName() + "</a>\n";
				}
			}
			request.setAttribute("Teams", listElementTeam);

		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
