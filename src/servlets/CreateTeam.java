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
import dao.TeamDAO;
import dao.TeamInfoDAO;
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
	private TeamInfoDAO teamInfo;
	private TeamDAO team;

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

		ValidateInput validate = new ValidateInput();

		boolean exist = false;
		boolean teamLeader = false;
		boolean teamPlayerS = false;

		try {
			name = request.getParameter("team_name");
			sport = request.getParameter("Sport");

			team = new TeamDAOImpl();
			teamInfo = new TeamInfoDAOImpl();
			List<TeamDTO> teamList = team.getTeamPlayerList();

			for(int index = 1; index<teamInfo.getTeamList().size();index++){ //Tjekker om holdets navn allerede er i brug
				if(teamInfo.getTeam(index).getTeamName().equals(name)){
					exist = true;
				}
				TeamInfoDTO teamInfoDTO = teamInfo.getTeam(index);
				String teamSport = teamInfoDTO.getSport();
				if(udto.getId() == teamInfoDTO.getUserId()&&teamSport.equals(sport)){ //Tjekker efter om spilleren allerede er teamleader af er hold med samme sport.
					teamLeader = true;
				}
			}

			for(TeamDTO teamPlayer: teamList){ //Tjekker om brugeren allerede er tilmeldt et hold med samme sport
				if(teamPlayer.getUserId()==udto.getId()){
					if(teamInfo.getTeam(teamPlayer.getTeamId()).getSport().equals(sport)){
						teamPlayerS = true;
					}
				}
			}


			if(teamPlayerS){
				ErrorService error = validate.createError(); 
				error.setError("<div class=\"alert alert-danger\">You are already on a team of the chosen sport</div>");
				request.setAttribute("error", error);
				request.getRequestDispatcher("/WEB-INF/user/CreateTeam.jsp").forward(request,response);
			}
			else if(teamLeader){
				ErrorService error = validate.createError(); 
				error.setError("<div class=\"alert alert-danger\">Your already team leder of team in this sport</div>");
				request.setAttribute("error", error);
				request.getRequestDispatcher("/WEB-INF/user/CreateTeam.jsp").forward(request,response);
			}
			else if(!exist&&!name.isEmpty()&&!sport.isEmpty()){
				teamInfo.createTeam(new TeamInfoDTO(name, sport, udto.getId()));
				TeamDAO player = new TeamDAOImpl();
				player.createTeamPlayer(new TeamDTO(teamInfo.getTeamId(name),udto.getId()));
				request.getRequestDispatcher("/WEB-INF/user/ViewTeam.jsp").forward(request,response);
			}
			else if(exist){
				ErrorService error = validate.createError(); 
				error.setError("<div class=\"alert alert-danger\">Team name already in use</div>");
				request.setAttribute("error", error);
				request.getRequestDispatcher("/WEB-INF/user/CreateTeam.jsp").forward(request,response);
			}
			
			else{
				ErrorService error = validate.createError(); 
				error.setError("<div class=\"alert alert-danger\">Missing Values</div>");
				request.setAttribute("error", error);
				request.getRequestDispatcher("/WEB-INF/user/CreateTeam.jsp").forward(request,response);
			}

		}catch (DALException e) {
			ErrorService error = validate.createError(); 
			error.setError("<div class=\"alert alert-danger\">"+e+"</div>");
			request.setAttribute("error", error);
			request.getRequestDispatcher("/WEB-INF/user/CreateTeam.jsp").forward(request,response);

		}catch(NullPointerException e){
			ErrorService error = validate.createError(); 
			error.setError("<div class=\"alert alert-danger\">Missing Values</div>");
			request.setAttribute("error", error);
			request.getRequestDispatcher("/WEB-INF/user/CreateTeam.jsp").forward(request,response);
		}


	}
}

