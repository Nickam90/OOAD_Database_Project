package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ErrorService;
import service.ValidateInput;
import dao.RoleDAO;
import dao.TournamentDAO;
import dao.UserDAO;
import daoimpl.RoleDAOImpl;
import daoimpl.TournamentDAOImpl;
import daoimpl.UserDAOImpl;
import dto.RoleDTO;
import dto.TournamentDTO;
import dto.UserDTO;
import exceptions.DALException;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ValidateInput validate = new ValidateInput();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		
		ValidateInput validate = new ValidateInput();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (validate.validateCredentials(username, password)){
			
		
				UserDAO uDAO;
				try {
					uDAO = new UserDAOImpl();
					UserDTO uDTO = uDAO.getUser(uDAO.getUserId(username));
					TournamentDAO tDAO = new TournamentDAOImpl();
					RoleDAO rDAO = new RoleDAOImpl();
					
					HttpSession session = request.getSession();
					session.setAttribute("userObject", uDTO);
					session.setAttribute("userName", uDTO.getUsername());
					
					// list of tournaments where the user´s the role is organizer
					List<RoleDTO> roleListOrg = rDAO.getTournamentRoleList(
							uDAO.getUserId(username), 1);
					// list of tournaments where the user´s role is participant
					List<RoleDTO> roleListPart = rDAO.getTournamentRoleList(
							uDAO.getUserId(username), 2);

					String listPartTourn = "";
					String listOrgTourn = "";
					String listAllTourn = "";
					
					TournamentDAO tournament = new TournamentDAOImpl();
					List<TournamentDTO> tournList;
					tournList = tournament.getTournamentList();
					
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
					
					session.setAttribute("partInList", listPartTourn);
					session.setAttribute("orgOfList", listOrgTourn);
					session.setAttribute("tournList", listAllTourn);
					
					this.getServletContext().getRequestDispatcher("/WEB-INF/user/Main.jsp").forward(request, response);
					
				} catch (DALException e) {
					ErrorService error = validate.createError();
					error.setError("<div class=\"alert alert-danger\">"+e+"</div>");
					request.setAttribute("error", error);
				}
		
				
		}
		//if user doesn't exist in database send back to login
		else{
			ErrorService error = validate.createError();
			error.setError("<div class=\"alert alert-danger\">Can't find user</div>");
			request.setAttribute("error", error);
			this.getServletContext().getRequestDispatcher("/LogIn.jsp").forward(request, response);
		}
	}

}
