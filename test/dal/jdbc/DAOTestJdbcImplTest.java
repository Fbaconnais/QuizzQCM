package dal.jdbc;

import static org.junit.Assert.fail;

import org.junit.Test;

public class DAOTestJdbcImplTest {

	/**
	 * Test: la méthode ramène un test avec ses attributs
	 */
	@Test
	public void testSelectOneCasNormal() {
		//Arrange
		bo.Test test = new bo.Test();
		
		
		//Act
		
		
		//Accept
		
	}
	
	/**
	 * Test: la méthode ne ramène pas
	 */
	@Test
	public void testSelectOneCasProbleme() {
		//Arrange
		bo.Test test = new bo.Test();
		
		
		//Act
		
		
		//Accept
		
	}

	@Test
	public void testSelectAll() {
		fail("Not yet implemented");
	}

}
