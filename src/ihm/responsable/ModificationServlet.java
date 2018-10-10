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
			url = "accinscription";
		}

		request.getRequestDispatcher(url).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
