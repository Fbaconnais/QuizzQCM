package bll;

import java.sql.Date;
import java.sql.Timestamp;
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

	public void addPromotion(Promotion p) throws BLLException {
		try {
			DAO.add(p);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
	}

	public void removePromotion(String codePromo) throws BLLException {
		try {
			DAO.removePromo(codePromo);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
	}

	public Promotion selectPromo(String codePromo) throws BLLException {
		Promotion p = new Promotion();

		try {
			p = DAO.selectPromo(codePromo);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}

		return p;
	}

	public void updatePromo(Promotion p) throws BLLException {
		try {
			DAO.update(p);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
	}

	public List<Promotion> selectAllPromos() throws BLLException {
		List<Promotion> liste = null;

		try {
			liste = DAO.selectAll();
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}

		return liste;
	}

	public void inscrirePromoATest(String codePromo, int idTest, String dateDebutValidite, String dateFinValidite,
			String heureDebut, String heureFin) throws BLLException {
		String dateDebut[] = dateDebutValidite.split("-");
		String heureDeb[] = heureDebut.split(":");
		String dateFin[] = dateFinValidite.split("-");
		String heureFi[] = heureFin.split(":");
		Timestamp datedebut = ManipDates.getDateViaString(dateDebut, heureDeb);
		Timestamp datefin = ManipDates.getDateViaString(dateFin, heureFi);
		System.out.println(datedebut+"" +datefin);
		try {
			DAO.inscrirePromoATest(codePromo, idTest, datedebut, datefin);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
	}
	
	public Boolean verifPromoInscriteATest(String codePromo,int idTest) throws BLLException {
		Boolean test = false;
		
		try {
			test = DAO.verifPromoInscriteATest(codePromo, idTest);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
		
		return test;
	}

}
