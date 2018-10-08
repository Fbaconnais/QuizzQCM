package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import bll.BLLException;
import bll.PromotionManager;
import bll.UtilisateurManager;
import bo.BeanGeneral;
import bo.Candidat;
import bo.Profil;
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
	
	@PUT
	@Path("/{id}")
	public Boolean modifUser(@PathParam("id") int id,@FormParam("nom") String nom, @FormParam("prenom") String prenom, @FormParam("email") String email, @FormParam("profil") String idProfil, @FormParam("promo") String codePromo) throws RestException{
		Utilisateur u = new Candidat();
		Promotion p = new Promotion();
		Profil pro = new Profil();
		int idpro = Integer.parseInt(idProfil);
		pro.setId(idpro);
		p.setId(codePromo);
		u.setProfil(pro);
		((Candidat)u).setPromotion(p);
		u.setIdUtilisateur(id);
		u.setNom(nom);
		u.setPrenom(prenom);
		u.setEmail(email);
		UtilisateurManager UMger = UtilisateurManager.getMger();
		try {
			UMger.updateUser(u);
		} catch (BLLException e) {
			throw new RestException(e.getMessage(), e);
		}
		
		
		
		return true;
	}

}
