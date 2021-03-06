package bll;

import java.sql.Timestamp;
import java.util.List;

import bo.Collaborateur;
import bo.Utilisateur;
import dal.DALException;
import dal.DAOFactory;
import dal.DAOUtilisateur;

public class UtilisateurManager {

	private DAOUtilisateur DAO;
	
	private UtilisateurManager() {
		DAO = DAOFactory.getDAOUtilisateur();
	}
	
	public static UtilisateurManager getMger() {
		return new UtilisateurManager();
	}
	
	public Utilisateur addUser(Utilisateur user) throws BLLException{
		try {
			user = DAO.add(user);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
		return user;
	}
	
	public String authentification(String email,String password) throws BLLException{
		String message = null;
		
		
		try {
			message = DAO.Authentification(email, password);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(),e);
		}
		return message;
	}
	
	public void removeUser(int id) throws BLLException{
		
		try {
			DAO.remove(id);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
	}
	
	public Utilisateur selectUser(int id) throws BLLException{
		Utilisateur user = null;
		
		try {
			user = DAO.selectOne(id);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
		
		
		return user;
		
	}
	
	public void updateUser(Utilisateur user) throws BLLException {
		
		try {
			DAO.update(user);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
	}
	
	public Utilisateur getUserByEmail(String email) throws BLLException{
		Utilisateur user = null;
		
		try {
			user = DAO.getUserByEmail(email);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
		
		
		return user;
	}
	public void setPassword(String email,String password) throws BLLException{
		
		try {
			DAO.updatePassword(email, password);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
	}
	public List<Utilisateur> getCandidatsFromPromo(String codePromo) throws BLLException{
		List<Utilisateur> liste = null;
		
		try {
			liste = DAO.getUsersByCodePromo(codePromo);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
		
		return liste;
		
	}
	public List<Utilisateur> getCandidatViaMailEtNom(String nommail) throws BLLException{
		List<Utilisateur> liste = null;
		
		try {
			liste = DAO.getUserByEmailOrName(nommail);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			throw new BLLException(e.getMessage(), e);
		}
		
		return liste;
	}
	
	public Boolean verifCandidatInscritAtest(int idTest,int idCandidat) throws BLLException{
		Boolean result = false;
		
		try {
			result = DAO.verifCandidatInscrit(idTest, idCandidat);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
		
		
		return result;
	}
	public void ajouterCandidatATest(String dateDebutValidite, String dateFinValidite,
			String heureDebut, String heureFin,int idTest, int idCandidat) throws BLLException {
		String dateDebut[] = dateDebutValidite.split("-");
		String heureDeb[] = heureDebut.split(":");
		String dateFin[] = dateFinValidite.split("-");
		String heureFi[] = heureFin.split(":");
		Timestamp datedebut = ManipDates.getDateViaString(dateDebut, heureDeb);
		Timestamp datefin = ManipDates.getDateViaString(dateFin, heureFi);
		
		try {
			DAO.inscrireCandidatAEpreuve(idTest, idCandidat, datedebut, datefin);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
	
	}
	
	public List<Collaborateur> getAllCollabos() throws BLLException{
		List<Collaborateur> liste = null;
		try {
			liste = DAO.getAllCollabos();
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
		return liste;
	}
	
}
