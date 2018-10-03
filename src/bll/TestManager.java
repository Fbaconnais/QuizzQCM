package bll;

import dal.DAOFactory;
import dal.DAOPromotion;
import dal.DAOTest;

public class TestManager {
private DAOTest DAO;
	
	private TestManager() {
		DAO = DAOFactory.getDAOTest();
	}
	
	public static TestManager getMger() {
		return new TestManager();
	}
}
