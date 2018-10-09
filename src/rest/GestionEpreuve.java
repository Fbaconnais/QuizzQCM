package rest;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import bll.BLLException;
import bll.EpreuveManager;
import bo.Epreuve;

@Path("/epreuve")
public class GestionEpreuve {

	@PUT
	@Path("/{id}/{nbSec}/timer")
	public Boolean updateTimer(@PathParam("id") int idEpreuve, @PathParam("nbSec") int nbSec) throws RestException{
		Epreuve epreuve = null;	
		EpreuveManager epreuveMger = EpreuveManager.getMger();
		try {
			epreuve = epreuveMger.selectEpreuve(idEpreuve);
			epreuve.setTempsEcoule(nbSec);
			epreuveMger.updateEpreuve(epreuve);
		} catch (BLLException e) {
			throw new RestException(e.getMessage(), e);
		}
		
		return true;
	}
	
	@GET
	@Path("/{id}/get")
	public Integer recupTimer(@PathParam("id") int idEpreuve) throws RestException {
		Epreuve epreuve = null;	
		EpreuveManager epreuveMger = EpreuveManager.getMger();
		Integer tpsEcoule=null;
		try {
			epreuve = epreuveMger.selectEpreuve(idEpreuve);
			tpsEcoule = epreuve.getTempsEcoule();
			epreuveMger.updateEpreuve(epreuve);
		} catch (BLLException e) {
			throw new RestException(e.getMessage(), e);
		}
		return tpsEcoule;
	}
}
