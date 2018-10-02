package bll;

import java.util.List;

import bo.QuestionTirage;
import dal.DALException;
import dal.DAOFactory;
import dal.DAOQuestionTirage;

public class QuestionTirageManager {
	private DAOQuestionTirage DAO;

	private QuestionTirageManager() {
		DAO = DAOFactory.getDAOQuestionTirage();
	}

	public static QuestionTirageManager getMger() {
		return new QuestionTirageManager();
	}
	
	public void genererTest(int idEpreuve) throws BLLException{
		try {
			DAO.generationTest(idEpreuve);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
	}
	public List<QuestionTirage> getQuestionsViaIdEpreuve(int idEpreuve)throws BLLException{
		List<QuestionTirage> liste = null;
		
		try {
			liste = DAO.getQuestionTirageDansLOrdre(idEpreuve);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
		
		return liste;
	}
	
}
