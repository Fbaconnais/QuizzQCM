package dal;

import bo.Utilisateur;

public interface DAOUtilisateur extends DAOGeneric<Utilisateur> {
	public String Authentification(String email, String password) throws DALException;
	public Utilisateur getUserByEmail(String email) throws DALException;




}
