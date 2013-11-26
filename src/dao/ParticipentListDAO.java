package dao;

import java.util.List;

import dto.ParticipentDTO;

import exceptions.DALException;

public interface ParticipentListDAO {
	
	void createParticipent(ParticipentDTO participent) throws DALException;
	List<ParticipentDTO> getParticipent(int particpentId) throws DALException;
	void updateParticipent(ParticipentDTO participentList) throws DALException;
	void disableParticipent(ParticipentDTO participent) throws DALException;

}
