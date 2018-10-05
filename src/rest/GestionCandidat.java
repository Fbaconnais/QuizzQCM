package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import bll.BLLException;
import bll.UtilisateurManager;
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
	public Utilisateur getOne(@PathParam("id") int id) throws BLLException {
		UtilisateurManager UMger = UtilisateurManager.getMger();
		Utilisateur u = UMger.selectUser(id);
		return u;
	}

}
