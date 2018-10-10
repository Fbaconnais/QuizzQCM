package dal;

import java.sql.Timestamp;
import java.util.List;

import bo.Promotion;

public interface DAOPromotion extends DAOGeneric<Promotion> {
	public void removePromo(String codePromo) throws DALException;
	public Promotion selectPromo(String codePromo) throws DALException;
	public void inscrirePromoATest(String codePromo,int idTest,Timestamp dateDebut, Timestamp dateFin) throws DALException;
	public Boolean verifPromoInscriteATest(String codePromo,int idTest) throws DALException;
	public List<Promotion> recherchePromos(String codePromo) throws DALException;
}
