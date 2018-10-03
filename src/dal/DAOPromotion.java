package dal;

import java.sql.Date;

import bo.Promotion;

public interface DAOPromotion extends DAOGeneric<Promotion> {
	public void removePromo(String codePromo) throws DALException;
	public Promotion selectPromo(String codePromo) throws DALException;
	public void inscrirePromoATest(String codePromo,int idTest,Date dateDebut, Date dateFin) throws DALException;
}
