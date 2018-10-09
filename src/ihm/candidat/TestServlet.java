package ihm.candidat;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.BLLException;
import bll.EpreuveManager;
import bll.QuestionTirageManager;
import bo.Epreuve;
import bo.QuestionTirage;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<QuestionTirage> listeQuestionsTirages;
		Integer tpsEcoule;
		EpreuveManager epreuveMger = EpreuveManager.getMger();
		QuestionTirageManager questionTirageMger = QuestionTirageManager.getMger();
		int id = Integer.parseInt(request.getParameter("id"));
		Epreuve epreuve;	
			try {
				listeQuestionsTirages = questionTirageMger.getQuestionsViaIdEpreuve(id);
				if (listeQuestionsTirages.size() == 0) {
					questionTirageMger.genererTest(id);
					listeQuestionsTirages = questionTirageMger.getQuestionsViaIdEpreuve(id);
				} 
				epreuve = epreuveMger.selectEpreuve(id);
				tpsEcoule = epreuve.getTempsEcoule();
				if (epreuve.getEtat() != "EC") {
					epreuve.setEtat("EC");
				}
				epreuveMger.updateEpreuve(epreuve);
				request.setAttribute("idEpreuve", id);
				request.setAttribute("tpsEcoule", tpsEcoule);
				request.getSession().setAttribute("listeQuestionsTirages", listeQuestionsTirages);
				request.getRequestDispatcher("/WEB-INF/jsp/candidat/test.jsp").forward(request, response);
			} catch (BLLException e) {
				e.printStackTrace();
			}
		}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
