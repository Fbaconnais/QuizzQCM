package rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import bll.BLLException;
import bll.EpreuveManager;
import bo.BeanGeneral;
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
	
	@POST
	@Path("/{id}/close")
	public Boolean cloturerEpreuve(@PathParam("id") int idEpreuve) throws RestException {
		EpreuveManager epreuveMger = EpreuveManager.getMger();
		try {
			epreuveMger.cloturerEpreuve(idEpreuve);
		} catch (BLLException e) {
			throw new RestException(e.getMessage(), e);
		}
		return true;
		
	}
	
	@DELETE
	@Path("/{id}")
	public Boolean deleteEpreuve(@PathParam("id") int id) throws RestException {
		EpreuveManager EMger = EpreuveManager.getMger();
		
		try {
			EMger.removeEpreuve(id);
		} catch (BLLException e) {
			throw new RestException(e.getMessage(), e);
		}
		
		return true;
	}
	@GET
	@Path("/candidat/{id}")
	public BeanGeneral recupEpreuvesCandidat(@PathParam("id") int idCandidat) throws RestException {
		BeanGeneral Rowan_Atkinson = new BeanGeneral();
		EpreuveManager epreuveMger = EpreuveManager.getMger();

		try {
			Rowan_Atkinson.setEpreuves(epreuveMger.selectEpreuvesTermineesCandidat(idCandidat));
		} catch (BLLException e) {
			throw new RestException(e.getMessage(), e);
		}
		return Rowan_Atkinson;
	}
	
	@GET
	@Path("/promos/{id}")
	public BeanGeneral recupEpreuvesPromo(@PathParam("id") String codePromo) throws RestException {
		BeanGeneral Rowan_Atkinson = new BeanGeneral();
		EpreuveManager epreuveMger = EpreuveManager.getMger();

		try {
			Rowan_Atkinson = epreuveMger.selectEpreuvesTermineesParPromo(codePromo);
		} catch (BLLException e) {
			throw new RestException(e.getMessage(), e);
		}
		return Rowan_Atkinson;
	}
	
}
