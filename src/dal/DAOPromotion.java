package dal;

import java.sql.Timestamp;

import bo.Promotion;

public interface DAOPromotion extends DAOGeneric<Promotion> {
	public void removePromo(String codePromo) throws DALException;
	public Promotion selectPromo(String codePromo) throws DALException;
	public void inscrirePromoATest(String codePromo,int idTest,Timestamp dateDebut, Timestamp dateFin) throws DALException;
}
