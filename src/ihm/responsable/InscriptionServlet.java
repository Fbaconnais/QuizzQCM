package ihm.responsable;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.BLLException;
import bll.PromotionManager;
import bll.TestManager;
import bll.UtilisateurManager;
import bo.Candidat;
import bo.Profil;
import bo.Promotion;
import bo.Test;
import bo.Utilisateur;

@WebServlet("/collaborateur/inscription")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("je passe dans le doget");
		request.getSession().setAttribute("messageValidation", "");
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
			default:
				url = "/login";
			}
		} else {
			url = "inscriptions";
		}

		request.getRequestDispatcher(url).forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("messageValidation", null);
		String url = null;
		String action = request.getParameter("actionajout");
		switch (action) {
		case "promotest":
			int idTest = 0;
			if (request.getParameter("test").equals("Choisir un test dans la liste")) {
				request.getSession().setAttribute("messageValidation", "Sélectionner un test");
				url = request.getContextPath() + "/collaborateur/inscriptions";
			} else {
				idTest = Integer.parseInt(request.getParameter("test"));
				String codePromo = request.getParameter("promo");
				if (codePromo.equals("Choisir une promotion dans la liste")) {
					request.getSession().setAttribute("messageValidation", "Sélectionner une promotion");
					url = request.getContextPath() + "/collaborateur/inscriptions";
				} else {
					String dateDebutValidite = request.getParameter("dateDebutValidite");
					String dateFinValidite = request.getParameter("dateFinValidite");
					String heureDebutValidite = request.getParameter("HeureDebutValidite");
					String heureFinValidite = request.getParameter("HeureFinValidite");
					PromotionManager promoMger = PromotionManager.getMger();
					try {
						System.out.println(idTest + " " + codePromo);
						if (promoMger.verifPromoInscriteATest(codePromo, idTest)) {

							request.getSession().setAttribute("messageValidation",
									"Un ou plusieurs membre(s) de la promotion est(sont) déjà inscrit(s) à ce test");
							url = request.getContextPath() + "/collaborateur/inscriptions";
							System.out.println("il y a déjà des inscrits");
						} else {
							System.out.println(
									dateDebutValidite + dateFinValidite + heureDebutValidite + heureFinValidite);
							promoMger.inscrirePromoATest(codePromo, idTest, dateDebutValidite, dateFinValidite,
									heureDebutValidite, heureFinValidite);
							request.getSession().setAttribute("messageValidation", "Requête exécutée");
							url = request.getContextPath() + "/collaborateur/inscriptions";
							System.out.println("il n'y a pas d inscrits");
						}
					} catch (BLLException e) {
						e.printStackTrace();
						System.out.println(e.getMessage());
						request.getSession().setAttribute("erreur", e.getMessage());
						url = request.getContextPath() + "/erreur";
					}
				}
			}

			break;
		case "stagiaire":
			int idProfil = Integer.parseInt(request.getParameter("type"));
			String codePromo = request.getParameter("promo");
			if ((codePromo.equals("Choisir une promotion dans la liste")) && idProfil ==1) {
				request.getSession().setAttribute("messageValidation", "Sélectionner une promotion au stagiaire");
				url = request.getContextPath() + "/collaborateur/inscriptions";
			} else {
				String email = request.getParameter("email");
				String nom = request.getParameter("nom");
				String prenom = request.getParameter("prenom");
				String password = org.apache.commons.codec.digest.DigestUtils.sha256Hex(nom.toUpperCase() + prenom.substring(0, 1).toUpperCase());
				Candidat cand = new Candidat(nom, prenom, email, password);
				Profil p = new Profil();
				p.setId(idProfil);
				cand.setProfil(p);
				if (idProfil == 1) {
					cand.getProfil().setLibelle("stagiaire");
					Promotion pr = new Promotion();
					pr.setId(codePromo);
					cand.setPromotion(pr);
				} else {
					cand.getProfil().setLibelle("candidat libre");
				}
				UtilisateurManager UserMger = UtilisateurManager.getMger();
				
				try {
					Utilisateur test = UserMger.getUserByEmail(email);
					if (test == null) {					
					UserMger.addUser(cand);
					request.getSession().setAttribute("messageValidation", "Requête exécutée");
					} else {
						request.getSession().setAttribute("messageValidation", "Il existe déjà un utilisateur avec cet Email");
					}
					url = request.getContextPath() + "/collaborateur/inscriptions";
					
				} catch (BLLException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					request.getSession().setAttribute("erreur", e.getMessage());
					url = request.getContextPath() + "erreur";
				}
				
			}

			break;
		}

		response.sendRedirect(url);

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
		PromotionManager promoMger = PromotionManager.getMger();

		try {
			List<Promotion> listePromos;
			listePromos = promoMger.selectAllPromos();
			request.getSession().setAttribute("promos", listePromos);
		} catch (BLLException e) {
			request.getSession().setAttribute("erreur", e.getMessage());
			return "/WEB-INF/jsp/erreur/affichageMessageErreur.jsp";
		}
		return "/WEB-INF/jsp/collaborateur/responsable/inscription/newStagiaire.jsp";

	}

}
