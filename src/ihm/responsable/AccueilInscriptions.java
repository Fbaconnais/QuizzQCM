package ihm.responsable;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.Promotion;
import dal.DAOFactory;
import dal.DAOPromotion;
import dal.DAOUtilisateur;

/**
 * Servlet implementation class inscriptions
 */
@WebServlet("/collaborateur/inscriptions")
public class AccueilInscriptions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String profil = null;
		String url;
		if (request.getSession().getAttribute("profilCon") != null) {
			profil = (String) request.getSession().getAttribute("profilCon");
		} else {
			url = "/WEB-INF/jsp/erreur/autorisation.jsp";
		}
		if (!(profil.equals("responsable de formation") || profil.equals("cellule de recrutement"))) {
			url = "/WEB-INF/jsp/erreur/autorisation.jsp";
		} else {
	
			url = "/WEB-INF/jsp/collaborateur/responsable/accueilinscription.jsp";
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
