package bll;

import java.util.List;

import bo.Proposition;
import bo.Question;
import dal.DALException;
import dal.DAOFactory;
import dal.DAOQuestion;

public class QuestionManager {
	private DAOQuestion DAOQuestion;
	
	private QuestionManager() {
		DAOQuestion = DAOFactory.getDAOQuestion();
	}
	
	public static QuestionManager getMger() {
		return new QuestionManager();
	}
	
	public Question selectQuestion(int id) throws BLLException {
		Question question = null;
		try {
			question = DAOQuestion.selectOne(id);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
		
		return question;
	}
	
	public List<Proposition> getPropositionsByIDQuestion(int id) throws BLLException{
		List<Proposition> listePropositions = null;
		try {
			listePropositions = DAOQuestion.selectAllByIDQuestion(id);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
		return listePropositions;
	}
	public Question addQuestion(Question q) throws BLLException {
		
		try {
			q = DAOQuestion.add(q);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
		return q;
	}
}
