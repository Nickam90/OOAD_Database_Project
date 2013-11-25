package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ErrorService;
import service.ValidateInput;
import dao.UserDAO;
import daoimpl.UserDAOImpl;
import dto.UserDTO;
import exceptions.DALException;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
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
		
		String username, password, email;
		boolean exist = false;
		RequestDispatcher dispatcher;
		HttpSession session = request.getSession();
		ValidateInput validate = new ValidateInput();

		if(!request.getMethod().equals("POST")){
			dispatcher = request.getRequestDispatcher("StartPage.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
			username = request.getParameter("username");
			password = request.getParameter("password");
			email = request.getParameter("email");
			
			try
			{
				UserDAO user = new UserDAOImpl();

				for(int i = 1 ; i <= user.getUserList().size(); i++){
					if(user.getUser(i).getUsername().equals(username)){
						exist = true;
						System.out.println(exist);
					}
					
				}
					
				if(!validate.validate(username, password, email)){

					ErrorService error = validate.createError(); 
					error.setError("<li>Invalid input</li>");
					request.setAttribute("error", error);
					dispatcher = request.getRequestDispatcher("SignUp.jsp");
					dispatcher.forward(request, response);

				}


				if(!exist){
					UserDTO newUser = new UserDTO(username, password, email);
					user.createUser(newUser);
					dispatcher = request.getRequestDispatcher("LogIn.jsp");
					dispatcher.forward(request, response);

				}
				else{
					ErrorService error = validate.createError(); 
					error.setError("<div class=\"alert alert-danger\">Username already taken</div>");
					request.setAttribute("error", error);
					dispatcher = request.getRequestDispatcher("SignUp.jsp");
					dispatcher.forward(request, response);
				}
			}
			catch (DALException e)
			{
				System.out.println("Fejl: "+e);
			}
		}
	
	}

}
