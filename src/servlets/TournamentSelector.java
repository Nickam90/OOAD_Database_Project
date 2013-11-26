package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RoleDAO;
import daoimpl.RoleDAOImpl;
import dto.RoleDTO;
import dto.UserDTO;
import exceptions.DALException;

/**
 * Servlet implementation class TournamentSelector
 */
@WebServlet("/TournamentSelector")
public class TournamentSelector extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TournamentSelector() {
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
			RoleDAO rDAO = new RoleDAOImpl();
			RoleDTO rDTO = rDAO.getRole(uDTO.getId(), tId);
			
			request.setAttribute("tournamentID", tId);
			
			//user is organizer
			if((rDTO.getRole()) == 1){
				this.getServletContext().getRequestDispatcher("/WEB-INF/user/EditTournament.jsp").forward(request, response);			
			}
			//user is participant
			else{
				this.getServletContext().getRequestDispatcher("/WEB-INF/user/ViewTournament.jsp").forward(request, response);				
			}
		} 
		// user is neither a participant nor a organizer so he is forwarded to view tournament
		catch (DALException e) {
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/user/ViewTournament.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
