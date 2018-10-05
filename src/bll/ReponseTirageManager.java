package bll;

import javax.ws.rs.PathParam;

import dal.DALException;
import dal.DAOFactory;
import dal.DAOReponseTirage;

public class ReponseTirageManager {
	private DAOReponseTirage DAOReponseTirage;

	private ReponseTirageManager() {
		DAOReponseTirage = DAOFactory.getDAOReponseTirage();

	}
	
	public static ReponseTirageManager getMger() {
		
		return new ReponseTirageManager();
	}

	public void gérerReponseTirage(int idProposition, int idQuestion, int idEpreuve) throws BLLException {
		try {
			DAOReponseTirage.gererReponseTirage(idProposition, idQuestion, idEpreuve);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
	}
	
	public boolean verifReponse(int idProposition, int idQuestion, int idEpreuve) throws BLLException {
		boolean estCoche;
		try {
			estCoche = DAOReponseTirage.verifReponse(idProposition, idQuestion, idEpreuve);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
		return estCoche;
	}
}