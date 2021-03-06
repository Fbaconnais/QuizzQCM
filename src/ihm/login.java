package ihm;

import java.io.IOException;
import java.util.HashMap;

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

	@Override
	public void init() throws ServletException {
		HashMap<String, Boolean> connections = new HashMap<String, Boolean>();
		ServletContext application = this.getServletContext();
		application.setAttribute("connections", connections);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("messageValidation");
		request.getSession().removeAttribute("erreur");
		if (request.getSession().getAttribute("profilCon") != null
				&& request.getSession().getAttribute("user") != null) {
			switch ((String) request.getSession().getAttribute("profilCon")) {
			case "candidat libre":
			case "stagiaire":
				response.sendRedirect("candidat");
				break;
			case "formateur":
				response.sendRedirect("collaborateur");
				break;
			case "administrateur":
				response.sendRedirect("collaborateur");
				break;
			case "cellule de recrutement":
			case "responsable de formation":
				response.sendRedirect("collaborateur");
				break;
			default:
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
				break;
			}
		} else {

			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.removeAttribute("musique");
		String email = request.getParameter("email");

		@SuppressWarnings("unchecked")
		HashMap<String, Boolean> connections = (HashMap<String, Boolean>)(this.getServletContext()
				.getAttribute("connections"));
		if (connections.get(email) != null && connections.get(email)) {

			request.getSession().setAttribute("profilCon", "connecte");
			response.sendRedirect(request.getContextPath() + "/login");
		} else {

			String password = request.getParameter("password");
			password = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
			String result = null;
			Utilisateur user = null;
			UtilisateurManager Mger = UtilisateurManager.getMger();
			try {
				result = Mger.authentification(email, password);

			} catch (BLLException e) {
				e.printStackTrace();
				request.getSession().setAttribute("erreur", e.getMessage());
			}
			if (result == null) {
				request.getSession().setAttribute("profilCon", "erreur");
				response.sendRedirect(request.getContextPath() + "/login");
			} else {
				try {
					user = Mger.getUserByEmail(email);
					request.getSession().setMaxInactiveInterval(7200);
					request.getSession().setAttribute("user", user);
					request.getSession().setAttribute("profilCon", user.getProfil().getLibelle());
					connections.put(email, true);
					this.getServletContext().setAttribute("connections", connections);
					if (user.getProfil().getLibelle().equals("stagiaire")
							|| user.getProfil().getLibelle().equals("candidat libre"))

						response.sendRedirect(request.getContextPath() + "/candidat");
					else {
						response.sendRedirect(request.getContextPath() + "/collaborateur");
					}

				} catch (BLLException e) {
					e.printStackTrace();
					request.getSession().setAttribute("erreur", e.getMessage());
					response.sendRedirect(request.getContextPath() + "/erreur");
				}

			}
		}
	}
}
