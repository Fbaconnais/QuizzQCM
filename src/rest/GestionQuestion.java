package rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import bll.BLLException;
import bll.PropositionManager;
import bll.QuestionManager;
import bll.QuestionTirageManager;
import bll.ReponseTirageManager;
import bo.BeanGeneral;
import bo.Proposition;
import bo.Question;
import bo.QuestionTirage;
import bo.ReponseTirage;
import bo.Theme;

@Path("/question")
public class GestionQuestion {

	@GET
	@Path("/{idQuestion}/{idEpreuve}/get")
	public BeanGeneral selectQuestionPropsReponses(@PathParam("idQuestion") int idQuestion,
			@PathParam("idEpreuve") int idEpreuve) throws BLLException {
		BeanGeneral bean = new BeanGeneral();
		Question question = new Question();
		List<ReponseTirage> listeReponses;
		QuestionManager qMger = QuestionManager.getMger();
		ReponseTirageManager rMger = ReponseTirageManager.getMger();
		QuestionTirageManager qtMger = QuestionTirageManager.getMger();
		listeReponses = rMger.selectAllByIDQuestionIDEpreuve(idQuestion, idEpreuve);
		question = qMger.selectQuestion(idQuestion);
		bean.setQuestion(question);
		Map<Integer, ReponseTirage> reponsetirages = new HashMap<>();
		List<QuestionTirage> questiontirages = new ArrayList<QuestionTirage>();
		questiontirages = qtMger.getQuestionsViaIdEpreuve(idEpreuve);
		QuestionTirage questionT = null;

		for (QuestionTirage qt : questiontirages) {
			if (qt.getQuestion().getIdQuestion() == idQuestion) {
				questionT = qt;
				bean.setQuestiontirage(questionT);
			}
		}

		for (ReponseTirage rp : listeReponses) {
			reponsetirages.put(rp.getIdProposition(), rp);
		}
		bean.setReponsetirages(reponsetirages);
		return bean;
	}

	public List<Question> getAllQuestion() {

		return null;
	};

	@PUT
	@Path("/add/")
	public Boolean addQuestion(@FormParam("enonce") String enonce, @FormParam("propositions") String propositions,
			@FormParam("bonnesReponses") String reponses, @FormParam("themes") String themes,
			@FormParam("points") String points) throws RestException {
		String listePropositions[] = propositions.split("---");
		String cochee[] = reponses.split("---");
		String listethemes[] = themes.split("---");
		QuestionManager QMger = QuestionManager.getMger();
		for (int x = 0; x < listethemes.length; x++) {
			Question q = new Question();
			q.setEnonce(enonce);
			q.setPoints(Integer.parseInt(points));
			q.setTheme(new Theme(Integer.parseInt(listethemes[x]), ""));
			try {
				q = QMger.addQuestion(q);
				for (int y = 0; y < listePropositions.length; y++) {
					PropositionManager PropMger = PropositionManager.getMger();
					if (listePropositions[y] != null && listePropositions[y] != "") {
						Proposition p = new Proposition();
						p.setEnonce(listePropositions[y]);
						if (cochee[y].equals("false")) {
							p.setEstBonne(false);
						} else {
							p.setEstBonne(true);
						}
						p.setIdQuestion(q.getIdQuestion());

						PropMger.addProposition(p);

					}
				}
			} catch (BLLException e) {
				throw new RestException(e.getMessage(), e);
			}

		}

		return true;
	}

	public void deleteQuestion(@PathParam("id") int id) {

	}

	@GET
	@Path("/{id}/getProps")
	public List<Proposition> getPropsQuestion(@PathParam("id") int id) throws BLLException {
		QuestionManager qMger = QuestionManager.getMger();
		List<Proposition> listeProps = new ArrayList<Proposition>();
		listeProps = qMger.getPropositionsByIDQuestion(id);
		return listeProps;
	}

}
