package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;

import service.ErrorService;
import service.ValidateInput;

import dao.RoleDAO;
import dao.TeamDAO;
import dao.TeamInfoDAO;
import dao.TournamentDAO;
import daoimpl.RoleDAOImpl;
import daoimpl.TeamDAOImpl;
import daoimpl.TeamInfoDAOImpl;
import daoimpl.TournamentDAOImpl;
import dto.RoleDTO;
import dto.TeamDTO;
import dto.TeamInfoDTO;
import dto.TournamentDTO;
import dto.UserDTO;
import exceptions.DALException;

/**
 * Servlet implementation class EditTournament
 */
@WebServlet("/EditTournament")
public class EditTournament extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TournamentDAO tourDAO;
	private TournamentDTO tourDTO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditTournament() {
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
		TournamentDTO tDTO = (TournamentDTO) request.getSession().getAttribute("Tournament");
		int tId = tDTO.getId();

		if(tDTO.getStatus()==0){

			if (request.getParameter("startButton") != null) {
				System.out.println("Starting Tour");
				StartTour(tId,request,response);
			}
			else if(request.getParameter("editButton") != null){
				editTour(tId,request,response);
			}
			else if(request.getParameter("resButton") != null){
				resTour(tId,request,response);
			}
			else if(request.getParameter("delButton") != null){
				deleteTour(tId,request,response);
			}
		}
		else{
			ValidateInput validate = new ValidateInput();
			ErrorService error = validate.createError();
			error.setError("<div class=\"alert alert-danger\">Cant start or edit a tournament in progress</div>");
			request.setAttribute("error", error);
			this.getServletContext().getRequestDispatcher("/LogIn.jsp").forward(request, response);
		}

	}

	private void deleteTour(int tId,HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	private void resTour(int tId,HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	private void editTour(int tId,HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	private void StartTour(int tId,HttpServletRequest request, HttpServletResponse response) {

		try {
			tourDAO = new TournamentDAOImpl();
			tourDTO = tourDAO.getTournament(tId);
			tourDAO.updateTournament(new TournamentDTO(tourDTO.getTournamentName(), tourDTO.getSport(), tourDTO.getMaxParticipants(), 2, tourDTO.getStartDate(), tourDTO.getInfo(), tourDTO.getTournament_type(), tourDTO.getTournament_format()));
		} catch (DALException e) {
			ValidateInput validate = new ValidateInput();
			ErrorService error = validate.createError();
			error.setError("<div class=\"alert alert-danger\">"+e+"</div>");
			request.setAttribute("error", error);
		}
	}

}
