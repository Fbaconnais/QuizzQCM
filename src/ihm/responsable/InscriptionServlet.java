package ihm.responsable;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.BLLException;
import bll.PromotionManager;
import bll.TestManager;
import bo.Promotion;
import bo.Test;

@WebServlet("/collaborateur/inscription")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action;
		String url = null;
		if (request.getParameter("action") != null) {
			action = request.getParameter("action");
			switch (action) {
			case "stagiaire":
				url = newStagiaire(request, response);
				break;
			case "promotion":
				url = newpromotion(request, response);
				break;
			case "stagiairepromo":
				url = newStagiairePromo(request, response);
				break;
			case "candidattest":
				url = newCandidatTest(request, response);
				break;
			case "promotest":
				url = newPromoTest(request, response);
				break;
			}
		} else if (request.getSession().getAttribute("actionEnCours") != null) {
			action = (String) request.getSession().getAttribute("actionEnCours");
			switch (action) {
			case "stagiaire":
				url = newStagiaire(request, response);
				break;
			case "promotion":
				url = newpromotion(request, response);
				break;
			case "stagiairepromo":
				url = newStagiairePromo(request, response);
				break;
			case "candidattest":
				url = newCandidatTest(request, response);
				break;
			case "promotest":
				url = newPromoTest(request, response);
				break;
			}
		} else {
			url = "collaborateur";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url=null;
		String action = request.getParameter("actionajout");
		System.out.println(request.getParameter("test"));
//		switch (action) {
//		case "promotest" :
//			int idTest = Integer.parseInt(request.getParameter("myhidden"));
//			String codePromo = request.getParameter("promo");
//			String dateDebutValidite = request.getParameter("dateDebutValidite");
//			String dateFinValidite = request.getParameter("dateFinValidite");
//			PromotionManager promoMger = PromotionManager.getMger();
//			try {
//				promoMger.inscrirePromoATest(codePromo,idTest,dateDebutValidite, dateFinValidite);
//				request.getSession().setAttribute("messageValidation", "Requête exécutée");
				url = "";
//			} catch (BLLException e) {
//				request.getSession().setAttribute("erreur", e.getMessage());
//				url = "/WEB-INF/jsp/erreur/affichageMessageErreur.jsp";
//			}
//			break;
//		}
		
		request.getRequestDispatcher(url).forward(request, response);
		
	}

	
	
	
	
	
	
	
	private String newPromoTest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TestManager testMger = TestManager.getMger();
		PromotionManager promoMger = PromotionManager.getMger();
		
		
		try {
			List<Promotion> listePromos = promoMger.selectAllPromos();
			List<Test> listeTests = testMger.selectAllTests();
			request.getSession().setAttribute("promos", listePromos);
			request.getSession().setAttribute("tests", listeTests);
		} catch (BLLException e) {
			request.getSession().setAttribute("erreur", e.getMessage());
			return "/WEB-INF/jsp/erreur/affichageMessageErreur.jsp";
		}
				
		return "/WEB-INF/jsp/collaborateur/responsable/inscription/newPromoTest.jsp";

	}

	private String newCandidatTest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/WEB-INF/jsp/collaborateur/responsable/inscription/newCandidatTest.jsp";

	}

	private String newStagiairePromo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/WEB-INF/jsp/collaborateur/responsable/inscription/newStagiaireInPromo.jsp";

	}

	private String newpromotion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		return "/WEB-INF/jsp/collaborateur/responsable/inscription/newPromotion.jsp";

	}

	private String newStagiaire(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/WEB-INF/jsp/collaborateur/responsable/inscription/newStagiaire.jsp";

	}

}
