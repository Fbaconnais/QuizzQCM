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
import bo.QuestionTirage;

@WebServlet("/test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;



	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<QuestionTirage> listeQuestionsTirages;
		QuestionTirageManager questionTirageMger = QuestionTirageManager.getMger();
		int id = Integer.parseInt(request.getParameter("id"));

		try {
			listeQuestionsTirages = questionTirageMger.getQuestionsViaIdEpreuve(id);
			if (listeQuestionsTirages.size() == 0) {
				questionTirageMger.genererTest(id);
				listeQuestionsTirages = questionTirageMger.getQuestionsViaIdEpreuve(id);
			}
			request.setAttribute("idEpreuve", id);
			request.getSession().setAttribute("listeQuestionsTirages", listeQuestionsTirages);
			request.getRequestDispatcher("/WEB-INF/jsp/candidat/test.jsp").forward(request, response);
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}
}
