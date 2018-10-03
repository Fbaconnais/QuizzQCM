package dal;

import bo.Promotion;

public interface DAOPromotion extends DAOGeneric<Promotion> {
	public void removePromo(String codePromo) throws DALException;
	public Promotion selectPromo(String codePromo) throws DALException;
}
