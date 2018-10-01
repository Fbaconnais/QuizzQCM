package dal;

import dal.jdbc.DAOUtilisateurJdbcImpl;

public class DAOFactory {

	public static DAOUtilisateur getDAOUtilisateur() {
		return new DAOUtilisateurJdbcImpl();
	}
	
}
