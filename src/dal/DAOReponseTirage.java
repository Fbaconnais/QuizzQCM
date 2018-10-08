package dal;

import java.util.List;

import bo.ReponseTirage;

public interface DAOReponseTirage extends DAOGeneric<ReponseTirage>{
	public void gererReponseTirage(int idProposition, int idQuestion, int idEpreuve) throws DALException;
	public List<ReponseTirage> recupReponses(int idProposition, int idQuestion, int idEpreuve) throws DALException;
}
