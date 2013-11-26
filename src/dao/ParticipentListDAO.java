package dao;

import java.util.List;

import dto.ParticipentListDTO;

import exceptions.DALException;

public interface ParticipentListDAO {
	
	void createParticipent(ParticipentListDTO participent) throws DALException;
	List<ParticipentListDTO> getParticipentList(int particpentId) throws DALException;
	void updateParticipent(ParticipentListDTO participentList) throws DALException;
	void disableParticipent(ParticipentListDTO participent) throws DALException;

}
