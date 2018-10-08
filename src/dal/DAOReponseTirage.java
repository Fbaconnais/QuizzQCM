package dal;

import java.util.List;

import bo.ReponseTirage;

public interface DAOReponseTirage extends DAOGeneric<ReponseTirage>{
	public void gererReponseTirage(int idProposition, int idQuestion, int idEpreuve) throws DALException;
	public List<ReponseTirage> selectAllByIDEpreuve(int idEpreuve) throws DALException;
	public List<ReponseTirage> selectAllByIDQuestionIDEpreuve(int idQuestion, int idEpreuve) throws DALException;
}
