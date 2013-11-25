package service;

import java.util.Random;

/**
 * Genererer et tilf��ldigt password
 * 
 * @author Gruppe 15
 *
 */

public class Password {


	public boolean isPassLegal(String password){
		if(password.matches("^(?!.*(.)\1{3})((?=.*[^\\w\\d\\s])(?=.*)).{7,8}$")){
			return true;
		}
		return false;
	}
	
	
	public String generatePassword()
	{
		String password = "";
		Random r = new Random();
		int[] symbols = {43,33,63,61,95};
		
		for(int i = 0 ; i < 8 ; i++)
		{
			if(i <= 3)
			{
				password+= r.nextInt(9);
			}
			else
				if(i <= 4)
				{
					password += (char)(r.nextInt(26) + 'a');
				}
				else
					if(i<= 5)
					{
						password += (char)(r.nextInt(26) + 'A');
					}
					else
						if(i<=6)
						{
							password += (char)(symbols[r.nextInt(4)]);
						}
		}
		
		if(isPassLegal(password))
			return password;
		else
			return "Password not generated";
	}
}
	
