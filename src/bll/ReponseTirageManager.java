package bll;

import java.util.List;

import bo.ReponseTirage;
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
	
	public List<ReponseTirage> selectAllByIDQuestionIDEpreuve(int idQuestion, int idEpreuve) throws BLLException {
		List<ReponseTirage> listeReponses = null;
		try {
			listeReponses = DAOReponseTirage.selectAllByIDQuestionIDEpreuve(idQuestion, idEpreuve);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
		return listeReponses;
	}
}