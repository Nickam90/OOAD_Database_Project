package test;

import service.ValidateInput;
import dao.UserDAO;
import daoimpl.UserDAOImpl;
import exceptions.DALException;

public class test2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int id;
			try {
				UserDAO u = new UserDAOImpl();
				System.out.println(id = u.getUserId("s123074"));
				System.out.println(u.getUser(id).getPassword());
				ValidateInput v = new ValidateInput();
				
				
				if(v.validateCredentials("s123074", "s123074"))
				{
					System.out.println("ok");
				}
				
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
	}

}
