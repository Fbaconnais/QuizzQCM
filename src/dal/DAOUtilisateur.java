package dal;

import java.util.List;

import bo.Utilisateur;

public interface DAOUtilisateur extends DAOGeneric<Utilisateur> {
	public String Authentification(String email, String password) throws DALException;
	public Utilisateur getUserByEmail(String email) throws DALException;
	public List<Utilisateur> getUsersByCodePromo(String codePromo) throws DALException;
	public void updatePassword(int id, String password) throws DALException;
	public List<Utilisateur> getUserByEmailOrName(String namemail) throws DALException;



}
