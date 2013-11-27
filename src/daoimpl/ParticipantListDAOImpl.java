package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ParticipantListDAO;
import database.MySQLConnector;
import dto.MatchDTO;
import dto.ParticipantListDTO;
import dto.PlacementListDTO;
import exceptions.DALException;

public class ParticipantListDAOImpl implements ParticipantListDAO {

	public MySQLConnector conn = null;


	public ParticipantListDAOImpl() throws DALException {
		conn = new MySQLConnector();
	}

	public void createParticipant(ParticipantListDTO participant)
			throws DALException {
		conn.doUpdate("INSERT INTO ParticipantList(tournement_id, team_id) VALUES"
				+ "("
				+ participant.getTournamentId()
				+ ",'"
				+ participant.getTeamId()
				+ "')");

	}

	public List<ParticipantListDTO> getParticipantList(int tournamentId)
			throws DALException {
		List<ParticipantListDTO> list = new ArrayList<ParticipantListDTO>();
		ResultSet rs = conn.doQuery("SELECT*FROM ParticipantList");
		try {
			while (rs.next()) {
				list.add(new ParticipantListDTO(rs.getInt(1), rs.getInt(2)));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	public void updateParticipant(ParticipantListDTO participantList)
			throws DALException {
		conn.doUpdate("UPDATE ParticipantList SET tournement_id = '"
				+ participantList.getTournamentId() + "', team_id = '"
				+ participantList.getTeamId() + "' WHERE tournement_id = "
				+ participantList.getTournamentId());

	}

	public void disableParticipant(ParticipantListDTO participant)
			throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public ParticipantListDTO getParticipant(int teamId, int tournamentId)
			throws DALException {
		ResultSet rs = conn.doQuery("SELECT * FROM ParticipantList WHERE team_id = "
				+ teamId + "AND tournament_id = " + tournamentId);
		try {
			if (!rs.first())
				throw new DALException("The tournament " + tournamentId + " dont exist");
			return new ParticipantListDTO(rs.getInt(1), rs.getInt(2));
		} catch (Exception e) {
			throw new DALException(e);
		}
	}



}
