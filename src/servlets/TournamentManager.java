package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TournamentManager
 */
@WebServlet("/TournamentManager")
public class TournamentManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		String action = request.getParameter("action");
		switch (action) {
		case "add team":
			
			System.out.println("missing logic");
			this.getServletContext().getRequestDispatcher("/WEB-INF/user/EditTeam.jsp").forward(request, response);

			break;
			
		case "delete team":
			
			System.out.println("missing logic");
			this.getServletContext().getRequestDispatcher("/WEB-INF/user/EditTeam.jsp").forward(request, response);

			break;
			
		case "edit":
			
			System.out.println("missing logic");
			this.getServletContext().getRequestDispatcher("/WEB-INF/user/EditTeam.jsp").forward(request, response);

			break;

		case "delete":
			
			System.out.println("missing logic");
			this.getServletContext().getRequestDispatcher("/WEB-INF/user/EditTeam.jsp").forward(request, response);

			break;

		default:
			
			System.out.println("missing logic");
			this.getServletContext().getRequestDispatcher("/WEB-INF/user/EditTeam.jsp").forward(request, response);
			
			break;
		}
	}

}
