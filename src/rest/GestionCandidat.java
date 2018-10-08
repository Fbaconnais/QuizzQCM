package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import bll.BLLException;
import bll.PromotionManager;
import bll.UtilisateurManager;
import bo.BeanGeneral;
import bo.Promotion;
import bo.Utilisateur;

@Path("/users")
public class GestionCandidat {

	@GET
	@Path("/{nommail}/all")
	public List<Utilisateur> getAllCandidats(@PathParam("nommail") String nommail) throws BLLException {
		UtilisateurManager UMger = UtilisateurManager.getMger();
		List<Utilisateur> liste = new ArrayList<Utilisateur>();
		liste = UMger.getCandidatViaMailEtNom(nommail);
		return liste;
	};
	
	@GET
	@Path("/{id}")
	public BeanGeneral getOne(@PathParam("id") int id) throws BLLException {
		UtilisateurManager UMger = UtilisateurManager.getMger();
		PromotionManager PMger = PromotionManager.getMger();
		Utilisateur u = UMger.selectUser(id);
		List<Promotion> liste = PMger.selectAllPromos();
		BeanGeneral retour = new BeanGeneral();
		retour.setUtilisateur(u);
		retour.setPromotions(liste);
		return retour;
	}

}
