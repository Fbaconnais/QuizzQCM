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
		if (request.getSession().getAttribute("profilCon") != null) {
			profil = (String) request.getSession().getAttribute("profilCon");
		} else {
			request.getRequestDispatcher("/WEB-INF/jsp/erreur/autorisation.jsp").forward(request, response);
		}
		if (!(profil.equals("responsable de formation") || profil.equals("cellule de recrutement"))) {
			request.getRequestDispatcher("/WEB-INF/jsp/erreur/autorisation.jsp").forward(request, response);
		} else {
	
			request.getRequestDispatcher("/WEB-INF/jsp/collaborateur/responsable/accueilinscriptions.jsp")
					.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
