package dal;

import bo.ReponseTirage;

public interface DAOReponseTirage extends DAOGeneric<ReponseTirage>{
	public void gererReponseTirage(int idProposition, int idQuestion, int idEpreuve) throws DALException;
	public boolean verifReponse(int idProposition, int idQuestion, int idEpreuve) throws DALException;
}
