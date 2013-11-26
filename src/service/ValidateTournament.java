package service;
import service.ErrorService;

public class ValidateTournament {
		
	String error = "";
	
	public boolean validate(String name, String size){
		
		validateName(name);
		validateSize(size);
		
		System.out.println(error);
		return (error.isEmpty());
	}

	private void validateSize(String size) {
		if(!size.matches("[0-9]+") || Integer.parseInt(size) > 128 || (Integer.parseInt(size)%2 != 0)){
			{
				if(size.isEmpty()){
					error = error + "Please type in the size for the tournament.";
				}
				else
				{
					error = error + "The size must be a even number between 0-128.";
				}
			}
	}
	}

	private void validateName(String name) {
		if(name.length() < 2 || name.length() > 30){
			if(name.isEmpty()){
				error = error + "Please type in a name.";
			}
			else{
				error = error + "Name must be between 2 and 30 characters long.";
			}
		}
	}

}
