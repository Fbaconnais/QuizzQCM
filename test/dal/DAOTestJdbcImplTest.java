package dal;

import static org.junit.Assert.fail;

import org.junit.Test;

import dal.DALException;
import dal.DAOFactory;
import dal.DAOTest;
import junit.framework.Assert;

public class DAOTestJdbcImplTest {

	/**
	 * Test: la méthode ramène un test avec ses attributs
	 */
	@Test
	public void testSelectOneCasNormal() {
		//Arrange
		Integer idTest = 1;
		DAOTest dao = DAOFactory.getDAOTest();
		bo.Test test = new bo.Test();

		
		
		//Act
		try {
			test = dao.selectOne(idTest);
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		//Accept
		Assert.assertTrue("Problème ajouté: Selection d'un test par son identifiant erronée", test != null);
	}
	
	/**
	 * Test: ID ne pointant sur rien
	 */
	@Test
	public void testSelectOneCasProbleme() {
		//Arrange
		Integer idTest = 65;
		DAOTest dao = DAOFactory.getDAOTest();
		bo.Test test = new bo.Test();
		
		//Act
		try {
			test = dao.selectOne(idTest);
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		//Accept
		Assert.assertTrue("Problème ajouté: Selection d'un test par son identifiant n'existant pas en base ne devrait pas fonctionner", test == null);
	}

	@Test
	public void testSelectAll() {
		fail("Not yet implemented");
	}

}
