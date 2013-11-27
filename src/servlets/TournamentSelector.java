package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.RoleDAO;
import dao.TeamInfoDAO;
import dao.TournamentDAO;
import daoimpl.RoleDAOImpl;
import daoimpl.TeamInfoDAOImpl;
import daoimpl.TournamentDAOImpl;
import dto.RoleDTO;
import dto.TeamInfoDTO;
import dto.TournamentDTO;
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
		
		
		//Get bracket
		// the following string b (from the example from the homepage)
		String b = "{\"teams\":[[\"Team 1\",\"Team 2\"],[\"Team 3\",\"Team 4\"]],\"results\":[[[[1,0],[2,7]]]]}";
		//is created by the following code
		//code start
		JSONObject bracket = new JSONObject();
		JSONArray matches = new JSONArray();
		JSONArray results = new JSONArray();
		ArrayList<JSONArray> rounds = new ArrayList<JSONArray>();
		
		JSONArray matchup1 = new JSONArray();//DO this for every matchup
		matchup1.put("Team1");
		matchup1.put("Team2"); 
		matches.put(matchup1);
		
		JSONArray matchup2 = new JSONArray();//Another one
		matchup2.put("Team3");
		matchup2.put("Team4"); 
		matches.put(matchup2);
		
		JSONArray round1 = new JSONArray();//One array per round
		JSONArray result1 = new JSONArray();
		result1.put(1);
		result1.put(3);
		round1.put(result1);
		JSONArray result2 = new JSONArray();//One array per round
		result2.put(5);
		result2.put(6);
		round1.put(result2);
		//after every result is in the round
		rounds.add(round1);
		
		JSONArray round2 = new JSONArray();//One array per round
		JSONArray result3 = new JSONArray();
		result3.put(12);
		result3.put(0);
		round2.put(result3);
		rounds.add(round2);
		
		//Prepare the JSONObject
		for (JSONArray round : rounds)//Save all rounds
			results.put(round);
		
		bracket.put("teams", matches);
		bracket.put("results", results);
		System.out.println(bracket.toString());
		request.setAttribute("bracketData", bracket.toString());
		//code end
		
		
		try {
			TournamentDAO tDAO = new TournamentDAOImpl();
			TournamentDTO tDTO = tDAO.getTournament(tId);
			request.setAttribute("Tournament", tDTO);
		}
		catch (DALException e) {
			String error = e.getMessage();
			System.out.println(error);
			request.setAttribute("error", error);
			this.getServletContext().getRequestDispatcher("/errorpage.jsp").forward(request, response);
			return;
		}
		
		
		try {
			RoleDAO rDAO = new RoleDAOImpl();
			RoleDTO rDTO = rDAO.getRole(uDTO.getId(), tId);
			
			//user is organizer
			if((rDTO.getRole()) == 1){
				request.setAttribute("organisor", true);
				TournamentDAO tDAO = new TournamentDAOImpl();
				TournamentDTO tDTO = tDAO.getTournament(tId);
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
				
				
			}
			//user is participant
			else{
				request.setAttribute("participant", true);
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
