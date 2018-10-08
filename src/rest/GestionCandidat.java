package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
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
	public BeanGeneral getOne(@PathParam("id") int id) throws RestException {
		UtilisateurManager UMger = UtilisateurManager.getMger();
		PromotionManager PMger = PromotionManager.getMger();
		Utilisateur u = null;
		List<Promotion> liste = null;
		try {
			u = UMger.selectUser(id);
			liste = PMger.selectAllPromos();
		} catch (BLLException e) {
			throw new RestException(e.getMessage(), e);
		}
		
		BeanGeneral retour = new BeanGeneral();
		retour.setUtilisateur(u);
		retour.setPromotions(liste);
		return retour;
	}
	
	@DELETE
	@Path("/{id}")
	public Boolean deleteUser(@PathParam("id") int id) throws RestException {
		UtilisateurManager UMger = UtilisateurManager.getMger();
		try {
			UMger.removeUser(id);
		} catch (BLLException e) {
			throw new RestException(e.getMessage(), e);
		
		}	
		return true;
	}

}
