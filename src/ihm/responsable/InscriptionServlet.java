package ihm.responsable;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.BLLException;
import bll.PromotionManager;
import bll.TestManager;
import dal.DALException;
import dal.DAOFactory;
import dal.DAOPromotion;
import dal.DAOTest;

@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action;
		if (request.getParameter("action") != null) {
			action = request.getParameter("action");
			switch (action) {
			case "stagiaire":
				newStagiaire(request, response);
				break;
			case "promotion":
				newpromotion(request, response);
				break;
			case "stagiairepromo":
				newStagiairePromo(request, response);
				break;
			case "candidattest":
				newCandidatTest(request, response);
				break;
			case "promotest":
				newPromoTest(request, response);
				break;
			}
		} else if (request.getSession().getAttribute("actionEnCours") != null) {
			action = (String) request.getSession().getAttribute("actionEnCours");
			switch (action) {
			case "stagiaire":
				newStagiaire(request, response);
				break;
			case "promotion":
				newpromotion(request, response);
				break;
			case "stagiairepromo":
				newStagiairePromo(request, response);
				break;
			case "candidattest":
				newCandidatTest(request, response);
				break;
			case "promotest":
				newPromoTest(request, response);
				break;
			}
		} else {
			request.getRequestDispatcher("collaborateur").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void newPromoTest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TestManager testMger = TestManager.getMger();
		PromotionManager promoMger = PromotionManager.getMger();
		
		
		try {
			request.getSession().setAttribute("promos", promoMger.selectAllPromos());
			request.getSession().setAttribute("tests", testMger.selectAllTests());
		} catch (BLLException e) {
			request.getSession().setAttribute("erreur", e.getMessage());
			response.sendRedirect("erreur");
		}
		
		
		
		request.getRequestDispatcher("/WEB-INF/jsp/collaborateur/responsable/inscription/newPromoTest.jsp").forward(request, response);

	}

	private void newCandidatTest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/collaborateur/responsable/inscription/newCandidatTest.jsp").forward(request, response);

	}

	private void newStagiairePromo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/collaborateur/responsable/inscription/newStagiaireInPromo.jsp").forward(request, response);

	}

	private void newpromotion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/jsp/collaborateur/responsable/inscription/newPromotion.jsp").forward(request, response);

	}

	private void newStagiaire(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/collaborateur/responsable/inscription/newStagiaire.jsp").forward(request, response);

	}

}
