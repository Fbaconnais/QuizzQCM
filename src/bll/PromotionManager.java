package bll;

import java.sql.Date;
import java.util.List;

import bo.Promotion;
import dal.DALException;
import dal.DAOFactory;
import dal.DAOPromotion;

public class PromotionManager {
private DAOPromotion DAO;
	
	private PromotionManager() {
		DAO = DAOFactory.getDAOPromotion();
	}
	
	public static PromotionManager getMger() {
		return new PromotionManager();
	}
	
	public void addPromotion(Promotion p) throws BLLException{
		try {
			DAO.add(p);
		} catch (DALException e) {
		throw new BLLException(e.getMessage(), e);
		}
	}
	
	public void removePromotion(String codePromo) throws BLLException{
		try {
			DAO.removePromo(codePromo);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
	}
	public Promotion selectPromo(String codePromo) throws BLLException{
		Promotion p = new Promotion();
		
		try {
			p = DAO.selectPromo(codePromo);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
		
		return p;
	}
	public void updatePromo(Promotion p)throws BLLException{
		try {
			DAO.update(p);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
	}
	public List<Promotion> selectAllPromos() throws BLLException{
		List<Promotion> liste = null;
		
		try {
			liste = DAO.selectAll();
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
		
		return liste;
	}
	
	public void inscrirePromoATest(String codePromo, int idTest,String dateDebutValidite,String dateFinValidite) throws BLLException{
		String dateDebut[] = dateDebutValidite.split("-");
		String dateFin[] = dateFinValidite.split("-");
		Date datedebut = ManipDates.getDateViaString(dateDebut);
		Date datefin = ManipDates.getDateViaString(dateFin);
		
		try {
			DAO.inscrirePromoATest(codePromo, idTest, datedebut, datefin);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
		
		
		
	}
	
}
