package bll;

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
	
}
