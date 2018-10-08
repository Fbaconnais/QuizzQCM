package rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import bll.BLLException;
import bll.QuestionManager;
import bll.ReponseTirageManager;
import bo.BeanGeneral;
import bo.Proposition;
import bo.Question;
import bo.ReponseTirage;

@Path("/question")
public class GestionQuestion {

	@GET
	@Path("/{idQuestion}/{idEpreuve}/get")
	public BeanGeneral selectQuestionPropsReponses(@PathParam("idQuestion") int idQuestion,@PathParam("idEpreuve") int idEpreuve) throws BLLException {
		BeanGeneral bean = new BeanGeneral();
		Question question = new Question();
		List<ReponseTirage> listeReponses;
		QuestionManager qMger = QuestionManager.getMger();
		ReponseTirageManager rMger = ReponseTirageManager.getMger();
		listeReponses = rMger.selectAllByIDQuestionIDEpreuve(idQuestion, idEpreuve);
		question = qMger.selectQuestion(idQuestion);
		bean.setQuestion(question);
		Map<Integer, ReponseTirage> reponsetirages = new HashMap<>();
		//reponsetirages.put(idQuestion, listeReponses);
		
		for(ReponseTirage rp : listeReponses) {
			reponsetirages.put(rp.getIdProposition(),rp);
		}
		
		
		bean.setReponsetirages(reponsetirages);
		return bean;
	}


	public List<Question> getAllQuestion() {

		return null;
	};


	public void addQuestion(@PathParam("data") Question data) {
	};


	public void deleteQuestion(@PathParam("id") int id) {

	};

	@GET
	@Path("/{id}/getProps")
	public List<Proposition> getPropsQuestion(@PathParam("id") int id) throws BLLException {
		QuestionManager qMger = QuestionManager.getMger();
		List<Proposition> listeProps = new ArrayList<Proposition>();
		listeProps = qMger.getPropositionsByIDQuestion(id);
		return listeProps;
	}
	
}
