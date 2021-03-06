package bll;

import java.util.List;

import bo.BeanGeneral;
import bo.Epreuve;
import dal.DALException;
import dal.DAOEpreuve;
import dal.DAOFactory;

public class EpreuveManager {

	private DAOEpreuve DAO;

	private EpreuveManager() {
		DAO = DAOFactory.getDAOEpreuve();
	}

	public static EpreuveManager getMger() {
		return new EpreuveManager();
	}

	public Epreuve addEpreuve(Epreuve epreuve) throws BLLException {
		try {
			epreuve = DAO.add(epreuve);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
		return epreuve;
	}

	public Epreuve selectEpreuve(int id) throws BLLException {
		Epreuve epreuve = null;

		try {
			epreuve = DAO.selectOne(id);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}

		return epreuve;
	}

	public void updateEpreuve(Epreuve epreuve) throws BLLException {

		try {
			DAO.update(epreuve);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}

	}

	public List<Epreuve> selectAllEpreuves() throws BLLException {
		List<Epreuve> listeEpreuves = null;

		try {
			listeEpreuves = DAO.selectAll();
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
		return listeEpreuves;
	}

	public List<Epreuve> selectAllEpreuvesByIDUser(int id) throws BLLException {
		List<Epreuve> listeEpreuves = null;
		try {
			listeEpreuves = DAO.selectAllByIDProfil(id);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
		return listeEpreuves;
	}

	public void cloturerEpreuve(int idEpreuve) throws BLLException {
		try {
			DAO.cloturerEpreuve(idEpreuve);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
	}
	public void removeEpreuve(int idEpreuve) throws BLLException {
		
		try {
			DAO.remove(idEpreuve);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
	}
	
	public List<Epreuve> selectAllByIDEtDate(int id) throws BLLException {
		List<Epreuve> listeEpreuves = null;
		try {
			listeEpreuves = DAO.selectAllByIDEtDate(id);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
		return listeEpreuves;
	}
	public List<Epreuve> selectEpreuvesTermineesCandidat(int idCandidat) throws BLLException {
		List<Epreuve> liste = null;
		
		try {
			liste=DAO.getEpreuvesTermineesParCandidat(idCandidat);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
		return liste;
		
	}
	public BeanGeneral selectEpreuvesTermineesParPromo(String codePromo) throws BLLException {
		BeanGeneral Rowan_Atkinson = new BeanGeneral();
		
		try {
			Rowan_Atkinson = DAO.getEpreuvesTermineesParPromo(codePromo);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
		
		return Rowan_Atkinson;
	}
}
