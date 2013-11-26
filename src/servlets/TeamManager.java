package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TeamDAO;
import daoimpl.TeamDAOImpl;
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
			String action = request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println(id);
		try {
			TeamDAO tDAO = new TeamDAOImpl();
			switch (action) {
			case "add member":

				break;
			case "edit":

				break;

			case "delete":

				break;

			default:
				break;
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}