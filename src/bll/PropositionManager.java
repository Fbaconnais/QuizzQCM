package bll;

import bo.Proposition;
import dal.DALException;
import dal.DAOFactory;
import dal.DAOProposition;

public class PropositionManager {
private DAOProposition DAOProposition;
	
	private PropositionManager() {
		DAOProposition = DAOFactory.getDAOProposition();
	}
	
	public static PropositionManager getMger() {
		return new PropositionManager();
	}
	public void addProposition(Proposition p) throws BLLException {
		try {
			DAOProposition.add(p);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
		
	}
}
