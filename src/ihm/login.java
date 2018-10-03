package ihm;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletContext;
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
	ServletContext application;

	@Override
	public void init() throws ServletException {
		application = this.getServletContext();
		application.setAttribute("erreur", null);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		application = this.getServletContext();
		if (request.getSession().getAttribute("profilCon") != null) {
			switch ((String)request.getSession().getAttribute("profilCon")) {
			case "candidat libre":
			case "stagiaire":
				request.getRequestDispatcher("/WEB-INF/jsp/candidat/accueil.jsp").forward(request, response);
				break;	
			case "formateur":
				request.getRequestDispatcher("/WEB-INF/jsp/collaborateur/accueilFormateur.jsp").forward(request, response);
				break;
			case "admin":
				request.getRequestDispatcher("/WEB-INF/jsp/collaborateur/accueilAdmin.jsp").forward(request, response);
				break;
			case "cellule de recrutement":
			case "responsable de formation":
				request.getRequestDispatcher("/WEB-INF/jsp/collaborateur/accueilResponsable.jsp").forward(request,
						response);
				break;
			}
		}
		request.getSession().setAttribute("profilCon", null);
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");		
		password = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
		String result = null;
		Utilisateur user = null;
		UtilisateurManager Mger = UtilisateurManager.getMger();
		try {
			result = Mger.authentification(email, password);

		} catch (BLLException e) {

			request.getSession().setAttribute("erreur", e.getMessage());
		}
		if (result == null) {
			request.getSession().setAttribute("profilCon", "erreur");
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		} else {
			try {
				user = Mger.getUserByEmail(email);
				request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("profilCon", user.getProfil().getLibelle());

			} catch (BLLException e) {

				request.getSession().setAttribute("erreur", e.getMessage());
			}
		}

		if (request.getSession().getAttribute("erreur") != null) {
			response.sendRedirect("erreur");
		} else {
			if (user.getProfil().getLibelle().equals("stagiaire")
					|| user.getProfil().getLibelle().equals("candidat libre"))

				response.sendRedirect("candidat");
			else {
				response.sendRedirect("collaborateur");
			}

		}
	}
}
