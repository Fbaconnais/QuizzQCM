package dal;

import java.util.List;

import bo.Proposition;
import bo.Question;

public interface DAOQuestion extends DAOGeneric<Question> {
	public List<Proposition> selectAllByIDQuestion(int id) throws DALException;
}
