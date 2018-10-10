package rest;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import bll.BLLException;
import bll.QuestionTirageManager;

@Path("/questionTirage")
public class GestionQuestionTirage {

	@PUT
	@Path("/{idQuestion}/{idEpreuve}/marquage")
	public Boolean gererMarquage(@PathParam("idQuestion") int idQuestion, @PathParam("idEpreuve") int idEpreuve) throws BLLException {
	QuestionTirageManager qtMger = QuestionTirageManager.getMger();
	qtMger.marquageQuestion(idQuestion, idEpreuve);
	return true;
	}
}
