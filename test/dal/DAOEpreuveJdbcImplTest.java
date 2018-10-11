package dal;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import bo.Candidat;
import bo.Epreuve;
import bo.Profil;
import bo.Promotion;

public class DAOEpreuveJdbcImplTest {
	
	@Before
	public void testPreparationDAO() {

	}
	
	
	/**
	 * Test: ajout d'une épreuve en BDD
	 */
	@Test
	public void testAddCasNormal() {
		Epreuve epreuve = new Epreuve();
		Candidat candidat = new Candidat();
		bo.Test test = new bo.Test();
		Profil profil = new Profil();
		Promotion promotion = new Promotion();
		DAOEpreuve daoEpreuve = DAOFactory.getDAOEpreuve();
		DAOUtilisateur daoCandidat = DAOFactory.getDAOUtilisateur();
		DAOTest daoTest = DAOFactory.getDAOTest();
		profil.setLibelle("Test");
		promotion.setLibelle("TEST101");
		candidat.setEmail("test@test.test");
		candidat.setNom("Test test");
		candidat.setPassword("test");
		candidat.setPrenom("Test");
		candidat.setProfil(profil);
		candidat.setPromotion(promotion);
		test.setDescription("Test");
		test.setDuree(2800);
		test.setLibelle("Test");
		test.setLogoLangage("");
		test.setSeuil_bas(8);
		test.setSeuil_haut(12);
		epreuve.setCandidat(candidat);
		epreuve.setTest(test);

	}
	
	/**
	 * Test: ajout d'une épreuve ne répondant pas aux critères en BDD
	 */
	@Test
	public void testAddCasProbleme() {
		fail("Not yet implemented");
	}

	/**
	 * 
	 */
	@Test
	public void testSelectOne() {
		fail("Not yet implemented");
	}

	/**
	 * 
	 */
	@Test
	public void testSelectAll() {
		fail("Not yet implemented");
	}

	/**
	 * 
	 */
	@Test
	public void testSelectAllByIDProfil() {
		fail("Not yet implemented");
	}

	/**
	 * 
	 */
	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	/**
	 * 
	 */
	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	/**
	 * 
	 */
	@Test
	public void testGetIdTestViaIdEpreuve() {
		fail("Not yet implemented");
	}

	/**
	 * 
	 */
	@Test
	public void testCloturerEpreuve() {
		fail("Not yet implemented");
	}

	/**
	 * 
	 */
	@Test
	public void testSelectAllByIDEtDate() {
		fail("Not yet implemented");
	}

	/**
	 * 
	 */
	@Test
	public void testGetEpreuvesTermineesParCandidat() {
		fail("Not yet implemented");
	}

	/**
	 * 
	 */
	@Test
	public void testGetEpreuvesTermineesParPromo() {
		fail("Not yet implemented");
	}

}
