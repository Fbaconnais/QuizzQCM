package bo;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CandidatTest {
	
	public static Candidat candidat;
	public static Promotion promotion;
	public static Profil profil;
	
	@BeforeClass
	public static void before() {
		candidat = new Candidat();
		promotion = new Promotion();
		profil = new Profil();
		promotion.setId("promoTest");
		promotion.setLibelle("libellePromoTest");
		profil.setId(1);
		profil.setLibelle("libelleProfilTest");
		candidat.setIdUtilisateur(2);
		candidat.setEmail("test@email");
		candidat.setNom("nomTest");
		candidat.setPrenom("prenomTest");
		candidat.setPassword("passwordTest");
		candidat.setProfil(profil);
		candidat.setPromotion(promotion);
		
	}
	

	@Test
	public void testGetPromotion() {
		Promotion expected = promotion;
		Promotion actual = promotion;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testSetPromotion() {
		fail("Not yet implemented");
	}

	@Test
	public void testCandidatPromotion() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetIdUtilisateur() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetIdUtilisateur() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNom() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetNom() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPrenom() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPrenom() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEmail() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetEmail() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testUtilisateur() {
		fail("Not yet implemented");
	}

	@Test
	public void testUtilisateurStringStringStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testUtilisateurIntStringStringStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetProfil() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetProfil() {
		fail("Not yet implemented");
	}

	@Test
	public void testUtilisateurIntStringStringStringStringProfil() {
		fail("Not yet implemented");
	}

}
