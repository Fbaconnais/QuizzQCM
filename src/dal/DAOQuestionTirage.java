package dal;

import java.util.List;

import bo.QuestionTirage;

public interface DAOQuestionTirage extends DAOGeneric<QuestionTirage> {
	public void generationTest(int idTest,int idEpreuve) throws DALException;
	public List<QuestionTirage> getQuestionTirageDansLOrdre(int idEpreuve) throws DALException;
}