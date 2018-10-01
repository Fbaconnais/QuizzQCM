package ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.BLLException;
import bll.UtilisateurManager;
import bo.Utilisateur;

/**
 * Servlet implementation class login
 */
@WebServlet(urlPatterns = { "/login", "" })
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getSession().setAttribute("auth", null);
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String result = null;
		Utilisateur user=null;
		UtilisateurManager Mger = UtilisateurManager.getMger();
		try {
			result = Mger.authentification(email, password);
		} catch (BLLException e) {
			request.getSession().setAttribute("erreur", e.getMessage());
		}
		if (result == null) {
			request.getSession().setAttribute("profilCon", "erreur");
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		} else {
			try {
				user = Mger.getUserByEmail(email);
				request.getSession().setAttribute("user", user);
			} catch (BLLException e) {
				request.getSession().setAttribute("erreur", e.getMessage());
			}
		}
		if (request.getSession().getAttribute("erreur") != null) {
			request.getRequestDispatcher("/WEB-INF/jsp/erreur/affichageMessageErreur.jsp");
		} else {
			switch (user.getProfil().getLibelle()) {
				case "candidat libre":
				case "stagiaire":
					request.getRequestDispatcher("candidat").forward(request, response);
				default :
					request.getRequestDispatcher("collaborateur").forward(request, response);
					
			}
		}
	}

}
