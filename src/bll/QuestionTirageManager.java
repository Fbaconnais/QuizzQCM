package bll;

import java.util.List;

import bo.QuestionTirage;
import bo.SectionTest;
import dal.DALException;
import dal.DAOEpreuve;
import dal.DAOFactory;
import dal.DAOQuestionTirage;
import dal.DAOSectionTest;

public class QuestionTirageManager {
	private DAOQuestionTirage DAOQuestionTirage;
	private DAOSectionTest DAOSectionTest;
	private DAOEpreuve DAOEpreuve;

	private QuestionTirageManager() {
		DAOQuestionTirage = DAOFactory.getDAOQuestionTirage();
		DAOSectionTest = DAOFactory.getDAOSectionTest();
		DAOEpreuve = DAOFactory.getDAOEpreuve();
	}

	public static QuestionTirageManager getMger() {
		return new QuestionTirageManager();
	}

	public void genererTest(int idEpreuve) throws BLLException {
		int idTest;
		try {
			idTest = DAOEpreuve.getIdTestViaIdEpreuve(idEpreuve);
		} catch (DALException e1) {
			throw new BLLException(e1.getMessage(), e1);
		}

		try {
			DAOQuestionTirage.generationTest(idTest, idEpreuve);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
	}

	public List<QuestionTirage> getQuestionsViaIdEpreuve(int idEpreuve) throws BLLException {
		List<QuestionTirage> liste = null;

		try {
			liste = DAOQuestionTirage.getQuestionTirageDansLOrdre(idEpreuve);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}

		return liste;
	}

}
