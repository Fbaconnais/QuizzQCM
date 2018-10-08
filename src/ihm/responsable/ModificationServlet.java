package ihm.responsable;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.BLLException;
import bll.UtilisateurManager;
import bo.Candidat;
import bo.Profil;
import bo.Promotion;
import bo.Utilisateur;

@WebServlet("/collaborateur/responsable/modif")
public class ModificationServlet extends HttpServlet {
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
				url = "/WEB-INF/jsp/collaborateur/responsable/modif/stagiaire.jsp";
				break;
			case "promotion":
				url = "/WEB-INF/jsp/collaborateur/responsable/modif/promo.jsp";
				break;
			}

		} else {
			url = "inscriptions";
		}

		request.getRequestDispatcher(url).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String url = "";
		switch (action) {
		case "stagiaire":
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			int idutil = Integer.parseInt(request.getParameter("idutil"));
			int codeProfil = Integer.parseInt(request.getParameter("profil"));
			Utilisateur util = new Candidat();
			if (codeProfil == 1) {
				String codePromo = request.getParameter("promo");
				Promotion p = new Promotion(codePromo, "");
				((Candidat)util).setPromotion(p);
			}
			Profil profil = new Profil(codeProfil, "");
			util.setProfil(profil);
			util.setIdUtilisateur(idutil);
			util.setNom(nom);
			util.setPrenom(prenom);
			util.setEmail(email);
			
			UtilisateurManager UMger = UtilisateurManager.getMger();
			
			try {
				UMger.updateUser(util);
			} catch (BLLException e) {
				request.getSession().setAttribute("erreur", e.getMessage());
				url = request.getContextPath() + "/erreur";
			}
			
			break;
		}
		response.sendRedirect(url);
	}

}
