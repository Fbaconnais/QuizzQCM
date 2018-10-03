package bll;

import java.util.List;

import bo.Test;
import dal.DALException;
import dal.DAOFactory;
import dal.DAOTest;

public class TestManager {
private DAOTest DAO;
	
	private TestManager() {
		DAO = DAOFactory.getDAOTest();
	}
	
	public static TestManager getMger() {
		return new TestManager();
	}
	
	public void addTest(Test t) throws BLLException{
		try {
			DAO.add(t);
		} catch (DALException e) {
		throw new BLLException(e.getMessage(), e);
		}
	}
	
	public void removeTest(int idTest) throws BLLException{
		try {
			DAO.remove(idTest);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
	}
	public Test selectTest(int idTest) throws BLLException{
		Test t = new Test();
		
		try {
			t = DAO.selectOne(idTest);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
		
		return t;
	}
	public void updateTest(Test t)throws BLLException{
		try {
			DAO.update(t);
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
	}
	public List<Test> selectAllTests() throws BLLException{
		List<Test> liste = null;
		
		try {
			liste = DAO.selectAll();
		} catch (DALException e) {
			throw new BLLException(e.getMessage(), e);
		}
		
		return liste;
	}
}
