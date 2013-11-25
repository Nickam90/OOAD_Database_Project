package servlets;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class ViewTournament
 */
@WebServlet("/ViewTournament")
public class ViewTournament extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewTournament() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TournamentDAO tournament;
		try {
			tournament = new TournamentDAOImpl();
			List<TournamentDTO> tournList = tournament.getTournamentList();
			String listElement = "";
			for (TournamentDTO tourn : tournList) {
				String url = request.getContextPath()
						+ "/TournamentServlet/Tournament?id=" + tourn.getId();
				listElement += "<a href=\"" + url
						+ "\" class=\" list-group-item\">"
						+ tourn.getTournamentName() + "</a>\n";
			}
			request.setAttribute("TournList", listElement);

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
