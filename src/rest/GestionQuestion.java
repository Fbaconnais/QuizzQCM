package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import bll.BLLException;
import bll.QuestionManager;
import bo.Proposition;
import bo.Question;

@Path("/question")
public class GestionQuestion {

	@GET
	@Path("/{id}/get")
	public Question selectOneQuestion(@PathParam("id") int id) throws BLLException {
		QuestionManager qMger = QuestionManager.getMger();
		Question question = new Question();
		question = qMger.selectQuestion(id);
		return question;
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
