package ihm.responsable;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

@WebServlet("/collaborateur/responsable/inscription")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String regexDate = "^(?:(?:31(-)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(-)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(-)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(-)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
	private String regexTemps = "^(([0-1]{0,1}[0-9])|(2[0-3])):[0-5]{0,1}[0-9]$";

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

		} else {
			url = "accinscription";
		}

		request.getRequestDispatcher(url).forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("messageValidation", null);
		String url = null;
		String action = request.getParameter("actionajout");
		String codePromo;
		switch (action) {
		case "promotest":
			int idTest = 0;
			if (request.getParameter("test").equals("Choisir un test dans la liste")) {
				request.getSession().setAttribute("messageValidation", "Sélectionner un test");
				url = request.getContextPath() + "/collaborateur/responsable/inscription?action=promotest";

			} else {
				idTest = Integer.parseInt(request.getParameter("test"));
				codePromo = request.getParameter("promo");
				if (codePromo.equals("Choisir une promotion dans la liste")) {
					request.getSession().setAttribute("messageValidation", "Sélectionner une promotion");
					url = request.getContextPath() + "/collaborateur/responsable/inscription?action=promotest";
				} else {
					String dateDebutValidite = request.getParameter("dateDebutValidite");
					String dateFinValidite = request.getParameter("dateFinValidite");
					String heureDebutValidite = request.getParameter("HeureDebutValidite");
					String heureFinValidite = request.getParameter("HeureFinValidite");
					Pattern date = Pattern.compile(regexDate);
					Pattern heure = Pattern.compile(regexTemps);
					Matcher matchDateDebut = date.matcher(dateDebutValidite);
					Matcher matchDateFin = date.matcher(dateFinValidite);
					Matcher matchHeureDebut = heure.matcher(heureDebutValidite);
					Matcher matchHeureFin = heure.matcher(heureFinValidite);

					if (!(matchDateDebut.matches()) || !(matchDateFin.matches())) {
						request.getSession().setAttribute("messageValidation",
								"Veuillez entrer une date au format correct: Jour-Mois-Année.");
						url = request.getContextPath() + "/collaborateur/responsable/inscription?action=promotest";
					} else {
						if (!(matchHeureDebut.matches()) || !(matchHeureFin.matches())) {

							request.getSession().setAttribute("messageValidation",
									"Veuillez entrer une heure au format correct: Heure:Minute.");
							url = request.getContextPath() + "/collaborateur/responsable/inscription?action=promotest";

						} else {

							PromotionManager promoMger = PromotionManager.getMger();
							try {

								if (promoMger.verifPromoInscriteATest(codePromo, idTest)) {

									request.getSession().setAttribute("messageValidation",
											"Un ou plusieurs membre(s) de la promotion est(sont) déjà inscrit(s) à ce test");
									url = request.getContextPath()
											+ "/collaborateur/responsable/inscription?action=promotest";

								} else {
									promoMger.inscrirePromoATest(codePromo, idTest, dateDebutValidite, dateFinValidite,
											heureDebutValidite, heureFinValidite);
									request.getSession().setAttribute("messageValidation", "Requête exécutée");
									url = request.getContextPath()
											+ "/collaborateur/responsable/inscription?action=promotest";

								}
							} catch (BLLException e) {
								e.printStackTrace();

								request.getSession().setAttribute("erreur", e.getMessage());
								url = request.getContextPath() + "/erreur";
							}
						}
					}
				}
			}

			break;

		case "stagiaire":

			int idProfil = Integer.parseInt(request.getParameter("type"));
			codePromo = request.getParameter("promo");
			if ((codePromo.equals("Choisir une promotion dans la liste")) && idProfil == 1) {
				request.getSession().setAttribute("messageValidation", "Sélectionner une promotion au stagiaire");
				url = request.getContextPath() + "/collaborateur/responsable/inscription?action=stagiaire";
			} else {
				String email = request.getParameter("email");
				String nom = request.getParameter("nom");
				String prenom = request.getParameter("prenom");
				String password = org.apache.commons.codec.digest.DigestUtils
						.sha256Hex(nom.toUpperCase() + prenom.substring(0, 1).toUpperCase());
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
						request.getSession().setAttribute("messageValidation",
								"Il existe déjà un utilisateur avec cet Email");
					}
					url = request.getContextPath() + "/collaborateur/responsable/inscription?action=stagiaire";

				} catch (BLLException e) {
					request.getSession().setAttribute("erreur", e.getMessage());
					url = request.getContextPath() + "/erreur";
				}

			}

			break;
		case "candidattest":
			if (request.getParameter("test").equals("Choisir un test dans la liste")) {
				request.getSession().setAttribute("messageValidation", "Sélectionner un test");
				url = request.getContextPath() + "/collaborateur/responsable/inscription?action=candidattest";
			} else {
				idTest = Integer.parseInt(request.getParameter("test"));
				int idUtil = Integer.parseInt(request.getParameter("idutil"));
				String dateDebutValidite = request.getParameter("dateDebutValidite");
				String dateFinValidite = request.getParameter("dateFinValidite");
				String heureDebutValidite = request.getParameter("HeureDebutValidite");
				String heureFinValidite = request.getParameter("HeureFinValidite");

				Pattern date = Pattern.compile(regexDate);
				Pattern heure = Pattern.compile(regexTemps);
				Matcher matchDateDebut = date.matcher(dateDebutValidite);
				Matcher matchDateFin = date.matcher(dateFinValidite);
				Matcher matchHeureDebut = heure.matcher(heureDebutValidite);
				Matcher matchHeureFin = heure.matcher(heureFinValidite);

				if (!(matchDateDebut.matches()) || !(matchDateFin.matches())) {
					request.getSession().setAttribute("messageValidation",
							"Veuillez entrer une date au format correct: Jour-Mois-Année.");
					url = request.getContextPath() + "/collaborateur/responsable/inscription?action=promotest";
				} else {
					if (!(matchHeureDebut.matches()) || !(matchHeureFin.matches())) {

						request.getSession().setAttribute("messageValidation",
								"Veuillez entrer une heure au format correct: Heure:Minute.");
						url = request.getContextPath() + "/collaborateur/responsable/inscription?action=promotest";

					} else {

						UtilisateurManager userMger = UtilisateurManager.getMger();
						try {

							if (userMger.verifCandidatInscritAtest(idTest, idUtil)) {

								request.getSession().setAttribute("messageValidation",
										"Ce candidat est déjà inscrit à ce test");
								url = request.getContextPath()
										+ "/collaborateur/responsable/inscription?action=candidattest";

							} else {

								userMger.ajouterCandidatATest(dateDebutValidite, dateFinValidite, heureDebutValidite,
										heureFinValidite, idTest, idUtil);
								request.getSession().setAttribute("messageValidation", "Requête exécutée");
								url = request.getContextPath()
										+ "/collaborateur/responsable/inscription?action=candidattest";

							}
						} catch (BLLException e) {

							request.getSession().setAttribute("erreur", e.getMessage());
							url = request.getContextPath() + "/erreur";
						}

					}
				}
			}

			break;

		case "stagiairepromo":
			codePromo = request.getParameter("promo");
			UtilisateurManager UtilMger = UtilisateurManager.getMger();
			if (codePromo.equals("Choisir une promotion dans la liste")) {
				request.getSession().setAttribute("messageValidation", "Sélectionner une promotion");
				url = request.getContextPath() + "/collaborateur/responsable/inscription?action=stagiairepromo";
			} else {
				int idUtil = Integer.parseInt(request.getParameter("idutil"));
				try {
					Utilisateur candidat = UtilMger.selectUser(idUtil);
					Promotion p = new Promotion();
					p.setId(codePromo);
					((Candidat) candidat).setPromotion(p);
					UtilMger.updateUser(candidat);
					request.getSession().setAttribute("messageValidation", "Requête exécutée");
					url = request.getContextPath() + "/collaborateur/responsable/inscription?action=stagiairepromo";
				} catch (BLLException e) {
					e.printStackTrace();
					request.getSession().setAttribute("erreur", e.getMessage());
					url = request.getContextPath() + "/erreur";
				}

			}

			break;
		case "promotion":
			codePromo = request.getParameter("codePromo");
			PromotionManager promoMger = PromotionManager.getMger();

			try {
				Promotion p = promoMger.selectPromo(codePromo);
				if (p == null) {
					p = new Promotion();
					p.setId(codePromo);
					p.setLibelle(request.getParameter("libelle"));
					promoMger.addPromotion(p);
					request.getSession().setAttribute("messageValidation", "Requête exécutée");
					url = request.getContextPath() + "/collaborateur/responsable/inscription?action=promotion";
				} else {
					request.getSession().setAttribute("messageValidation", "Il existe déjà une promotion avec ce code");
					url = request.getContextPath() + "/collaborateur/responsable/inscription?action=promotion";
				}
			} catch (BLLException e) {
				request.getSession().setAttribute("erreur", e.getMessage());
				url = request.getContextPath() + "/erreur";
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
			return "/erreur";
		}

		return "/WEB-INF/jsp/collaborateur/responsable/inscription/newPromoTest.jsp";

	}

	private String newCandidatTest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		TestManager testMger = TestManager.getMger();

		try {
			List<Test> listeTests = testMger.selectAllTests();
			request.getSession().setAttribute("tests", listeTests);
		} catch (BLLException e) {
			request.getSession().setAttribute("erreur", e.getMessage());
			return "/erreur";
		}

		return "/WEB-INF/jsp/collaborateur/responsable/inscription/newCandidatTest.jsp";

	}

	private String newStagiairePromo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PromotionManager promoMger = PromotionManager.getMger();

		try {
			List<Promotion> listePromos = promoMger.selectAllPromos();
			request.getSession().setAttribute("promos", listePromos);
		} catch (BLLException e) {
			request.getSession().setAttribute("erreur", e.getMessage());
			return "/erreur";
		}

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
			return "/erreur";
		}
		return "/WEB-INF/jsp/collaborateur/responsable/inscription/newStagiaire.jsp";

	}

}
