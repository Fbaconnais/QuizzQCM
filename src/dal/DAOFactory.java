package dal;

import dal.jdbc.DAOEpreuveJdbcImpl;
import dal.jdbc.DAOThemeJdbcImpl;
import dal.jdbc.DAOUtilisateurJdbcImpl;

public class DAOFactory {

	public static DAOUtilisateur getDAOUtilisateur() {
		return new DAOUtilisateurJdbcImpl();
	}
	
	public static DAOEpreuve getDAOEpreuve() {
		return new DAOEpreuveJdbcImpl();
	}
	public static DAOTheme getDAOTheme() {
		return new DAOThemeJdbcImpl();
	}
}
