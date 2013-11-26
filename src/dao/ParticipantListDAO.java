package dao;

import java.util.List;

import dto.ParticipantListDTO;
import dto.RoleDTO;

import exceptions.DALException;

public interface ParticipantListDAO {
	
	void createParticipant(ParticipantListDTO participant) throws DALException;
	List<ParticipantListDTO> getParticipantList(int tournamentId) throws DALException;
	void updateParticipant(ParticipantListDTO participantList) throws DALException;
	void disableParticipant(ParticipantListDTO participant) throws DALException;
	ParticipantListDTO getParticipant(int teamId, int tournamentId) throws DALException;

}
