package dao;

import java.util.List;
import dto.PlacementListDTO;
import exceptions.DALException;

public interface PlacementListDAO {
	
	void createPlacementList(PlacementListDTO placementList) throws DALException;
	List<PlacementListDTO> getPlacementList(int placementListId) throws DALException;
	void updatePlacementList(PlacementListDTO placementList) throws DALException;
	void disablePlacementList(PlacementListDTO placementList) throws DALException;

}
