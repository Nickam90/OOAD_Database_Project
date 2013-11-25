package service;
import service.ErrorService;
import service.Password;


import dao.UserDAO;
import daoimpl.UserDAOImpl;
import database.MySQLConnector;
import exceptions.DALException;


public class ValidateInput 
{
	private String error = "";

	public boolean validate(String username, String password, String email)
	{
		error = "";
		
		if (username != null)
			validateUsername(username);

		if (password != null) 
			validatePassword(password);
		
		if(email != null)
			validateEmail(email);

		//Tjekker om validerignen har fejlet
		System.out.println(error);
		return (error.isEmpty());
	}
	
		//####################### ID VALIDERING #######################
		
	
	private void validateUsername(String username) {
		
		if(! username.matches("^[A-Za-z0-9 ]+$") || username.length() < 2)
		{
			if(username.isEmpty()){
				error = error + "<div class=\"alert alert-danger\">Please type in Username</div>";
		}
			else 
			{
				error = error + "<div class=\"alert alert-danger\">Username must contain a minimum of 2 characters</div>";
			}
		}
	}

	private void validatePassword(String password) {
		
	
		if(password.isEmpty())
		{
			error = error + "<div class=\"alert alert-danger\">Please type your password</div>";
		}
		else if (password.length() < 7 || password.length() > 10 )
		{
			error = error + "<div class=\"alert alert-danger\">Password must contain 7-10 chars</div>";
		}
		
		
	}
	
	public void validateEmail(String email) {
//		System.out.println(email); //debug/
		if(!email.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
		{
			if(email.isEmpty())
			{
//				System.out.println("ikke val"); /debug
				error = error + "<div class=\"alert alert-danger\">Please type your Email</div>";
			}
			else
			{
//				System.out.println("ikke val"); /debug
				error = error + "<div class=\"alert alert-danger\">Email not valid!</div>";
			}
		}
	}

	public ErrorService createError(){
		ErrorService error = new ErrorService();
		return error; 
	}
	
	public void setError(String error)
	{
		this.error = error;
	}
	
	//######################### GETTERS ##########################
	public String getError(){
		return error;
	}

	public boolean validateCredentials(String username, String pass)
	{	
		try { new MySQLConnector(); } 
		catch (DALException e) {e.printStackTrace();}
		
		try
		{

		UserDAO udto = new UserDAOImpl();
		int id = udto.getUserId(username);
			return udto.getUser(id).getPassword().equals(pass) ? true:false;
		}
		catch (DALException e)
		{
			System.out.println("DALException: "+e);
			return false;
		}
		catch (Exception e)
		{
			System.out.println("Exception: "+e);
			return false;
		}
	}

	//######################### SETTERS ##########################


}
