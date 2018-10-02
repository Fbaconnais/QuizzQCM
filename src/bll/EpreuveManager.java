package bll;

import bo.Epreuve;
import dal.DALException;
import dal.DAOEpreuve;
import dal.DAOFactory;

public class EpreuveManager {

	private DAOEpreuve DAO;
	
	private EpreuveManager() {
		DAO = DAOFactory.getDAOEpreuve();
	}
	
	public static EpreuveManager getMger() {
		return new EpreuveManager();
	}
	
	public Epreuve addEpreuve(Epreuve epreuve) throws BLLException {
		try {
			epreuve = DAO.add(epreuve);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
		return epreuve;
	}
	
	public Epreuve selectEpreuve(int id) throws BLLException {
		Epreuve epreuve = null;
		
		try {
			epreuve = DAO.selectOne(id);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
		
		return epreuve;
	}
	
	public void updateEpreuve(Epreuve epreuve) throws BLLException {
		try {
			DAO.update(epreuve);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
	}
	
	
}
