package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import bll.BLLException;
import bll.ReponseTirageManager;

@Path("/reponse")
public class GestionReponseTirage {
	
	@GET
	@Path("/{idProposition}/{idQuestion}/{idEpreuve}/gestion")
	public void gérerReponseTirage(@PathParam("idProposition") int idProposition,@PathParam("idQuestion") int idQuestion,@PathParam("idEpreuve") int idEpreuve) throws BLLException{
		ReponseTirageManager Mger = ReponseTirageManager.getMger();
		Mger.gérerReponseTirage(idProposition, idQuestion, idEpreuve);
	}
	
	public boolean verifReponse(@PathParam("idProposition") int idProposition,@PathParam("idQuestion") int idQuestion,@PathParam("idEpreuve") int idEpreuve) throws BLLException{
		boolean estCoche;
		ReponseTirageManager Mger = ReponseTirageManager.getMger();
		estCoche = Mger.verifReponse(idProposition, idQuestion, idEpreuve);
		return estCoche;
	}
}
