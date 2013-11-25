package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ErrorService;
import service.ValidateInput;
import dao.TeamDAO;
import daoimpl.TeamDAOImpl;
import daoimpl.TeamInfoDAOImpl;
import dto.TeamDTO;
import dto.TeamInfoDTO;
import dto.UserDTO;
import exceptions.DALException;

/**
 * Servlet implementation class CreateTeam
 */
@WebServlet("/CreateTeam")
public class CreateTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String name;
	private String sport;
	private TeamInfoDAOImpl team;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateTeam() {
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
		
		UserDTO udto = (UserDTO) request.getSession().getAttribute("userObject");

		boolean dontExist = true;

		try {
			name = request.getParameter("tournament_name");
			sport = request.getParameter("Sport");


			team = new TeamInfoDAOImpl();
			for(int index = 1; index<team.getTeamList().size();index++){
				if(team.getTeam(index).getTeamName().equals(name)){
					dontExist = false;
				}
			}
			if(dontExist){
				team.createTeam(new TeamInfoDTO(name, sport, udto.getId()));
				TeamDAO player = new TeamDAOImpl();
				player.createTeamPlayer(new TeamDTO(team.getTeamId(name).getTeamId(),udto.getId()));
			}
			else{
				ValidateInput validate = new ValidateInput();
				ErrorService error = validate.createError(); 
				error.setError("<li>Name already taken</li>");
				request.setAttribute("error", error);
			}

		}catch (DALException e) {
			ValidateInput validate = new ValidateInput();
			ErrorService error = validate.createError(); 
			error.setError("<li>Error</li>" + e);
			request.setAttribute("error", error);

		}catch(NullPointerException e){
			ValidateInput validate = new ValidateInput();
			ErrorService error = validate.createError(); 
			error.setError("<li>Error</li>" + e);
			request.setAttribute("error", error);
		}
		request.getRequestDispatcher("/WEB-INF/user/TeamOverview.jsp").forward(request,response);

	}
}

