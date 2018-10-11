package bll;

import java.util.List;

import bo.Theme;
import dal.DALException;
import dal.DAOFactory;
import dal.DAOTheme;

public class ThemeManager {
private DAOTheme DAO;
	
	private ThemeManager() {
		DAO = DAOFactory.getDAOTheme();
	}
	
	public static ThemeManager getMger() {
		return new ThemeManager();
	}
	public List<Theme> selectAll() throws BLLException{
		List<Theme> liste = null;
		
		try {
			liste = DAO.selectAll();
		} catch (DALException e) {
			throw new BLLException(e.getMessage(),e);
		}
		
		return liste;
	}
	
}
