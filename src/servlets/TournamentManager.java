package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		int id = Integer.parseInt(request.getParameter("id"));
		TournamentDAO tDAO;
		TournamentDTO tDTO;
		try {
			tDAO = new TournamentDAOImpl();
			tDTO = tDAO.getTournament(id);
			request.setAttribute("Tournament", tDTO);


		System.out.println("Action = "+action);
		switch (action) {
		case "add team":
			
			System.out.println("missing logic");
			this.getServletContext().getRequestDispatcher("/WEB-INF/user/EditTournament.jsp").forward(request, response);

			break;
			
		case "delete team":
			
			System.out.println("missing logic");
			this.getServletContext().getRequestDispatcher("/WEB-INF/user/EditTournament.jsp").forward(request, response);
			break;
			
		case "edit":
			
			System.out.println("missing logic");
			this.getServletContext().getRequestDispatcher("/WEB-INF/user/EditTournament.jsp").forward(request, response);
			break;

		case "delete":
			
			System.out.println("missing logic");
			this.getServletContext().getRequestDispatcher("/WEB-INF/user/Main.jsp").forward(request, response);
			break;
			
		case "toggle tournament":
			
			System.out.println("missing logic");

			String buttontext = "Finish tournament";
			if (tDTO.getStatus() == 0){
				buttontext = "Start tournament";
			}
			if (tDTO.getStatus() != 2){
				String buttonHtml = "<form method=\"POST\" action=\"TournamentManager\" >"+
				"<input type=\"hidden\" name=\"action\" value=\"toggle tournament\">"+
				"<input type=\"hidden\" class=\"form-control\" name=\"id\" value=\""+ tDTO.getId()+"\">"+
				"<button type=\"submit\" class=\"btn btn-primary\" >"+buttontext+"</button>"+
				"</form>";
				request.setAttribute("toggleButton", buttonHtml);
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/user/EditTournament.jsp").forward(request, response);
			break;

		default:
			
			System.out.println("missing logic");
			this.getServletContext().getRequestDispatcher("/WEB-INF/user/EditTournament.jsp").forward(request, response);			
			break;
		}
		} catch (DALException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
