package dao;

import java.util.List;

import dto.BracketsDTO;
import exceptions.DALException;

public interface BracketsDAO {
	
	void createBracket(BracketsDTO bracket) throws DALException;
	BracketsDTO getBracket(int tournamentId) throws DALException;
	List<BracketsDTO> getBracketList() throws DALException;
	void updateBracket(BracketsDTO bracket) throws DALException;
	void disableBracket(BracketsDTO bracket) throws DALException;

}
