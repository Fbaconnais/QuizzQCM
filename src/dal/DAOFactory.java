package dal;

import dal.jdbc.DAOEpreuveJdbcImpl;
import dal.jdbc.DAOQuestionJdbcImpl;
import dal.jdbc.DAOQuestionTirageJdbcImpl;
import dal.jdbc.DAOSectionTestJdbcImpl;
import dal.jdbc.DAOTestJdbcImpl;
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
	
	public static DAOQuestion getDAOQuestion() {
		return new DAOQuestionJdbcImpl();
	}
	
	public static DAOSectionTest getDAOSectionTest() {
		return new DAOSectionTestJdbcImpl();
	}
	
	public static DAOQuestionTirage getDAOQuestionTirage() {
		return new DAOQuestionTirageJdbcImpl();
	}
	public static DAOTest getDAOTest() {
		return new DAOTestJdbcImpl();
	}
}
