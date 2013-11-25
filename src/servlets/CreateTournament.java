package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ErrorService;
import service.ValidateInput;
import service.ValidateTournament;

import dao.RoleDAO;
import dao.TournamentDAO;
import daoimpl.RoleDAOImpl;
import daoimpl.TournamentDAOImpl;
import dto.RoleDTO;
import dto.TournamentDTO;
import dto.UserDTO;
import exceptions.DALException;

/**
 * Servlet implementation class CreateTournament
 */
@WebServlet("/CreateTournament")
public class CreateTournament extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String name;
	private String sport;
	private String pcount;
	private int size;
	private String info;
	private String dato;
	private String type;
	private int typeInt;
	private int format;
	private String formatText;
	private TournamentDAO tour;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateTournament() {
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

		UserDTO uDTO =(UserDTO)request.getSession().getAttribute("userObject");
		boolean exist = false;

		try {
			name = request.getParameter("tournament_name");
			sport = request.getParameter("Sport");
			formatText = request.getParameter("Tournament Type");

			if (formatText.equals("type1")) {
				format = 1; // Hvis format = 1 er turneringen open
			} else {
				format = 2; // lukket tur. format
			}
			pcount = request.getParameter("size");
			dato = request.getParameter("Starting Date");
			info = request.getParameter("info");
			type = request.getParameter("Tournament Format");

			if (type.equals("format1")) {
				typeInt = 0; // 0 for single elim. turnering.
			} else {
				typeInt = 1; // 1 for double elim. turnering.
			}

			ValidateTournament vt = new ValidateTournament();
			if (vt.validate(name, pcount)) {
				size = Integer.parseInt(pcount);
				tour = new TournamentDAOImpl();

				for (int index = 1; index < tour.getTournamentList().size(); index++) {
					if (tour.getTournament(index).getTournamentName()
							.equals(name)) {
						exist = true;
					}
				}
				if (!exist) {
					tour.createTournament(new TournamentDTO(name, sport, size,
							0, dato, info, typeInt, format));
					RoleDAO role = new RoleDAOImpl();
					role.createRole(new RoleDTO(uDTO.getId(), tour.getTournamentId(name), 1));

				} else {
					ValidateInput validate = new ValidateInput();
					ErrorService error = validate.createError();
					error.setError("<li>Tournament already taken</li>");
					request.setAttribute("error", error);
				}

			}
		} catch (DALException e) {
			ValidateInput validate = new ValidateInput();
			ErrorService error = validate.createError();
			error.setError("<li>Error</li>" + e);
			request.setAttribute("error", error);

		} catch (NullPointerException e) {
			ValidateInput validate = new ValidateInput();
			ErrorService error = validate.createError();
			error.setError("<li>Error</li>" + e);
			request.setAttribute("error", error);
		}

		request.getRequestDispatcher("/WEB-INF/user/ViewTournament.jsp").forward(request,response);
	}
}



